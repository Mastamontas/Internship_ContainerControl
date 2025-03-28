package com.DEVLOP.Application.Queries;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.Queries.IEquipmentQueries;
import com.DEVLOP.Repositories.EquipmentRepository;
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
    private final EquipmentRepository equipmentRepository;
    private final IEquipmentMapper iEquipmentMapper;

    @Autowired
    private final EquipmentSpecification equipmentSpecification;

    @Autowired
    public EquipmentQuery(EquipmentRepository equipmentRepository, @Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper,
                          EquipmentSpecification equipmentSpecification){
        this.equipmentRepository = equipmentRepository;
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentSpecification = equipmentSpecification;

    }
    /*
    todo
    aplicar filtros nesta função
     */

    @Override
    @Transactional
    public CompletableFuture<List<@Valid EquipmentDto>> FindAllEquipmentsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ReturnMappedEquipDTOList();//remover função chamar a outra
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
    @Override
    @Transactional
    public CompletableFuture<List<EquipmentDto>> ReturnEquipmentsFilteredASync(Map<String,Object> filters){
        return CompletableFuture.supplyAsync(()->{
            Specification<Equipment> spec = equipmentSpecification.BuildSpecification(filters);
            List<Equipment> filteredEquipmentList = equipmentRepository.ReturnEquipmentListFiltered(spec);
            return filteredEquipmentList.stream().map(iEquipmentMapper::MaptoEquipmentDto).toList();
        });
    }
}
