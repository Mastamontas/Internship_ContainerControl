package com.DEVLOP.Web.Controllers.EquipmentClassControllers;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Application.Queries.EquipmentClassQuery;
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
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipmentClass")
@Tag(name ="Equipment Class queries", description = "Endpoints for querying equipment class data. Version 1")
public class EquipmentClassQueryController {
    private final EquipmentClassQuery equipmentClassQuery;

    @Autowired
    public EquipmentClassQueryController(EquipmentClassQuery equipmentClassQuery) {
        this.equipmentClassQuery = equipmentClassQuery;
    }

    @GetMapping("/{id}")
    @Async
    @Operation(summary = "Get equipment class by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the equipment class",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment class not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<EquipmentClassDto>> getById(@PathVariable int id) {
        return equipmentClassQuery.GetEquipmentClassById(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    if (ex.getCause() instanceof NoSuchElementException) {
                        return ResponseEntity.notFound().build();
                    }
                    throw new CompletionException(ex);
                });
    }

    @GetMapping("/code/{code}")
    @Async
    @Operation(summary = "Get equipment class by code")
    public CompletableFuture<ResponseEntity<EquipmentClassDto>> getByCode(@PathVariable String code) {
        return equipmentClassQuery.GetEquipmentByCode(code)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/batch")
    @Async
    @Operation(summary = "Get equipment classes by list of IDs")
    public CompletableFuture<ResponseEntity<List<EquipmentClassDto>>> getByIdList(@RequestBody List<Integer> ids) {
        return equipmentClassQuery.GetEquipmentClassByIdList(ids)
                .thenApply(ResponseEntity::ok);
    }
}
