package com.DEVLOP.Application.Queries;


import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Application.Mappers.IEquipmentClassMapper;
import com.DEVLOP.CustomExceptions.EquipmentClassNotFoundException;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentClassQuery {

    private final EquipmentClassRepo equipmentClassRepo;

    @Qualifier("IEquipmentClassMapper")
    private final IEquipmentClassMapper mapper;

    public EquipmentClassQuery (EquipmentClassRepo equipmentClassRepo, @Qualifier("IEquipmentClassMapperImpl") IEquipmentClassMapper mapper){
        this.equipmentClassRepo = equipmentClassRepo;
        this.mapper = mapper;
    }

    @Transactional
    public CompletableFuture<EquipmentClassDto> GetEquipmentClassById(int id){
        return CompletableFuture.supplyAsync(()->{
            EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(id)
                    .orElseThrow(()-> new EquipmentClassNotFoundException("Equipment class with that ID does not exist"));;
            return mapper.MapToEquipmentClassDto(equipmentClass);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<EquipmentClassDto> GetEquipmentByCode(String code){
        return CompletableFuture.supplyAsync(()->{
            EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByCode(code)
                    .orElseThrow(()-> new EquipmentClassNotFoundException("Equipment class with that code does not exist"));;
            return mapper.MapToEquipmentClassDto(equipmentClass);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<List<EquipmentClassDto>> GetEquipmentClassByIdList(List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<EquipmentClass> equipmentClassList = equipmentClassRepo.ReturnEquipmentClassListByID(idList);
            return equipmentClassList.stream().map(mapper::MapToEquipmentClassDto).toList();
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }


}
