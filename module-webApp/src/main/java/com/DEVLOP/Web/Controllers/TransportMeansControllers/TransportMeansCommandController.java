package com.DEVLOP.Web.Controllers.TransportMeansControllers;

import com.DEVLOP.Application.Commands.TransportMeansCommand;
import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.DTOS.TransportMeansDto;
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
@RequestMapping("/v1/transportMeans")
@Tag(name ="transport means commands", description = "Endpoints for controlling transport means data. Version 1")
public class TransportMeansCommandController {

    private final TransportMeansCommand transportMeansCommand;

    @Autowired
    public TransportMeansCommandController (TransportMeansCommand transportMeansCommand){
        this.transportMeansCommand = transportMeansCommand;
    }

    @PostMapping
    @Async
    @Operation(summary = "Create new transport means entity", description = "Create a new transport means entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movement type created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransportMeansDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<TransportMeansDto>> CreateTransportMeans(@RequestBody TransportMeansDto transportMeansDto){
        return transportMeansCommand.CreateTransportMeans(transportMeansDto).thenApply(ResponseEntity::ok);
    }

    @PutMapping
    @Async
    @Operation(summary = "Updates existing transport means entity", description = "Create a new transport means entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movement type created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransportMeansDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CompletableFuture<ResponseEntity<TransportMeansDto>> UpdateTransportMeans(@RequestBody TransportMeansDto transportMeansDto){
        return transportMeansCommand.UpdateTransportMeans(transportMeansDto).thenApply(ResponseEntity::ok);
    }
}
