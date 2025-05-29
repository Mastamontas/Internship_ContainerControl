package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Commands.IMovementCommands;
import com.DEVLOP.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/*
todo: some methods are overrided while other dont. Make methods consistent
todo: Methods are named async while others are not. Make method naming consistent
 */
@Service
public class MovementCommand implements IMovementCommands {

    private final IMovementMapper mapper;
    private final MovementRepo movementRepo;
    private final EquipmentRepo equipmentRepo;
    private final MovementTypeRepo movementTypeRepo;
    private final EquipmentLeasingRepo equipmentLeasingRepo;
    private final EquipmentConditionRepo equipmentConditionRepo;
    private final EquipmentStatusRepo equipmentStatusRepo;
    private final EquipmentServiceRepo equipmentServiceRepo;
    private final TransportMeansRepo transportMeansRepo;


    @Autowired
    public MovementCommand(@Qualifier("IMovementMapperImpl") IMovementMapper mapper, MovementRepo movementRepo, EquipmentRepo equipmentRepo, MovementTypeRepo movementTypeRepo, EquipmentLeasingRepo equipmentLeasingRepo, EquipmentConditionRepo equipmentConditionRepo, EquipmentStatusRepo equipmentStatusRepo, EquipmentServiceRepo equipmentServiceRepo, TransportMeansRepo transportMeansRepo){
        this.mapper = mapper;
        this.movementRepo = movementRepo;
        this.equipmentRepo = equipmentRepo;
        this.movementTypeRepo = movementTypeRepo;
        this.equipmentLeasingRepo = equipmentLeasingRepo;
        this.equipmentConditionRepo = equipmentConditionRepo;
        this.equipmentStatusRepo = equipmentStatusRepo;
        this.equipmentServiceRepo = equipmentServiceRepo;
        this.transportMeansRepo = transportMeansRepo;
    }

    //todo falta exceptionally
    @Override
    @Transactional
    public CompletableFuture<com.DEVLOP.Entities.Movement> UpdateMovementAsync(int id, MovementDto movementDto){
        return CompletableFuture.supplyAsync(()->{
            Movement move = movementRepo.FindMovementById(id).orElseThrow(()-> new MovementNotFoundException("Movement with that id " +
                    "does not exist"));
            return mapper.UpdateMovementEntity(movementDto, move);
        }).thenApplyAsync(updatedMovement ->{
            movementRepo.UpdateMovement(updatedMovement);
            return updatedMovement;
        });
    }


    @Transactional
    public CompletableFuture<List<MovementDto>> ChangeGroupMovement(List<Integer> movementIDs, MovementDto movementToChange){
        return CompletableFuture.supplyAsync(()-> {
            List<com.DEVLOP.Entities.Movement> movementList = GetSelectedMovements(movementIDs);
            return movementList.stream()
                    .map(mov -> mapper.UpdateMovementEntity(movementToChange, mov))
                    .toList();
        }).thenApply(updatedMovements->{
            movementRepo.SaveMovementList(updatedMovements);
            return updatedMovements.stream().map(mapper::MapToMovementDto).toList();
        }).exceptionally(e ->{
            throw new CompletionException(e);
        });
    };

    private List<com.DEVLOP.Entities.Movement> GetSelectedMovements(List<Integer> ids){
        List<com.DEVLOP.Entities.Movement> movements = movementRepo.ReturnMovementsByIDList(ids);
        if (movements.isEmpty() || movements.size() != ids.size()) {
            throw new MovementNotFoundException("One or more movements not found for the provided IDs: " + ids);
        }
        return movements;
    }

    /*
    todo: o retorno de erro quando nao se cria, retorna uma lista
     */
    @Transactional
    public CompletableFuture<List<MovementDto>> AddMovementToEquipmentGroup(List<Integer> equipmentIdList, MovementDto movementToAdd) {
        return CompletableFuture.supplyAsync(() -> {
            List<Equipment> equipmentList = GetEquipmentList(equipmentIdList);
            return equipmentList.stream().map(eq -> {
                Movement movement = mapper.MapToMovementEntity(movementToAdd);
                //pensar numa maneira melhor de fazer este setting
                movement.setMovementType(movementTypeRepo.FindMovementTypeByID(movementToAdd.getMovementTypeId()).orElseThrow());
                movement.setEquipmentLeasing(equipmentLeasingRepo.ReturnEquipmentLeasingByID(movementToAdd.getEquipmentLeasingId()).orElseThrow());
                movement.setEquipmentCondition(equipmentConditionRepo.FindEquipmentConditionByID(movementToAdd.getEquipmentConditionId()).orElseThrow());
                movement.setEquipmentStatus(equipmentStatusRepo.ReturnEquipmentStatusByID(movementToAdd.getEquipmentStatusId()).orElseThrow());
                movement.setEquipmentService(equipmentServiceRepo.ReturnEquipmentServiceByID(movementToAdd.getEquipmentServiceId()).orElseThrow());
                movement.setTransportMeans(transportMeansRepo.FindTransportMeansByID(movementToAdd.getTransportMeansId()).orElseThrow());
                movement.setEquipment(eq);
                movement.setEquipmentType(eq.getEquipmentType());
                return movement;
            }).toList();
        }).thenApply(movements -> {
            movementRepo.SaveMovementList(movements);
            return movements.stream()
                    .map(mapper::MapToMovementDto)
                    .toList();
        }).exceptionally(e -> {
            throw new EquipmentNotFoundException("cant create movements for selected equipments");
        });
    }
    //tem de verificar se entidade existe aqui
    private List<Equipment> GetEquipmentList(List<Integer> idList){
        return equipmentRepo.GetEquipmentListFromID(idList);
    }

    /*
    Range movement filtering

    tem de ser colocados dois equipamentos
    datas entre os movimentos tem de ser obrigatorios
    outros campos de filtros sao opcionais
    através do specification
     */

}
