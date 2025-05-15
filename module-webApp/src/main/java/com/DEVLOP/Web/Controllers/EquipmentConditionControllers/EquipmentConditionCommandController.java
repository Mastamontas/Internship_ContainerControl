package com.DEVLOP.Web.Controllers.EquipmentConditionControllers;


import com.DEVLOP.Application.Commands.EquipmentConditionCommand;
import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/equipmentCondition")
@Tag(name ="Equipment Condition commands", description = "Endpoints for commanding equipment condition data. Version 1")
public class EquipmentConditionCommandController {

    private final EquipmentConditionCommand equipmentConditionCommand;
    @Autowired
    public EquipmentConditionCommandController(EquipmentConditionCommand equipmentConditionCommand) {
        this.equipmentConditionCommand = equipmentConditionCommand;
    }
    // CREATE
    @PostMapping("/create")
    @Async
    @Operation(
            summary = "Create a new EquipmentCondition",
            description = "Creates a new EquipmentCondition entity based on the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentConditionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentConditionDto>> CreateEquipmentCondition(
            @Valid @RequestBody EquipmentConditionDto equipmentConditionDto) {
        return equipmentConditionCommand.CreateNewEquipmentCondition(equipmentConditionDto)
                .thenApply(created -> ResponseEntity.status(HttpStatus.CREATED).body(created));
    }

    // UPDATE
    @PutMapping("/update")
    @Async
    @Operation(
            summary = "Update an EquipmentCondition",
            description = "Updates an existing EquipmentCondition entity based on ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentConditionDto.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentConditionDto>> UpdateEquipmentCondition(
            @PathVariable int id,
            @Valid @RequestBody EquipmentConditionDto equipmentConditionDto) {
        return equipmentConditionCommand.UpdateEquipmentCondition(equipmentConditionDto)
                .thenApply(updated -> ResponseEntity.ok().body(updated));
    }
}
