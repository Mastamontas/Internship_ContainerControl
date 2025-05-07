package com.DEVLOP.Web.Controllers.MovementControllers;

import com.DEVLOP.Application.Commands.MovementCommand;
import com.DEVLOP.Application.DTOS.MovementDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
todo: Rewrite better errors and responses to exceptions
 */
@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movements")
@Tag(name ="Movement commands", description = "Endpoints for controlling movement data. Version 1")
public class MovementCommandController {

    private final MovementCommand movementCommand;
    @Autowired
    public MovementCommandController(MovementCommand movementCommand){
        this.movementCommand = movementCommand;
    }

    @PutMapping("/{id}/update")
    @Async
    @Operation(
            summary = "Updates existing movement",
            description = "Receives movement dto, updates fields and persist new movement"
    )
    public CompletableFuture<ResponseEntity<String>> UpdateMovement(@PathVariable("id") int id, @Valid @RequestBody MovementDto movementDto){
        return movementCommand.UpdateMovementAsync(id, movementDto).
                thenApply(updatedMovement -> ResponseEntity.ok("Movement was updated"))
                .exceptionally(ex ->{
                    log.error("Error updating movement with id " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movement was not updated");
                });
    }
    @PatchMapping("/updateGroup")
    @Async
    @Operation(summary = "Updates group of movements. Requires set of id's and movementDto in the body. Returns updated movements selected",
    description = "Receives a list of selected movement ID's and updates the selected fields.")
    public CompletableFuture<ResponseEntity<List<MovementDto>>> ChangeGroupMovement(
            @Parameter(description = "Id's of movements to update") @RequestParam List<Integer> movementIDs,
            @RequestBody MovementDto newMovementData){
        return movementCommand.ChangeGroupMovement(movementIDs,newMovementData).thenApply(ResponseEntity::ok).exceptionally(
                ex -> {
                    log.error("Error doing group movement");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
                }
        );
    };

    @PostMapping("/addGroupMovement")
    @Async
    @Operation(summary = "Add a movement to a group of equipments. Receives a set of equipment id's and sets those equipments in the created movement",
    description = "This method is used to create a movement for a set of equipments")
    public CompletableFuture<ResponseEntity<List<MovementDto>>> AddGroupMovement(
            @Parameter(description = "Id's of equipments to add to the movement") @RequestParam List<Integer> equipmentIDs,
            @Parameter(description ="Movement information in which the equipment will be added") @RequestBody MovementDto movementToCreate){
        return movementCommand.AddMovementToEquipmentGroup(equipmentIDs, movementToCreate).thenApply(ResponseEntity::ok).exceptionally(ex ->{
            log.error("error adding group movement");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        });
    }
}
