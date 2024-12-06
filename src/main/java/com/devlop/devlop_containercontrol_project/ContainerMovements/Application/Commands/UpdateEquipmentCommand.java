package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Commands;


import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IEquipmentMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IEquipmentCommands;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IEquipmentQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * recebe DTO de equipamento do frontend
 * faz map desse dto para entity através do mapper
 * faz persistencia dessa nova entidade
 */
@Service
public class UpdateEquipmentCommand {
    private final IEquipmentMapper equipmentMapper;
    private final IEquipmentCommands containerMovementCommands;
    private final IEquipmentQueries containerMovementQueries;


    @Autowired
    public UpdateEquipmentCommand(IEquipmentMapper equipmentMapper, IEquipmentCommands containerMovementCommands,
                                  IEquipmentQueries containerMovementQueries) {
        this.equipmentMapper = equipmentMapper;
        this.containerMovementCommands = containerMovementCommands;
        this.containerMovementQueries= containerMovementQueries;
    }
    /*@Transactional
    public void updateEquipment(EquipmentInformationDTO equipmentInformationDTO, int eqID){
        //fetch the equipment, por ID ou por prefixo?
        Equipment eqInDB = containerMovementQueries.findEquipmentByID(eqID).orElseThrow()-> new ResourceNotFound("equipment not found");
        //transforma o DTO em entity
        equipmentMapper.updateEquipmentFromDTO(equipmentInformationDTO, eqInDB);
        //guarda a nova entity atualizada
        containerMovementCommands.saveAndFlush(eqInDB);
    }*/

}
