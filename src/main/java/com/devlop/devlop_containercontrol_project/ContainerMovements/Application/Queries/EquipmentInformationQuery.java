package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Queries;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper.IEquipmentMapper;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence.IContainerMovementQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta classe só serve para retornar a informação do equipamento
 *
 * que tipo de segurança posso implementar aqui?
 * o container control requer autenticação? se sim, que tipo de autenticação?
 * a autenticacao vem do frontend mas a verificacao do user nao e feita no container control
 * evento kafka para verificar se o user esta autenticado ou se existe
 */
@Service
public class EquipmentInformationQuery {

    private final IContainerMovementQueries equipmentInformationRepository;
    private final IEquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentInformationQuery(IContainerMovementQueries equipmentInformationRepository, IEquipmentMapper equipmentMapper) {
        this.equipmentInformationRepository = equipmentInformationRepository;
        this.equipmentMapper = equipmentMapper;
    }

    /*public List<EquipmentInformationDTO> getAllEquipmentInformation() {
        return equipmentMapper.toEquipmentDTO(equipmentInformationRepository.getEquipmentInformation());
    }*/

    /**
     * métodos aqui tem de variar entre private e public.
     * ou seja, o metodo acima de retornar os equipamentos tem de ser private primeiro
     * publicos sao os metodos que sao chamados pelo controller
     * funciona melhor em metodos que tenham detalhes como ID ou outras chaves
     */
}
