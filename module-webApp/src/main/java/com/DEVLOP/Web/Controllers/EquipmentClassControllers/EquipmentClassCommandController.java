package com.DEVLOP.Web.Controllers.EquipmentClassControllers;


import com.DEVLOP.Application.Commands.EquipmentClassCommand;
import com.DEVLOP.Application.DTOS.EquipmentClassDto;
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
@RequestMapping("/v1/equipmentClass")
@Tag(name ="Equipment Class command", description = "Endpoints for commanding equipment class data. Version 1")
public class EquipmentClassCommandController {

    private final EquipmentClassCommand equipmentClassCommand;

    @Autowired
    public EquipmentClassCommandController(EquipmentClassCommand equipmentClassCommand) {
        this.equipmentClassCommand = equipmentClassCommand;
    }

    @PostMapping
    @Async
    @Operation(summary = "Create a new equipment class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment class created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentClassDto.class)))
    })
    public CompletableFuture<ResponseEntity<EquipmentClassDto>> create(@RequestBody EquipmentClassDto dto) {
        return equipmentClassCommand.CreateEquipmentClass(dto)
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping
    @Async
    @Operation(summary = "Update an existing equipment class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment class updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment class not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentClassDto>> update(@RequestBody EquipmentClassDto dto) {
        return equipmentClassCommand.UpdateEquipmentClass(dto)
                .thenApply(ResponseEntity::ok);
    }
}
