package com.DEVLOP.Web.Controllers.MovementTypeControllers;


import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.Queries.MovementTypeQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movementType")
@Tag(name ="Movement type queries", description = "Endpoints for controlling movement type data. Version 1")
public class MovementTypeQueryController {

    private final MovementTypeQuery movementTypeQuery;
    public MovementTypeQueryController(MovementTypeQuery movementTypeQuery){
        this.movementTypeQuery = movementTypeQuery;
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Get MovementType by ID",
            description = "Retrieves a MovementTypeDto based on the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement type retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovementTypeDto.class))),
            @ApiResponse(responseCode = "404", description = "Movement type not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<MovementTypeDto>> GetById(
            @Parameter(description = "ID of the movement type", required = true)
            @PathVariable int id) {
        return movementTypeQuery.GetMovementTypeByIDAsync(id)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/code/{code}")
    @Operation(
            summary = "Get MovementType by Code",
            description = "Retrieves a MovementTypeDto based on the given movement type code."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement type retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovementTypeDto.class))),
            @ApiResponse(responseCode = "404", description = "Movement type not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<MovementTypeDto>> getByCode(
            @Parameter(description = "Movement type code", required = true)
            @PathVariable String code) {
        return movementTypeQuery.GetMovementTypeByCodeAsync(code)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/list")
    @Operation(
            summary = "Get list of MovementTypes by IDs",
            description = "Retrieves a list of MovementTypeDto objects based on a provided list of IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement type list retrieved successfully",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovementTypeDto.class)))),
            @ApiResponse(responseCode = "404", description = "No movement types found with those IDs", content = @Content)
    })
    public CompletableFuture<ResponseEntity<List<MovementTypeDto>>> getListByIds(
            @RequestBody List<Integer> idList) {
        return movementTypeQuery.GetMovementTypeListAsync(idList)
                .thenApply(ResponseEntity::ok);
    }
}
