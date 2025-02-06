package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.CustomExceptions.EquipmentMappingException;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.EquipmentUpdateException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.Commands.IEquipmentCommands;
import com.DEVLOP.Interfaces.ICommands;
import com.DEVLOP.Repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
/*
injeto aqui a queries para ir buscar os equipamentos? Ou acedo diretamente ao repositorio?
 */
@Service
public class EquipmentCommand implements IEquipmentCommands {

    private final IEquipmentMapper iEquipmentMapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentCommand(@Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper, EquipmentRepository equipmentRepository) {
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentRepository = equipmentRepository;
    }

    /*
    nomenclatura desta função tem de ser pensada, porque herda o metodo da classe generica
     */
    @Transactional
    @Override
    public CompletableFuture<Void> UpdateEquipmentAsync(EquipmentDTO equipmentDTO){
        return CompletableFuture.runAsync(()->{
           try{
               Equipment eq = getAndMapEquipment(equipmentDTO);
               equipmentRepository.updateEquipment(eq);
           } catch (Exception e){
               throw new EquipmentUpdateException("failed to update equipment", e);
           }
        }).thenRun(()->{
            System.out.println("update was performed successfully");
        });
    }

    private Equipment getAndMapEquipment(@Valid EquipmentDTO eqDTO){
        Equipment eq = fetchEquipmentFromRepoByUniqueDetails(eqDTO.getPrefix(), eqDTO.getCheckDigit(), eqDTO.getNumber());
        Equipment updatedEq = iEquipmentMapper.updateEquipmentFromDTO(eqDTO, eq);
        if (updatedEq == null) {
            throw new EquipmentMappingException("Mapping failed: updated equipment is null!");
        }
        return updatedEq;
    }


    private Equipment fetchEquipmentFromRepoByUniqueDetails (String prefix, int checkDigit, int number){
        return equipmentRepository.findEquipmentByUniqueDetails(prefix, checkDigit, number)
                .orElseThrow(()->
                        new EquipmentNotFoundException(
                                String.format("No equipment found for details: Prefix=%s, CheckDigit=%d, Number=%d", prefix, checkDigit, number)));
    }
}
