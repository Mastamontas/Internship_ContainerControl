package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Commands;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.AggregatedInformationDTO;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.EquipmentMovementDTO;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IMovementMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Queries.MovementHistoryQuery;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IEquipmentCommands;
import com.devlop.devlop_containercontrol_project.Domain.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * classes command funcionam da frente para trás. Funcionalidades são de verificação de dados dos DTOS e transformação
 * em entidades para serem suheitos a CRUD na DB;
 * métodos public: verificação de permissoes e estrutura do DTO
 * métodos private: interação com bases de dados
 */

@Service
public class UpdateMovementCommand {
    //transformar os DTOS em entidades e vice versa
    private final IMovementMapper imovementMapper;
    //ir buscar dados para as entidades
    private final MovementHistoryQuery movementHistoryQuery;
    //para persistir dados dos movimentos
    private final IEquipmentCommands iEquipmentCommands;

    @Autowired
    public UpdateMovementCommand(IMovementMapper imovementMapper, MovementHistoryQuery movementHistoryQuery, IEquipmentCommands iEquipmentCommands){
        this.imovementMapper = imovementMapper;
        this.movementHistoryQuery = movementHistoryQuery;
        this.iEquipmentCommands = iEquipmentCommands;
    }
    //segurança

    //quando o DTO vem da frente aggregado, tem de ser transformado em movimento
/*    private EquipmentMovementDTO convertAggDTOToEquipMovDTO(AggregatedInformationDTO agDTO){
        return imovementMapper.fromAggregatedToMoveDTO(agDTO);
    }
    private Movement getMovementEntity(EquipmentMovementDTO equipMoveDTO){
        return imovementMapper.toMovementEntity(equipMoveDTO);
    }*/

    //das duas uma, ou se faz um repositório só para movements ou andamos aqui a desenrolar o novelo para chegar ao mov
    //dentro do equipment
/*    @Transactional
    protected void updateEquipmentMovement(AggregatedInformationDTO aggDTO){
        //o equipamento tenho de ir o ir buscar ao agregated
        iContainerMovementCommands.save(getMovementEntity(convertAggDTOToEquipMovDTO(aggDTO)));
        //tenho de ir buscar o movimento associado a este equipamento.
        //aggreg--> equip--> movement --> persist movement de equip

    }*/
}
