package com.DEVLOP.Repositories;


import com.DEVLOP.Entities.TransportMeans;
import com.DEVLOP.Interfaces.IGenericRepository;
import com.DEVLOP.Interfaces.ITransportMeansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransportMeansRepo  {
    private final ITransportMeansRepo iTransportMeansRepo;
    @Autowired
    public TransportMeansRepo(ITransportMeansRepo iTransportMeansRepo){
        this.iTransportMeansRepo = iTransportMeansRepo;
    }
    public TransportMeans PersistTransportMeans(TransportMeans transpMeans){
        return iTransportMeansRepo.saveAndFlush(transpMeans);
    }
    public Optional<TransportMeans> FindTransportMeansByID(int id){
        return iTransportMeansRepo.findById(id);
    }

    public List<TransportMeans> ReturnTransportMeansList(List<Integer> idList) {
        return iTransportMeansRepo.findAllById(idList);
    }
}
