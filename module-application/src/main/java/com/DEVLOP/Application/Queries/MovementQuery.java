package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Queries.IMovementQuery;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MovementQuery implements IMovementQuery {
    private final MovementRepository movementRepository;
    private final IMovementMapper mapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public MovementQuery(MovementRepository movementRepository, IMovementMapper mapper,
                         EquipmentRepository equipmentRepository){
        this.mapper = mapper;
        this.movementRepository = movementRepository;
        this.equipmentRepository = equipmentRepository;
    }
    /*
    todo
    finalizar metodo quando mapper estiver pronto
     *///este tem de ser pedido async

    public CompletableFuture<List<MovementDto>> ReturnEquipmentMovementsAsync(int id){
        Optional<Equipment> eq = equipmentRepository.FindByID(id);
        if (eq.isEmpty()){
            throw new EquipmentNotFoundException("Equipment does not exist");
        }
        return CompletableFuture.supplyAsync(()->{
                List<Movement> movementList = movementRepository.GetMovementsOfEquipment(eq.get());
                return movementList.stream()
                        .map(mapper::MapToMovementDto).collect(Collectors.toList());
        });
    }
}
