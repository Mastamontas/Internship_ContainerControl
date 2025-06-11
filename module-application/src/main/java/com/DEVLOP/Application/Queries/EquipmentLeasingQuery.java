    package com.DEVLOP.Application.Queries;

    import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
    import com.DEVLOP.Application.Mappers.IEquipmentLeasingMapper;
    import com.DEVLOP.CustomExceptions.EquipmentLeasingNotFoundException;
    import com.DEVLOP.Entities.EquipmentLeasing;
    import com.DEVLOP.Repositories.EquipmentLeasingRepo;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.concurrent.CompletableFuture;
    import java.util.concurrent.CompletionException;

    @Service
    public class EquipmentLeasingQuery {

        private final EquipmentLeasingRepo equipmentLeasingRepo;
        @Qualifier("iEquipmentLeasingMapperImpl")
        private final IEquipmentLeasingMapper mapper;

        public EquipmentLeasingQuery (EquipmentLeasingRepo equipmentLeasingRepo, @Qualifier("IEquipmentLeasingMapperImpl") IEquipmentLeasingMapper mapper){
            this.equipmentLeasingRepo = equipmentLeasingRepo;
            this.mapper = mapper;
        }

        @Transactional
        public CompletableFuture<EquipmentLeasingDto> GetEquipmentLeasingByID (int id){
            return CompletableFuture.supplyAsync(()->{
                EquipmentLeasing equipmentLeasing = equipmentLeasingRepo.ReturnEquipmentLeasingByID(id)
                        .orElseThrow(()-> new EquipmentLeasingNotFoundException("Equipment leasing with that ID does not exist"));
                return mapper.MapToEquipmentLeasingDto(equipmentLeasing);
            }).exceptionally(ex ->{
               throw new CompletionException(ex);
            });
        }

        @Transactional
        public CompletableFuture<EquipmentLeasingDto> GetEquipmentLeasingByCode(String code){
            return CompletableFuture.supplyAsync(()->{
                EquipmentLeasing equipmentLeasing = equipmentLeasingRepo.ReturnEquipmentLeasingByCode(code)
                        .orElseThrow(()-> new EquipmentLeasingNotFoundException("Equipment leasing with that code does not exist"));
                return mapper.MapToEquipmentLeasingDto(equipmentLeasing);
            }).exceptionally(ex ->{
                throw new CompletionException(ex);
            });
        }

        @Transactional
        public CompletableFuture<List<EquipmentLeasingDto>> GetEquipmentLeasingByIdList(List<Integer> idList){
            return CompletableFuture.supplyAsync(()->{
                List<EquipmentLeasing> equipmentLeasingList = equipmentLeasingRepo.ReturnListOfEquipmentLeasing(idList);
                return equipmentLeasingList.stream().map(mapper::MapToEquipmentLeasingDto).toList();
            }).exceptionally(ex ->{
                throw new CompletionException(ex);
            });
        }

        //todo: make this method for all classes
        @Transactional
        public CompletableFuture<List<EquipmentLeasingDto>> GetAllEquipmentLeasing(){
            return CompletableFuture.supplyAsync(()->{
                List<EquipmentLeasing> equipmentLeasingList = equipmentLeasingRepo.FindAll();
                return equipmentLeasingList.stream().map(mapper::MapToEquipmentLeasingDto).toList();
            }).exceptionally(ex-> {
                throw new CompletionException(ex);
            });
        }


    }
