package com.DEVLOP.Application.Queries;


import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import com.DEVLOP.Application.Mappers.IEquipmentTypeMapper;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Repositories.EquipmentTypeRepo;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentTypeQuery {

    private final EquipmentTypeRepo equipmentTypeRepo;

    @Qualifier("IEquipmentTypeMapperImpl")
    private final IEquipmentTypeMapper mapper;

    public EquipmentTypeQuery (EquipmentTypeRepo equipmentTypeRepo, @Qualifier("IEquipmentTypeMapperImpl") IEquipmentTypeMapper mapper){
        this.equipmentTypeRepo = equipmentTypeRepo;
        this.mapper = mapper;
    }


    @Transactional
    public CompletableFuture<EquipmentTypeDto> GetEquipmentTypeByID(int id){
        return CompletableFuture.supplyAsync(()->{
            EquipmentType equipmentType = equipmentTypeRepo.ReturnEquipmentTypeByID(id).orElseThrow();
            return mapper.MapToEquipmentTypeDto(equipmentType);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<List<EquipmentTypeDto>> ReturnListOfEquipmentTypes (List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<EquipmentType> equipmentTypeList = equipmentTypeRepo.ReturnListOfEquipmentType(idList);
            return equipmentTypeList.stream().map(mapper::MapToEquipmentTypeDto).toList();
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<List<EquipmentTypeDto>> ReturnAllEquipmentTypes(){
        return CompletableFuture.supplyAsync(()->{
            List<EquipmentType> equipmentTypeList = equipmentTypeRepo.FindAll();
            return equipmentTypeList.stream().map(mapper::MapToEquipmentTypeDto).toList();
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }

}
