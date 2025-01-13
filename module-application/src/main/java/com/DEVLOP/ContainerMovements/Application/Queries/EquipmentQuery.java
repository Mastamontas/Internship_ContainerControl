package com.DEVLOP.ContainerMovements.Application.Queries;

import com.DEVLOP.ContainerMovements.Application.ApplicationMappers.IEquipmentApplicationMapper;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.DEVLOP.ContainerMovements.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Repositories.EquipmentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class for returning all the equipment in the system
 */
@Service //tag para a camada de aplicação
public class EquipmentQuery {
    private final EquipmentRepository equipmentRepositoryImplementation;
    private final IEquipmentApplicationMapper iEquipmentApplicationMapper;

    @Autowired//annotation for automatic injection of required field
    public EquipmentQuery(EquipmentRepository equipmentRepositoryImplementation, @Qualifier("IEquipmentApplicationMapperImpl") IEquipmentApplicationMapper iEquipmentApplicationMapper){
        this.equipmentRepositoryImplementation = equipmentRepositoryImplementation;
        this.iEquipmentApplicationMapper = iEquipmentApplicationMapper;
    }

    /**
     * Returns all equipments from a repository or an empty collection
     *
     * @return List of equipment entities
     */
    private List<Equipment> getAllEquipments(){
        List<Equipment> equipmentList = equipmentRepositoryImplementation.findAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
           /* System.out.println("Equipment list returned empty");
            return Collections.emptyList();*/
            throw new EquipmentNotFoundException("No lists found");
        }
        return equipmentList;
    }

    /**
     * Precisa validação adicional
     * Maps a list of equipment entities to a list of equipment DTO's
     *
     * @param equipmentList
     * @return List of equipment DTO's
     */
    private List<EquipmentInformationDTO> getAllEquipmentDTO (List<Equipment> equipmentList){
        return equipmentList.stream().map(iEquipmentApplicationMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Public methods that aggregates both the return of equipment list and the maps to a DTO
     * returns the list of equipment DTO's or throws an object not found exception
     *
     * @return list of equipment information DTO's
     */
    public List<EquipmentInformationDTO> fetchAndMapEquipments(){
        try{
            List<Equipment> equipmentList =  getAllEquipments();
            System.out.println("Calling fetch equipments");
            return getAllEquipmentDTO(equipmentList);
        } catch (Exception e){
            throw new ObjectNotFoundException("no equipments found",e);
        }
    }



    //faz um pedido asincrono dos dados de owner e line para o kafka
    //for each equipment in the equipment list above, return the values from kafka
    //implementação kafka fica para depois

    //validação DTO
    //Valid DTO - public boolean
    /**
     * Equipment
     * id not null
     * number not null
     * check digit not null
     * equipment type code not null
     * equipment class code not null
     */

    //quando os dados sao retornados transforma em DTO
    //envia para o controller (web api)


}
