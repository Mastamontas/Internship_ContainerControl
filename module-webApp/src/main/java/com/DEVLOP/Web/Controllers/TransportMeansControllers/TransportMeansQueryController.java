package com.DEVLOP.Web.Controllers.TransportMeansControllers;

import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.DTOS.TransportMeansDto;
import com.DEVLOP.Application.Queries.TransportMeansQuery;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/transportMeans")
@Tag(name ="transport means queries", description = "Endpoints for querying transport means data. Version 1")
public class TransportMeansQueryController {

    private final TransportMeansQuery transportMeansQuery;

    public TransportMeansQueryController(TransportMeansQuery transportMeansQuery){
        this.transportMeansQuery = transportMeansQuery;
    }

    @Async
    @GetMapping("/{id}")
    @Operation(
            summary = "Get transport means by ID",
            description = "Retrieves a transport means dto based on the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movement type retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransportMeansDto.class))),
            @ApiResponse(responseCode = "404", description = "Transport means not found", content = @Content)
    })
    public CompletableFuture<ResponseEntity<TransportMeansDto>> GetTransportMeansByID(
            @Parameter(description = "ID of the transport means", required = true)
            @PathVariable int id){
        return transportMeansQuery.GetTransportMeansByID(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping("/list")
    @Operation(
            summary = "Get list of MovementTypes by IDs",
            description = "Retrieves a list of MovementTypeDto objects based on a provided list of IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport means list retrieved successfully",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransportMeansDto.class)))),
            @ApiResponse(responseCode = "404", description = "No transport means found with those IDs", content = @Content)
    })
    public CompletableFuture<ResponseEntity<List<TransportMeansDto>>> getTransportMeansListByIds(
            @RequestBody List<Integer> idList) {
        return transportMeansQuery.GetTransportMeansList(idList)
                .thenApply(ResponseEntity::ok);
    }


}
