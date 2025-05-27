package com.DEVLOP.Web.Controllers.EquipmentStatusControllers;

import com.DEVLOP.Application.Commands.EquipmentStatusCommand;
import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
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
@RequestMapping("/v1/equipmentStatus")
@Tag(name ="Equipment status commands", description = "Endpoints for commanding equipment status data. Version 1")
public class EquipmentStatusCommandController {

    private final EquipmentStatusCommand equipmentStatusCommand;

    @Autowired
    public EquipmentStatusCommandController(EquipmentStatusCommand equipmentStatusCommand) {
        this.equipmentStatusCommand = equipmentStatusCommand;
    }

    // Create EquipmentStatus
    @PostMapping
    @Async
    @Operation(
            summary = "Create a new EquipmentStatus",
            description = "Creates a new EquipmentStatus entity based on the provided DTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentStatusDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentStatusDto>> create(
            @RequestBody EquipmentStatusDto dto) {
        return equipmentStatusCommand.CreateEquipmentStatus(dto)
                .thenApply(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    // Update EquipmentStatus
    @PutMapping
    @Async
    @Operation(
            summary = "Update an existing EquipmentStatus",
            description = "Updates an existing EquipmentStatus entity using the provided DTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentStatusDto.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentStatusDto>> update(
            @RequestBody EquipmentStatusDto dto) {
        return equipmentStatusCommand.UpdateEquipmentStatus(dto)
                .thenApply(ResponseEntity::ok);
    }
}
