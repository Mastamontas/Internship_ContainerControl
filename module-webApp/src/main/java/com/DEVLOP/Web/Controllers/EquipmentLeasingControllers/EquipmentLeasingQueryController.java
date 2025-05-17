package com.DEVLOP.Web.Controllers.EquipmentLeasingControllers;

import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Application.Queries.EquipmentLeasingQuery;
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
@RequestMapping("/v1/equipmentLeasing")
@Tag(name ="Equipment leasing queries", description = "Endpoints for querying equipment leasing data. Version 1")
public class EquipmentLeasingQueryController {

    private final EquipmentLeasingQuery equipmentLeasingQuery;

    @Autowired
    public EquipmentLeasingQueryController(EquipmentLeasingQuery equipmentLeasingQuery) {
        this.equipmentLeasingQuery = equipmentLeasingQuery;
    }

    @GetMapping("/{id}")
    @Async
    @Operation(summary = "Get equipment leasing by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment leasing found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentLeasingDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment leasing not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentLeasingDto>> GetEquipmentLeasingById(@PathVariable int id) {
        return equipmentLeasingQuery.GetEquipmentLeasingByID(id)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/code/{code}")
    @Async
    @Operation(summary = "Get equipment leasing by code")
    public CompletableFuture<ResponseEntity<EquipmentLeasingDto>> GetEquipmentLeasingByCode(@PathVariable String code) {
        return equipmentLeasingQuery.GetEquipmentLeasingByCode(code)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/list")
    @Async
    @Operation(summary = "Get equipment leasing by a list of IDs")
    public CompletableFuture<ResponseEntity<List<EquipmentLeasingDto>>> GetEquipmentLeasingByIDList(@RequestBody List<Integer> ids) {
        return equipmentLeasingQuery.GetEquipmentLeasingByIdList(ids)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping
    @Async
    @Operation(summary = "Get all equipment leasing entries")
    public CompletableFuture<ResponseEntity<List<EquipmentLeasingDto>>> GetAllEquipmentLeasing() {
        return equipmentLeasingQuery.GetAllEquipmentLeasing()
                .thenApply(ResponseEntity::ok);
    }
}
