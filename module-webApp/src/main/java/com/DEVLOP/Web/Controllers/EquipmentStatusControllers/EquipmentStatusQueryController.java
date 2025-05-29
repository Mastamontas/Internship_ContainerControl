package com.DEVLOP.Web.Controllers.EquipmentStatusControllers;

import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
import com.DEVLOP.Application.Queries.EquipmentStatusQuery;
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
@RequestMapping("/v1/equipmentStatus")
@Tag(name ="Equipment status queries", description = "Endpoints for querying equipment status data. Version 1")
public class EquipmentStatusQueryController {
    @Autowired
    private final EquipmentStatusQuery equipmentStatusQuery;

    public EquipmentStatusQueryController(EquipmentStatusQuery equipmentStatusQuery){
        this.equipmentStatusQuery = equipmentStatusQuery;
    }
    // GET by ID
    @GetMapping("/{id}")
    @Async
    @Operation(
            summary = "Get EquipmentStatus by ID",
            description = "Retrieves a single EquipmentStatusDto using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentStatusDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentStatusDto>> getById(@PathVariable int id) {
        return equipmentStatusQuery.GetEquipmentStatusByID(id)
                .thenApply(ResponseEntity::ok);
    }

    // GET by code
    @GetMapping("/code/{code}")
    @Async
    @Operation(
            summary = "Get EquipmentStatus by Code",
            description = "Retrieves a single EquipmentStatusDto using its code."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentStatusDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentStatusDto>> getByCode(@PathVariable String code) {
        return equipmentStatusQuery.GetEquipmentStatusByCode(code)
                .thenApply(ResponseEntity::ok);
    }
}
