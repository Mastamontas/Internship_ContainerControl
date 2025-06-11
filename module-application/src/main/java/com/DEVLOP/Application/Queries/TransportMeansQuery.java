package com.DEVLOP.Application.Queries;


import com.DEVLOP.Application.DTOS.TransportMeansDto;
import com.DEVLOP.Application.Mappers.ITransportMeansMapper;
import com.DEVLOP.CustomExceptions.TransportMeansNotFoundException;
import com.DEVLOP.Entities.TransportMeans;
import com.DEVLOP.Repositories.TransportMeansRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class TransportMeansQuery {
    private final TransportMeansRepo transportMeansRepo;

    @Qualifier("ITransportMeansMapperImpl")
    private final ITransportMeansMapper mapper;

    @Autowired
    public TransportMeansQuery (TransportMeansRepo transportMeansRepo, @Qualifier("ITransportMeansMapperImpl") ITransportMeansMapper mapper){
        this.mapper = mapper;
        this.transportMeansRepo = transportMeansRepo;
    }

    @Transactional
    public CompletableFuture<TransportMeansDto> GetTransportMeansByID(int id){
        return CompletableFuture.supplyAsync(()->{
            TransportMeans transportMeans = transportMeansRepo.FindTransportMeansByID(id)
                    .orElseThrow(()-> new TransportMeansNotFoundException("No transport means with that ID"));
            return mapper.MapToTransportMeansDto(transportMeans);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<List<TransportMeansDto>> GetTransportMeansList(List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<TransportMeans> transportMeansList = transportMeansRepo.ReturnTransportMeansList(idList);
            return transportMeansList.stream().map(mapper::MapToTransportMeansDto).toList();
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
