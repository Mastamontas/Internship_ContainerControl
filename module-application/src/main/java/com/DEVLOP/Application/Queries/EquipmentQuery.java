package com.DEVLOP.Application.Queries;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Interfaces.Queries.IEquipmentQueries;
import com.DEVLOP.Repositories.EquipmentRepo;
import com.DEVLOP.Specifications.EquipmentSpecification;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Class for returning all the equipment in the system
 */
@Service
public class EquipmentQuery implements IEquipmentQueries {
    private final EquipmentRepo equipmentRepo;
    private final IEquipmentMapper iEquipmentMapper;

    @Autowired
    private final EquipmentSpecification equipmentSpecification;

    @Autowired
    public EquipmentQuery(EquipmentRepo equipmentRepo, @Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper,
                          EquipmentSpecification equipmentSpecification){
        this.equipmentRepo = equipmentRepo;
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentSpecification = equipmentSpecification;

    }


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
    /*
    funciona mas é redundante, fazer refactor
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
    private List<EquipmentDto> MapToEquipmentDTOList(List<com.DEVLOP.Entities.Equipment> equipmentList){
        return equipmentList.stream().map(iEquipmentMapper::MaptoEquipmentDto).collect(Collectors.toList());
    }

    /**
     * Returns all equipments from a repository or an equipment not found exception
     *
     * @return List of equipment entities
     */
    private List<com.DEVLOP.Entities.Equipment> FetchEquipmentListFromRepo() {
        List<com.DEVLOP.Entities.Equipment> equipmentList = equipmentRepo.FindAll();
        if (equipmentList == null || equipmentList.isEmpty()) {
            return new ArrayList<>();
        }
        return equipmentList;
    }

    @Override
    @Transactional
    public CompletableFuture<EquipmentDto> GetEquipmentByIDAsync(int id){
        return CompletableFuture.supplyAsync(()-> iEquipmentMapper.MaptoEquipmentDto(FetchEquipmentByID(id)));
    }

    private com.DEVLOP.Entities.Equipment FetchEquipmentByID(int id){
        Optional<com.DEVLOP.Entities.Equipment> optionalEquipment = equipmentRepo.FindByID(id);
        if (optionalEquipment.isPresent()) {
            return optionalEquipment.get();
        } else {
            throw new EquipmentNotFoundException("No equipment found with ID " + id);
        }
    }
    @Override
    @Transactional
    public CompletableFuture<List<EquipmentDto>> ReturnEquipmentsFilteredASync(Map<String,Object> filters){
        return CompletableFuture.supplyAsync(()->{
            Specification<com.DEVLOP.Entities.Equipment> spec = equipmentSpecification.BuildSpecification(filters);
            List<com.DEVLOP.Entities.Equipment> filteredEquipmentList = equipmentRepo.ReturnEquipmentListFiltered(spec);
            return filteredEquipmentList.stream().map(iEquipmentMapper::MaptoEquipmentDto).toList();
        });
    }
}
