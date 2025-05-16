package com.DEVLOP.Web.Controllers.EquipmentServiceControllers;

import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import com.DEVLOP.Application.Queries.EquipmentServiceQuery;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipmentService")
@Tag(name ="Equipment service queries", description = "Endpoints for querying equipment service data. Version 1")
public class EquipmentServiceQueryController {
    @Autowired
    private final EquipmentServiceQuery equipmentServiceQuery;

    public EquipmentServiceQueryController(EquipmentServiceQuery equipmentServiceQuery) {
        this.equipmentServiceQuery = equipmentServiceQuery;
    }
    @GetMapping("/{id}")
    @Async
    @Operation(
            summary = "Get EquipmentService by ID",
            description = "Fetches a single EquipmentService by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the equipment service",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentServiceDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment service not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentServiceDto>> getById(@PathVariable int id) {
        return equipmentServiceQuery.GetEquipmentServiceByID(id)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/code/{code}")
    @Async
    @Operation(
            summary = "Get EquipmentService by code",
            description = "Fetches an EquipmentService by its unique code."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the equipment service",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentServiceDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment service not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentServiceDto>> getByCode(@PathVariable String code) {
        return equipmentServiceQuery.GetEquipmentServiceByCode(code)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/batch")
    @Async
    @Operation(
            summary = "Get multiple EquipmentServices by a list of IDs",
            description = "Returns a list of EquipmentServices given a list of IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List fetched successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentServiceDto.class))),
            @ApiResponse(responseCode = "404", description = "Some or all EquipmentServices not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<List<EquipmentServiceDto>>> getByListOfIds(@RequestBody List<Integer> ids) {
        return equipmentServiceQuery.GetEquipmentServiceByListOfID(ids)
                .thenApply(ResponseEntity::ok);
    }
}
