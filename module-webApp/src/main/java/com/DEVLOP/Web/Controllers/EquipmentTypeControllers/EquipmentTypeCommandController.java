package com.DEVLOP.Web.Controllers.EquipmentTypeControllers;

import com.DEVLOP.Application.Commands.EquipmentTypeCommand;
import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.source.spi.IdentifierSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipmentType")
@Tag(name ="Equipment type command", description = "Endpoints for commanding equipment type data. Version 1")
public class EquipmentTypeCommandController {
    @Autowired
    private final EquipmentTypeCommand equipmentTypeCommand;

    public EquipmentTypeCommandController (EquipmentTypeCommand equipmentTypeCommand){
        this.equipmentTypeCommand = equipmentTypeCommand;
    }

    @PostMapping
    @Async
    @Operation(summary = "create new equipment type")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "equipment type created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentTypeDto.class))),
            @ApiResponse(responseCode = "400", description = "equipment type was not created", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentTypeDto>> CreateEquipmentType (@RequestBody EquipmentTypeDto equipmentTypeDto){
        return equipmentTypeCommand.CreateEquipmentType(equipmentTypeDto).thenApply(ResponseEntity::ok);
    }

    @PutMapping
    @Async
    @Operation(summary = "Update existing equipment type")
    @ApiResponses(value ={
            @ApiResponse(responseCode ="200", description = "equipment type updated sucessfully", content = @Content(mediaType ="application/json",
                    schema = @Schema(implementation = EquipmentTypeDto.class ))),
            @ApiResponse(responseCode = "400", description = "equipment type not updated", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentTypeDto>> UpdateEquipmentType(@RequestBody EquipmentTypeDto equipmentTypeDto){
        return equipmentTypeCommand.UpdateEquipmentType(equipmentTypeDto).thenApply(ResponseEntity::ok);
    }

}
