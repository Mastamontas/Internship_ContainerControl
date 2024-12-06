package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Queries;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IAggregatedInformationMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IEquipmentMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IEquipmentQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementHistoryQuery {
//este é o que vai fazer a query dos movimentos quando for chamado um equipamento pelo ID
    private final IEquipmentQueries movementInformationQuery;
    private final IEquipmentMapper equipmentMapper;
    private final IAggregatedInformationMapper aggregatedMapper;

    @Autowired
    public MovementHistoryQuery(IEquipmentQueries movementInformationQuery, IEquipmentMapper equipmentMapper, IAggregatedInformationMapper aggregatedMapper) {
        this.movementInformationQuery = movementInformationQuery;
        this.equipmentMapper = equipmentMapper;
        this.aggregatedMapper = aggregatedMapper;
    }

    /**
     * Retorna todos os movimentos associados a um equipamento quando selecionado pelo seu prefixo.
     * também pode ser filtrado por numero e CD
     * @param equipmentPrefix
     * @return
     */
    //como colocar varios atributos de query?
/*    public List<EquipmentMovementDTO> getEquipmentMovement(String equipmentPrefix) {
        return equipmentMapper.toEquipmentMovementDTOList(movementInformationQuery.getEquipmentMovement(ID));
     }*/
}
