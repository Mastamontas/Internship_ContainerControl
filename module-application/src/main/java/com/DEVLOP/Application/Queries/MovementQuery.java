package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;

import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.IMovementRepo;
import com.DEVLOP.Interfaces.Queries.IMovementQuery;
import com.DEVLOP.Repositories.EquipmentRepo;
import com.DEVLOP.Repositories.MovementRepo;
import com.DEVLOP.Specifications.MovementSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


@Slf4j
@Service
public class MovementQuery implements IMovementQuery {

    @Autowired
    private final MovementRepo movementRepo;

    @Qualifier("IMovementMapperImpl")
    @Autowired
    private final IMovementMapper mapper;
    @Autowired
    private final EquipmentRepo equipmentRepo;

    private final MovementSpecification movementSpecification;


    @Autowired
    public MovementQuery(MovementRepo movementRepo, IMovementMapperImpl mapper,
                         EquipmentRepo equipmentRepo, MovementSpecification movementSpecification){
        this.mapper = mapper;
        this.movementRepo = movementRepo;
        this.equipmentRepo = equipmentRepo;
        this.movementSpecification = movementSpecification;
    }

    @Override
    @Transactional
    public CompletableFuture<List<MovementDto>> ReturnMovementListFromEquipAsync(int id){
        System.out.println("entering service method" + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("inside service method" + Thread.currentThread().getName());
            Equipment eq = GetEquipment(id);
            List<MovementDto> movements = MapMovementList(eq);//refactor method names
            if (movements.isEmpty()) {
                log.warn("Equipment with ID {} has no movements.", id);
                throw new MovementNotFoundException("No movements found for equipment ID: " + id);
            }
            return movements;
        }).exceptionally(ex -> {
            if (ex.getCause() instanceof EquipmentNotFoundException) {
                log.error("Equipment not found: {}", ex.getMessage());
                throw new CompletionException(new EquipmentNotFoundException(ex.getCause().getMessage()));
            }
            if (ex.getCause() instanceof MovementNotFoundException) { //refactor: redundant
                log.warn("No movements found for equipment ID: {}", id);
                return List.of(); // Return empty list
            }

            log.error("Unexpected error retrieving movements for equipment ID {}: {}", id, ex.getMessage());
            throw new CompletionException(new RuntimeException("Unexpected error retrieving movements", ex));
        });
    }
    @Override
    @Transactional
    public CompletableFuture<MovementDto> ReturnMovementById(int movementId){
        return CompletableFuture.supplyAsync(()->{
            Movement move = GetMovement(movementId);
            return mapper.MapToMovementDto(move);
        }).exceptionally(ex ->{
            throw new CompletionException(new Exception("unsucessfull return of movement",ex.getCause()));
        });
    }

    //refactor/review
    private List<MovementDto> MapMovementList(Equipment eq){ //refactor
        return Optional.ofNullable(movementRepo.GetMovementsOfEquipment(eq))
                .orElse(Collections.emptyList()) // Prevents NullPointerException
                .stream()
                .peek(move -> System.out.println(move.getId())) // Debugging (Optional)
                .map(mapper::MapToMovementDto) // Stream mapping for cleaner code
                .toList();
    }
    private Movement GetMovement(int id){
        return movementRepo.FindMovementById(id).orElseThrow(()-> new MovementNotFoundException("No movement with that id " + id));
    }
    private Equipment GetEquipment(int id) {
        return equipmentRepo.FindByID(id)
                .orElseThrow(() -> new EquipmentNotFoundException("No equipment found with ID " + id));
    }

    @Override
    @Transactional
    public CompletableFuture<List<MovementDto>> ReturnFilteredMovementListAsync(Map<String,Object> filters){
        return CompletableFuture.supplyAsync(()->{
            Specification<Movement> spec = movementSpecification.BuildSpecification(filters);
            List<Movement> filteredMovementList = movementRepo.ReturnFilteredMovementList(spec);
            return filteredMovementList.stream().map(mapper::MapToMovementDto).toList();
        });
    }

    @Override
    @Transactional
    //todo: se lista vier vazia, retornar erro "No matches for that query"; Falta excepções quando campos são inválidos
    public CompletableFuture<List<MovementDto>> ReturnRangeFilteredMovementListAsync(Map<String, Object> fromFilter, Map<String, Object> toFilter){
        return CompletableFuture.supplyAsync(()->{
           Specification<Movement> rangeFilterSpec = movementSpecification.SpecificationBetween(fromFilter,toFilter);
           List<Movement> filteredMovementList = movementRepo.ReturnFilteredMovementList(rangeFilterSpec);
           return filteredMovementList.stream().map(mapper::MapToMovementDto).toList();
        });
    }
}
