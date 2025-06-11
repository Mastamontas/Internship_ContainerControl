package com.DEVLOP.Application.Commands;


import com.DEVLOP.Application.DTOS.TransportMeansDto;
import com.DEVLOP.Application.Mappers.ITransportMeansMapper;
import com.DEVLOP.CustomExceptions.TransportMeansNotFoundException;
import com.DEVLOP.Entities.TransportMeans;
import com.DEVLOP.Repositories.TransportMeansRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class TransportMeansCommand {
    private final TransportMeansRepo transportMeansRepo;

    @Qualifier("ITransportMeansMapperImpl")
    private final ITransportMeansMapper mapper;

    @Autowired
    public TransportMeansCommand(TransportMeansRepo transportMeansRepo, @Qualifier("ITransportMeansMapperImpl") ITransportMeansMapper mapper){
        this.mapper = mapper;
        this.transportMeansRepo = transportMeansRepo;
    }

    @Transactional
    public CompletableFuture<TransportMeansDto> CreateTransportMeans(TransportMeansDto transportMeansDto){
        return CompletableFuture.supplyAsync(()->{
            TransportMeans transportMeans = mapper.MapToTransportMeans(transportMeansDto);
            transportMeansRepo.PersistTransportMeans(transportMeans);
            return transportMeansDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<TransportMeansDto> UpdateTransportMeans(TransportMeansDto transportMeansDto){
        return CompletableFuture.supplyAsync(()->{
            TransportMeans transportMeans = transportMeansRepo.FindTransportMeansByID(transportMeansDto.getId())
                    .orElseThrow(()-> new TransportMeansNotFoundException("No transport means with that ID"));
            transportMeansRepo.PersistTransportMeans(mapper.UpdateTransportMeans(transportMeansDto,transportMeans));
            return transportMeansDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

}
