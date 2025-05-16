package com.DEVLOP.Web.Controllers.EquipmentServiceControllers;

import com.DEVLOP.Application.Commands.EquipmentServiceCommand;
import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipmentService")
@Tag(name ="Equipment service commands", description = "Endpoints for commanding equipment service data. Version 1")
public class EquipmentServiceCommandController {
    private final EquipmentServiceCommand equipmentServiceCommand;

    @Autowired
    public EquipmentServiceCommandController(EquipmentServiceCommand equipmentServiceCommand) {
        this.equipmentServiceCommand = equipmentServiceCommand;
    }

    @PostMapping
    @Async
    @Operation(
            summary = "Create a new EquipmentService",
            description = "Creates and persists a new EquipmentService based on the provided DTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipment service created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentServiceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentServiceDto>> create(@RequestBody EquipmentServiceDto equipmentServiceDto) {
        return equipmentServiceCommand.CreateNewEquipmentService(equipmentServiceDto)
                .thenApply(ResponseEntity::ok); // Optionally: .thenApply(dto -> ResponseEntity.status(201).body(dto))
    }

    @PutMapping
    @Async
    @Operation(
            summary = "Update an existing EquipmentService",
            description = "Updates an existing EquipmentService with the new data provided."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment service updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentServiceDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment service not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentServiceDto>> update(@RequestBody EquipmentServiceDto equipmentServiceDto) {
        return equipmentServiceCommand.UpdateEquipmentService(equipmentServiceDto)
                .thenApply(ResponseEntity::ok);
    }
}
