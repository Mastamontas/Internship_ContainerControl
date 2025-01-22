package com.DEVLOP.ContainerMovements.Application.Queries;

import com.DEVLOP.ContainerMovements.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentDTO;
import com.DEVLOP.ContainerMovements.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for returning all the equipment in the system
 */
@Service //tag para a camada de aplicação
public class EquipmentQuery {
    private final EquipmentRepository equipmentRepositoryImplementation;
    private final IEquipmentMapper iEquipmentMapper;

    @Autowired//annotation for automatic injection of required field
    public EquipmentQuery(EquipmentRepository equipmentRepositoryImplementation, @Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper){
        this.equipmentRepositoryImplementation = equipmentRepositoryImplementation;
        this.iEquipmentMapper = iEquipmentMapper;
    }

    /**
     * Returns all equipments from a repository or an equipment not found exception
     *
     * @return List of equipment entities
     */
    private List<Equipment> getAllEquipments() {
        //try and catch method
        List<Equipment> equipmentList = equipmentRepositoryImplementation.findAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
            return new ArrayList<Equipment>();
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
    private List<EquipmentDTO> getAllEquipmentDTO (List<Equipment> equipmentList){
        return equipmentList.stream().map(iEquipmentMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Public methods that aggregates both the return of equipment list and the maps to a DTO
     * returns the list of equipment DTO's or throws {@link EquipmentNotFoundException}
     *
     * @return list of equipment information DTO's
     */
    public List<EquipmentDTO> fetchAndMapEquipments(){
        //o que faz sentido aqui a nivel de performance? Ter o metodo nested na return call ou criar um objeto que é retornado?
            return getAllEquipmentDTO(getAllEquipments());
    }



    //retorna o equipamento por prefixo e envia para frontend
    private EquipmentDTO mapEquipmentToDTO(Equipment eq){
        //compensa me mais criar um objeto ou chamar a resposta só?
        return iEquipmentMapper.toDTO(eq);
    }
    //null error management
    private Equipment fetchEquipmentByPrefix (String prefix){
        return equipmentRepositoryImplementation.findByPrefix(prefix);
    }

    public EquipmentDTO getEqDTOByPrefix(String prefix){
        Equipment eq = fetchEquipmentByPrefix(prefix);
        return mapEquipmentToDTO(eq);
    }





    /*
    tem de poder editar o equipamento excepto campos bloqueados
    fazer lock no frontend ou no backend
     */
}
