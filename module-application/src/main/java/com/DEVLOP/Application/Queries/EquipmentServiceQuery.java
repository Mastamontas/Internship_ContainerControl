package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import com.DEVLOP.Application.Mappers.IEquipmentServiceMapper;
import com.DEVLOP.CustomExceptions.EquipmentServiceNotFoundException;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Repositories.EquipmentServiceRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentServiceQuery {
    private final EquipmentServiceRepo equipmentServiceRepo;


    @Qualifier("IEquipmentMapperImpl")
    private final IEquipmentServiceMapper mapper;
    @Autowired
    public EquipmentServiceQuery(EquipmentServiceRepo equipmentServiceRepo, IEquipmentServiceMapper mapper) {
        this.equipmentServiceRepo = equipmentServiceRepo;
        this.mapper = mapper;
    }

    @Transactional
    public CompletableFuture<EquipmentServiceDto> GetEquipmentServiceByID(int id) {
        return CompletableFuture.supplyAsync(() -> {
            EquipmentService equipmentService = equipmentServiceRepo.ReturnEquipmentServiceByID(id)
                    .orElseThrow(()-> new EquipmentServiceNotFoundException("Equipment service with that ID does not exist"));
            return mapper.MapToEquipmentServiceDto(equipmentService);
        }).exceptionally(ex -> {
            throw new CompletionException( ex);
        });
    }
    @Transactional
    public CompletableFuture<EquipmentServiceDto> GetEquipmentServiceByCode(String code) {
        return CompletableFuture.supplyAsync(() -> {
            EquipmentService equipmentService = equipmentServiceRepo.ReturnEquipmentServiceByCode(code)
                    .orElseThrow(()-> new EquipmentServiceNotFoundException("Equipment service with that code does not exist"));
            return mapper.MapToEquipmentServiceDto(equipmentService);
        }).exceptionally(ex -> {
            throw new CompletionException( ex);
        });
    }
    @Transactional
    public CompletableFuture<List<EquipmentServiceDto>> GetEquipmentServiceByListOfID(List<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> {
            List<EquipmentService> equipmentServices = equipmentServiceRepo.ReturnListOfEquipmentService(ids);
            return equipmentServices.stream().map(mapper::MapToEquipmentServiceDto).toList();
        }).exceptionally(ex -> {
            throw new CompletionException( ex);
        });
    }





}
