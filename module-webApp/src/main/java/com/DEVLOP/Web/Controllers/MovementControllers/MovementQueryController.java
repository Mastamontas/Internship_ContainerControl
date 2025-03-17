package com.DEVLOP.Web.Controllers.MovementControllers;

import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Queries.MovementQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movements")
@Tag(name ="Movements", description = "Endpoints for querying movement data from equipments. Version 1")
public class MovementQueryController {
    public final MovementQuery movementQuery;

    @Autowired
    public MovementQueryController(MovementQuery movementQuery){
        this.movementQuery = movementQuery;
    }

    @GetMapping("/{id}")
    @Async
    @Operation(
            summary = "Returns a list of movements from an equipment id",
            description = "asynchronously returns a list of movements DTOs according to the ID of the input equipment"
    )
    public CompletableFuture<ResponseEntity<List<MovementDto>>> GetMovementsOfEquipment(@Parameter(description = "Id of equipment") @PathVariable("id") int id){
        return movementQuery.ReturnEquipmentMovementsAsync(id).thenApply(movementDtos -> ResponseEntity.ok(movementDtos))
                .exceptionally(
                ex ->{
                    log.error("could not retrieve movements for equipment with ID {} due to error {}",id, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        );
    }
}
