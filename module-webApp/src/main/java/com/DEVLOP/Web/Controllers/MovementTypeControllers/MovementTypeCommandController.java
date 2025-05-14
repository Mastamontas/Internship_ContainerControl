package com.DEVLOP.Web.Controllers.MovementTypeControllers;

import com.DEVLOP.Application.Commands.MovementTypeCommand;
import com.DEVLOP.Application.DTOS.MovementTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movementType")
@Tag(name ="Movement type commands", description = "Endpoints for controlling movement type data. Version 1")
public class MovementTypeCommandController {

    private final MovementTypeCommand movementTypeCommand;

    @Autowired
    public MovementTypeCommandController(MovementTypeCommand movementTypeCommand){
        this.movementTypeCommand = movementTypeCommand;
    }
    //create movement type
    @PostMapping("/create")
    @Async
    @Operation(
            summary = "Create a movement type entity",
            description = "Creates a new MovementType entity with the provided code, name, comment, and empty status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movement type created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovementTypeDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<MovementTypeDto>> createMovementType(
            @RequestBody MovementTypeDto movementTypeDto) {
        return movementTypeCommand.CreateMovementType(movementTypeDto)
                .thenApply(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @PutMapping("/update")
    @Async
    @Operation(
            summary = "Update an existing movement type entity",
            description = "Updates an existing MovementType entity based on the provided MovementTypeDto. The ID must refer to an existing entity."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement type updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovementTypeDto.class))),
            @ApiResponse(responseCode = "404", description = "Movement type not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<MovementTypeDto>> UpdateMovementType(
            @RequestBody MovementTypeDto movementTypeDto) {

        return movementTypeCommand.UpdateMovementType(movementTypeDto)
                .thenApply(updated -> ResponseEntity.ok(updated));
    }

}
