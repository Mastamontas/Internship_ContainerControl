package com.DEVLOP.Application.Queries;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.Queries.IEquipmentQueries;
import com.DEVLOP.Repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
Change method naming to Pascal Case
comment functions
verificar se estrutura find all esta correta
 */

/**
 * Class for returning all the equipment in the system
 */
@Service
public class EquipmentQuery implements IEquipmentQueries {
    private final EquipmentRepository equipmentRepository;
    private final IEquipmentMapper iEquipmentMapper;

    @Autowired
    public EquipmentQuery(EquipmentRepository equipmentRepository, @Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper){
        this.equipmentRepository = equipmentRepository;
        this.iEquipmentMapper = iEquipmentMapper;

    }
    /*
    todo
    ver se esta função é melhor ser uma page
     */

    @Override
    @Transactional
    public CompletableFuture<List<@Valid EquipmentDto>> FindAllEquipmentsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ReturnMappedEquipDTOList();
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
    private List<EquipmentDto> ReturnMappedEquipDTOList(){
        return MapToEquipmentDTOList(FetchEquipmentListFromRepo());
    }

    /**
     * Maps a list of equipment entities to a list of equipment DTO's
     *
     * @param equipmentList
     * @return List of equipment DTO's
     */
    private List<EquipmentDto> MapToEquipmentDTOList(List<Equipment> equipmentList){
        return equipmentList.stream().map(iEquipmentMapper::MaptoEquipmentDto).collect(Collectors.toList());
    }

    /**
     * Returns all equipments from a repository or an equipment not found exception
     *
     * @return List of equipment entities
     */
    private List<Equipment> FetchEquipmentListFromRepo() {
        List<Equipment> equipmentList = equipmentRepository.FindAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
            return new ArrayList<>();
        }
        return equipmentList;
    }


    /*
    confirmar com filipe se isto é boa pratica, tem mapper e fetch equipment nested
    alternativa é chamar so uma função que faca o mapping automatico
     */
    @Override
    @Transactional
    public CompletableFuture<EquipmentDto> GetEquipmentByIDAsync(int id){
        return CompletableFuture.supplyAsync(()-> iEquipmentMapper.MaptoEquipmentDto(FetchEquipmentByID(id)));
    }


    private Equipment FetchEquipmentByID(int id){
        Optional<Equipment> optionalEquipment = equipmentRepository.FindByID(id);
        if (optionalEquipment.isPresent()) {
            return optionalEquipment.get();
        } else {
            throw new EquipmentNotFoundException("No equipment found with ID " + id);
        }
    }

    /*
    todo
    this method will have dynamic query - return partial results according to user input
     *//*
    public CompletableFuture<EquipmentDTO> ReturnEqDTOByUniqueDetailsAsync(String prefix, int checkDigit, int number){
        return CompletableFuture.supplyAsync(()->{
            Equipment eq = FetchEquipmentByUniqueDetails(prefix, checkDigit, number);
            return iEquipmentMapper.MaptoEquipmentDto(eq);
        });
    }*/
    /*
    todo
    completar proxima branch
     */
    /*private Equipment FetchEquipmentByUniqueDetails(String prefix, int checkDigit, int number){
        return equipmentRepository.FindEquipmentByUniqueDetails(prefix, checkDigit, number).orElseThrow(() -> new EquipmentNotFoundException(
                String.format("No equipment found for details: Prefix=%s, CheckDigit=%d, Number=%d", prefix, checkDigit, number)
        ));
    }*/
}
