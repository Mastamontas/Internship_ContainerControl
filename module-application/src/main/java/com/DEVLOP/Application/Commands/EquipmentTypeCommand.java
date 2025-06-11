package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import com.DEVLOP.Application.Mappers.IEquipmentTypeMapper;
import com.DEVLOP.CustomExceptions.EquipmentClassNotFoundException;
import com.DEVLOP.CustomExceptions.EquipmentTypeNotFoundException;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import com.DEVLOP.Repositories.EquipmentTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentTypeCommand {

    private EquipmentTypeRepo equipmentTypeRepo;
    private final EquipmentClassRepo equipmentClassRepo;


    @Qualifier("IEquipmentTypeMapperImpl")
    private IEquipmentTypeMapper mapper;

    @Autowired
    public EquipmentTypeCommand (EquipmentTypeRepo equipmentTypeRepo, EquipmentClassRepo equipmentClassRepo, @Qualifier("IEquipmentTypeMapperImpl") IEquipmentTypeMapper mapper){
        this.equipmentTypeRepo = equipmentTypeRepo;
        this.equipmentClassRepo = equipmentClassRepo;
        this.mapper =mapper;
    }

    //todo verificar melhor opcao do que retornar o dto que nao vem atualizado
    @Transactional
    public CompletableFuture<EquipmentTypeDto> CreateEquipmentType (EquipmentTypeDto equipmentTypeDto){
        return CompletableFuture.supplyAsync(()->{
            //retornar a equipment class que tem de existir para criar o type
            EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(equipmentTypeDto.getEquipmentClassID()).orElseThrow(()-> new EntityNotFoundException("No equipment class wih that id"));
            EquipmentType equipmentType = mapper.MapToEquipmentType(equipmentTypeDto);
            equipmentType.setEquipmentClass(equipmentClass);
            equipmentTypeRepo.PersistEquipmentType(equipmentType);
            return equipmentTypeDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    /*
    todo:
    Vejo duas maneiras de se fazer esta funcao, ou como esta abaixo ou alterando o mapeamento do ID para o ID direto
    da equipment class dentro do type. nao sei qual das duas mais vantajosas, excepto que esta parece ter mais verificação
     */
    @Transactional
    public CompletableFuture<EquipmentTypeDto> UpdateEquipmentType(EquipmentTypeDto equipmentTypeDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(equipmentTypeDto.getEquipmentClassID())
                    .orElseThrow(()-> new EquipmentClassNotFoundException("Equipment class with that ID does not exist"));//ver o que se pode mandar aqui
            EquipmentType equipmentType = equipmentTypeRepo.ReturnEquipmentTypeByID(equipmentTypeDto.getId())
                    .orElseThrow(()-> new EquipmentTypeNotFoundException("Equipment type with that ID not found"));
            equipmentType.setEquipmentClass(equipmentClass);
            equipmentTypeRepo.PersistEquipmentType(mapper.UpdateEquipmentType(equipmentTypeDto, equipmentType));
            return equipmentTypeDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
