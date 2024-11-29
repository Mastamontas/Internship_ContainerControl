package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Queries;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IAggregatedInformationMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IEquipmentMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IContainerMovementQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Query class to fetch the movement history of an equipment
 * get the movement history of an equipment by its ID
 * @return list of equipment movement DTO
 *
 * get the specific movement details and equipment by clicking in the date of the movement
 *
 * @return aggregatedInformationDTO
 */


@Service
public class MovementHistoryQuery {
//este é o que vai fazer a query dos movimentos quando for chamado um equipamento pelo ID
    private final IContainerMovementQueries movementInformationQuery;
    private final IEquipmentMapper equipmentMapper;
    private final IAggregatedInformationMapper aggregatedMapper;

    @Autowired
    public MovementHistoryQuery(IContainerMovementQueries movementInformationQuery, IEquipmentMapper equipmentMapper, IAggregatedInformationMapper aggregatedMapper) {
        this.movementInformationQuery = movementInformationQuery;
        this.equipmentMapper = equipmentMapper;
        this.aggregatedMapper = aggregatedMapper;
    }
    /**
     * public List<EquipmentMovementDTO> getEquipmentMovement(int ID) {
     *    return equipmentMapper.toEquipmentMovementDTOList(movementInformationQuery.getEquipmentMovement(ID));
     *    }
     */
    /**
     * preciso do ID do equipamento e de uma data do movimento para ver o DTO inteiro relacionado com aquela data
     *
     */
}
