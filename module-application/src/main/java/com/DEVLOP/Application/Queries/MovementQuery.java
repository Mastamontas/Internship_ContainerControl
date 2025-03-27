package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;

import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Queries.IMovementQuery;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import com.DEVLOP.Specifications.MovementSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
    private final MovementRepository movementRepository;
    private final IMovementMapperImpl mapper;
    private final EquipmentRepository equipmentRepository;

    private final MovementSpecification movementSpecification;

    @Autowired
    public MovementQuery(MovementRepository movementRepository, IMovementMapperImpl mapper,
                         EquipmentRepository equipmentRepository,MovementSpecification movementSpecification){
        this.mapper = mapper;
        this.movementRepository = movementRepository;
        this.equipmentRepository = equipmentRepository;
        this.movementSpecification = movementSpecification;
    }

    @Override
    @Transactional
    public CompletableFuture<List<MovementDto>> ReturnMovementListFromEquipAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
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
        return Optional.ofNullable(movementRepository.GetMovementsOfEquipment(eq))
                .orElse(Collections.emptyList()) // Prevents NullPointerException
                .stream()
                .peek(move -> System.out.println(move.getId())) // Debugging (Optional)
                .map(mapper::MapToMovementDto) // Stream mapping for cleaner code
                .toList();
    }
    private Movement GetMovement(int id){
        return movementRepository.FindMovementById(id).orElseThrow(()-> new MovementNotFoundException("No movement with that id " + id));
    }
    private Equipment GetEquipment(int id) {
        return equipmentRepository.FindByID(id)
                .orElseThrow(() -> new EquipmentNotFoundException("No equipment found with ID " + id));
    }
    /*
    todo
    exceptions
    test
     */
    @Override
    @Transactional
    public CompletableFuture<List<MovementDto>> ReturnFilteredMovementListAsync(Map<String,Object> filters){
        return CompletableFuture.supplyAsync(()->{
            Specification<Movement> spec = movementSpecification.GetMovementSpecification(filters);
            List<Movement> filteredMovementList = movementRepository.ReturnFilteredMovementList(spec);
            return filteredMovementList.stream().map(mapper::MapToMovementDto).toList();
        });
    };
}
