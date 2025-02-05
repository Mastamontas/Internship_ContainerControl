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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
/*
TODO
Return equipment by unique details check digit, prefix, number. These 3 return the equipment
when returned, it can be updated. Check logic for updating equipment
create tests for the updated equipment
also allow for the returned equipment to query all movements (next feature Last movements)
clean classes and try to change name of async function

 */
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

    //returns assync equipment list DTO
    @Override
    public CompletableFuture<List<EquipmentDTO>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return returnMappedEquipDTOList();
            } catch (Exception ex) {
                return new ArrayList<>();
            }
        });
    }

    /**
     * Public methods that aggregates both the return of equipment list and the maps to a DTO
     * returns the list of equipment DTO's or throws {@link EquipmentNotFoundException}
     *
     * @return list of equipment information DTO's
     */
    private List<EquipmentDTO> returnMappedEquipDTOList(){
        return mapToEquipmentDTOList(fetchEquipmentListFromRepo());
    }

    /**
     * Maps a list of equipment entities to a list of equipment DTO's
     *
     * @param equipmentList
     * @return List of equipment DTO's
     */
    private List<EquipmentDTO> mapToEquipmentDTOList (List<Equipment> equipmentList){
        return equipmentList.stream().map(iEquipmentMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Returns all equipments from a repository or an equipment not found exception
     *
     * @return List of equipment entities
     */
    private List<Equipment> fetchEquipmentListFromRepo() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
            return new ArrayList<>();
        }
        return equipmentList;
    }

    /***************PARA FAZER REFACTOR/INCOMPLETO***************************/

    public CompletableFuture<EquipmentDTO> returnEqDTOByUniqueDetailsAsync(String prefix, int checkDigit, int number){
        return CompletableFuture.supplyAsync(()->{
            Equipment eq = fetchEquipmentByUniqueDetails(prefix, checkDigit, number);
            return iEquipmentMapper.toDTO(eq);
        });
    }

    //this function is redundant
    /*private EquipmentDTO mapEqToEqDTOByUniqueDetails(String prefix, int checkDigit, int number){
        Equipment eq = fetchEquipmentByUniqueDetails(prefix,checkDigit,number);
        return iEquipmentMapper.toDTO(eq);
    }*/


    private Equipment fetchEquipmentByUniqueDetails (String prefix, int checkDigit, int number){
        return equipmentRepository.findEquipmentByUniqueDetails(prefix, checkDigit, number).orElseThrow(() -> new EquipmentNotFoundException(
                String.format("No equipment found for details: Prefix=%s, CheckDigit=%d, Number=%d", prefix, checkDigit, number)
        ));
    }
}
