package com.DEVLOP.Web.Controllers.MovementControllers;

import com.DEVLOP.Application.Commands.MovementCommand;
import com.DEVLOP.Application.DTOS.MovementDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movements")
@Tag(name ="Equipment", description = "Endpoints for controlling movement data. Version 1")
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
}
