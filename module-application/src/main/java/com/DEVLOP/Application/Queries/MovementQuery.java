package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Queries.IMovementQuery;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class MovementQuery implements IMovementQuery {
    private final MovementRepository movementRepository;
    private final IMovementMapperImpl mapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public MovementQuery(MovementRepository movementRepository, IMovementMapperImpl mapper,
                         EquipmentRepository equipmentRepository){
        this.mapper = mapper;
        this.movementRepository = movementRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<List<MovementDto>> ReturnEquipmentMovementsAsync(int id){
        return CompletableFuture.supplyAsync(() -> {
            Equipment eq = GetEquipment(id);
            List<MovementDto> movements = MapMovementList(eq);
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
            if (ex.getCause() instanceof MovementNotFoundException) {
                log.warn("No movements found for equipment ID: {}", id);
                return List.of(); // Return empty list
            }

            log.error("Unexpected error retrieving movements for equipment ID {}: {}", id, ex.getMessage());
            throw new CompletionException(new RuntimeException("Unexpected error retrieving movements", ex));
        });
    }
    private List<MovementDto> MapMovementList(Equipment eq){ //refactor
        return Optional.ofNullable(movementRepository.GetMovementsOfEquipment(eq))
                .orElse(Collections.emptyList()) // Prevents NullPointerException
                .stream()
                .peek(move -> System.out.println(move.getId())) // Debugging (Optional)
                .map(mapper::MapToMovementDto) // Stream mapping for cleaner code
                .toList();
    }
    private Equipment GetEquipment(int id) {
        return equipmentRepository.FindByID(id)
                .orElseThrow(() -> new EquipmentNotFoundException("No equipment found with ID " + id));
    }
}
