package com.DEVLOP.Web.Controllers.EquipmentLeasingControllers;

import com.DEVLOP.Application.Commands.EquipmentLeasingCommand;
import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
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
@RequestMapping("/v1/equipmentLeasing")
@Tag(name ="Equipment leasing commands", description = "Endpoints for commanding equipment leasing data. Version 1")
public class EquipmentLeasingCommandController {
    private final EquipmentLeasingCommand equipmentLeasingCommand;

    @Autowired
    public EquipmentLeasingCommandController(EquipmentLeasingCommand equipmentLeasingCommand) {
        this.equipmentLeasingCommand = equipmentLeasingCommand;
    }

    @PostMapping
    @Async
    @Operation(
            summary = "Create a new EquipmentLeasing record",
            description = "Persists a new equipment leasing entry based on the provided DTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipment leasing created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentLeasingDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentLeasingDto>> CreateEquipmentLeasing(@RequestBody EquipmentLeasingDto dto) {
        return equipmentLeasingCommand.CreateEquipmentLeasing(dto)
                .thenApply(ResponseEntity::ok); // Or use status(201) if preferred
    }

    @PutMapping
    @Async
    @Operation(
            summary = "Update an existing EquipmentLeasing record",
            description = "Updates an existing equipment leasing entry with the new values provided in the DTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment leasing updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentLeasingDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment leasing not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentLeasingDto>> UpdateEquipmentLeasing(@RequestBody EquipmentLeasingDto dto) {
        return equipmentLeasingCommand.UpdateEquipmentLeasing(dto)
                .thenApply(ResponseEntity::ok);
    }
}
