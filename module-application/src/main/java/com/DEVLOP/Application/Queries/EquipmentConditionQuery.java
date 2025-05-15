package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Application.Mappers.IEquipmentConditionMapper;
import com.DEVLOP.Entities.EquipmentCondition;
import com.DEVLOP.Repositories.EquipmentConditionRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EquipmentConditionQuery {
    private final EquipmentConditionRepo equipmentConditionRepo;
    @Qualifier("IEquipmentConditionImpl")
    private final IEquipmentConditionMapper mapper;

    @Autowired
    public EquipmentConditionQuery(EquipmentConditionRepo equipmentConditionRepo, IEquipmentConditionMapper mapper){
        this.equipmentConditionRepo = equipmentConditionRepo;
        this.mapper = mapper;
    }
    @Transactional
    public CompletableFuture<EquipmentConditionDto> FindEquipmentConditionByID(int id){
        return CompletableFuture.supplyAsync(()->{
            EquipmentCondition equipmentCondition = equipmentConditionRepo.FindEquipmentConditionByID(id)
                    .orElseThrow();
            return mapper.MapToEquipmentConditionDto(equipmentCondition);
        });
    }
    @Transactional
    public CompletableFuture<EquipmentConditionDto> FindEquipmentConditionByCode(String code){
        return CompletableFuture.supplyAsync(()->{
            EquipmentCondition equipmentCondition = equipmentConditionRepo.FindEquipmentConditionByCode(code).orElseThrow();
            return mapper.MapToEquipmentConditionDto(equipmentCondition);
        });
    }

    @Transactional
    public CompletableFuture<List<EquipmentConditionDto>> FindEquipmentConditionByIDList(List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<EquipmentCondition> equipmentConditionList = equipmentConditionRepo.ReturnListOfEquipmentCondition(idList);
            return equipmentConditionList.stream().map(mapper::MapToEquipmentConditionDto).toList();
        }).exceptionally(ex ->{
            //todo: melhorar esta exception
            throw new EntityNotFoundException(String.valueOf(ex.getCause()));
        });
    }

}
