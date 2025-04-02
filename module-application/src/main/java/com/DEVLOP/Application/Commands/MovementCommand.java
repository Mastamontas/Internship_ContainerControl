package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Commands.IMovementCommands;
import com.DEVLOP.Repositories.MovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class MovementCommand implements IMovementCommands {



    /*
    why is field injection not recommended here (autowired recommendation) for mapper
     */
    private final IMovementMapper mapper;
    private final MovementRepository movementRepository;


    @Autowired
    public MovementCommand(@Qualifier("IMovementMapperImpl") IMovementMapper mapper, MovementRepository movementRepository){
        this.mapper = mapper;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<Movement> UpdateMovementAsync(int id, MovementDto movementDto){
        return CompletableFuture.supplyAsync(()->{
            Movement move = movementRepository.FindMovementById(id).orElseThrow(()-> new MovementNotFoundException("Movement with that id " +
                    "does not exist"));
            return mapper.UpdateMovementEntity(movementDto, move);
        }).thenApplyAsync(updatedMovement ->{
            movementRepository.UpdateMovement(updatedMovement);
            return updatedMovement;
        });
    }

    /*
    add movements to an equipment
    recebe lista de equipamentos
    converte movement dto para movement
    adiciona-lhe o equipamento
    parametros de filtragem do dto sao todos os outros campos disponiveis para serem alterados
    recebe o mesmo dto e altera a informação para aqueles ids de movimento
    precisa de um array de ids de movimento - para cada elemento, faz o mapeamento do DTO para entidade -

     */

    //todo fazer update a este metodo que nao tenho a certeza se funciona
    /*
    nao preocupar com o movimento porque mantem se nos equipamentos selecionados.

     */
    @Transactional
    //technically, everything that is managed within the transactional scope is updated by hibernate so you dont need to call the update method from the repository
    //todo: research further into @Modifying commands and commands managed by hibernate
    //this method updates groups of movements with specific fields
    //todo: test with and without the call for the update movement
    public CompletableFuture<List<MovementDto>> ChangeGroupMovement(List<Integer> movementIDs, MovementDto movementToChange){
        return CompletableFuture.supplyAsync(()-> {
            //have to fetch the movements from database somewhere
            List<Movement> movementList = GetSelectedMovements(movementIDs);
            List<Movement> updatedMovementList = movementList.stream().map(mov -> mapper.UpdateMovementEntity(movementToChange, mov)).toList();
            movementRepository.SaveMovementList(updatedMovementList);
            return updatedMovementList.stream().map(mapper::MapToMovementDto).toList();
        });
    };

    //todo falta add group movement
    //recebe lista de movim


    //preciso função para mapear lista de dtos para lista de movimentos
    //função select movements que retorna uma lista de movimentos
    //recebe list de ints
    //we search the movements by the filter
    private List<Movement> GetSelectedMovements(List<Integer> ids){
        return movementRepository.ReturnMovementsByIDList(ids);
    }
}
