package com.DEVLOP.Web.Controllers.EquipmentConditionControllers;


import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Application.Queries.EquipmentConditionQuery;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipmentCondition")
@Tag(name ="Equipment Condition queries", description = "Endpoints for querying equipment condition data. Version 1")
public class EquipmentConditionQueryController {
    private final EquipmentConditionQuery equipmentConditionQuery;

    @Autowired
    public EquipmentConditionQueryController(EquipmentConditionQuery equipmentConditionQuery) {
        this.equipmentConditionQuery = equipmentConditionQuery;
    }
    @GetMapping("/{id}")
    @Async
    @Operation(
            summary = "Get EquipmentCondition by ID",
            description = "Retrieves a single EquipmentConditionDto using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentConditionDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentConditionDto>> getById(
            @PathVariable int id) {
        return equipmentConditionQuery.FindEquipmentConditionByID(id)
                .thenApply(ResponseEntity::ok);
    }
    @GetMapping("/code/{code}")
    @Async
    @Operation(
            summary = "Get EquipmentCondition by Code",
            description = "Retrieves a single EquipmentConditionDto using its code."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentConditionDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentConditionDto>> getByCode(
            @PathVariable String code) {
        return equipmentConditionQuery.FindEquipmentConditionByCode(code)
                .thenApply(ResponseEntity::ok);
    }
}
