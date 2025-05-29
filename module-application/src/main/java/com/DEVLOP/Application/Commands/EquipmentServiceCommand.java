package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import com.DEVLOP.Application.Mappers.IEquipmentServiceMapper;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Repositories.EquipmentServiceRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentServiceCommand {
    private final EquipmentServiceRepo equipmentServiceRepo;
    @Qualifier("IEquipmentServiceMapperImpl")
    private final IEquipmentServiceMapper mapper;

    @Autowired
    public EquipmentServiceCommand (EquipmentServiceRepo equipmentServiceRepo, IEquipmentServiceMapper mapper){
        this.equipmentServiceRepo = equipmentServiceRepo;
        this.mapper = mapper;
    }
    @Transactional
    public CompletableFuture<EquipmentServiceDto> CreateNewEquipmentService(EquipmentServiceDto equipmentServiceDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentService equipmentService = mapper.MapToEquipmentService(equipmentServiceDto);
            equipmentServiceRepo.PersistEquipmentService(equipmentService);
            return equipmentServiceDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<EquipmentServiceDto> UpdateEquipmentService(EquipmentServiceDto equipmentServiceDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentService  equipmentService = equipmentServiceRepo.ReturnEquipmentServiceByID(equipmentServiceDto.getId())
                    .orElseThrow();
            equipmentServiceRepo.PersistEquipmentService(mapper.UpdateEquipmentService(equipmentServiceDto,equipmentService));
            return equipmentServiceDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
