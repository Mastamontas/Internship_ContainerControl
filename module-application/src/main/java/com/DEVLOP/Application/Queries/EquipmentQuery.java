package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IQueries;
import com.DEVLOP.Repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * Class for returning all the equipment in the system
 */
@Service
public class EquipmentQuery implements IQueries<EquipmentDTO> {
    private final EquipmentRepository equipmentRepository;
    private final IEquipmentMapper iEquipmentMapper;

    @Autowired
    public EquipmentQuery(EquipmentRepository equipmentRepository, @Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper){
        this.equipmentRepository = equipmentRepository;
        this.iEquipmentMapper = iEquipmentMapper;
    }

    /**
     * Returns all equipments from a repository or an equipment not found exception
     *
     * @return List of equipment entities
     */
    private List<Equipment> getAllEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
            return new ArrayList<>();
        }
        return equipmentList;
    }

    @Override
    public CompletableFuture<List<EquipmentDTO>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("entered here");
                return fetchAndMapEquipments();
            } catch (Exception ex) {
                System.err.println("Error occurred while fetching equipment: " + ex.getMessage());
                return new ArrayList<>();
            }
        });
    }

    /**
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
        return getAllEquipmentDTO(getAllEquipments());
    }




    private EquipmentDTO mapEquipmentToDTO(Equipment eq){
        return iEquipmentMapper.toDTO(eq);
    }

    private Equipment fetchEquipmentByPrefix (String prefix){
        return equipmentRepository.findByPrefix(prefix);
    }

    //refactor nome para getEquipmentDTOByEquipmentPrefix
    public EquipmentDTO getEqDTOByPrefix(String prefix){
        Equipment eq = fetchEquipmentByPrefix(prefix);
        return mapEquipmentToDTO(eq);
    }

}
