package com.DEVLOP.Web.Controllers.EquipmentTypeControllers;

import com.DEVLOP.Application.Commands.EquipmentTypeCommand;
import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import com.DEVLOP.Application.Queries.EquipmentTypeQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.exec.ExecutionException;
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
@RequestMapping("/v1/equipmentType")
@Tag(name ="Equipment type query", description = "Endpoints for querying equipment type data. Version 1")
public class EquipmentTypeQueryController {

    @Autowired
    private final EquipmentTypeQuery equipmentTypeQuery;

    public EquipmentTypeQueryController(EquipmentTypeQuery equipmentTypeQuery){
        this.equipmentTypeQuery = equipmentTypeQuery;
    }

    @Async
    @Operation(summary = "Get Equipment Type by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment type found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentTypeDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipment type not found", content = @Content)
    })
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<EquipmentTypeDto>> GetById(@PathVariable int id) {
        return equipmentTypeQuery.GetEquipmentTypeByID(id).thenApply(ResponseEntity::ok);
    }

    @Async
    @Operation(summary = "Get list of Equipment Types by ID list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of equipment types retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentTypeDto.class)))
    })
    @PostMapping("/list")
    public CompletableFuture<ResponseEntity<List<EquipmentTypeDto>>> GetByIdList(@RequestBody List<Integer> idList) throws ExecutionException, InterruptedException {
        return equipmentTypeQuery.ReturnListOfEquipmentTypes(idList).thenApply(ResponseEntity::ok);
    }

    @Async
    @Operation(summary = "Get all Equipment Types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All equipment types retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipmentTypeDto.class)))
    })


    @GetMapping
    public CompletableFuture<ResponseEntity<List<EquipmentTypeDto>>> GetAll() {
        return equipmentTypeQuery.ReturnAllEquipmentTypes().thenApply(ResponseEntity::ok);
    }
    //todo get equipment types by id of equipment class

}
