package com.DEVLOP.Web.Controllers.MovementControllers;

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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movements")
@Tag(name ="Movements", description = "Endpoints for querying movement data from movements. Version 1")
public class MovementQueryController {
    public final MovementQuery movementQuery;

    @Autowired
    public MovementQueryController(MovementQuery movementQuery){
        this.movementQuery = movementQuery;
    }

    @GetMapping("/equipment/{id}")
    @Async
    @Operation(
            summary = "Returns a list of movements from an equipment id",
            description = "asynchronously returns a list of movements DTOs according to the ID of the input equipment"
    )
    public CompletableFuture<ResponseEntity<List<MovementDto>>> GetMovementsOfEquipment(@Parameter(description = "Id of equipment") @PathVariable("id") int id){
        return movementQuery.ReturnMovementListFromEquipAsync(id).thenApply(movementDtos -> ResponseEntity.ok(movementDtos))
                .exceptionally(
                ex ->{
                    log.error("could not retrieve movements for equipment with ID {} due to error {}",id, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        );
    }

    @GetMapping("/filter")
    @Async
    @Operation(
            summary = "Returns filtered list of movements",
            description = "Asynchronously returns a list of filtered movements according to the selected filters"
    )
    public CompletableFuture<ResponseEntity<List<MovementDto>>> ReturnFilteredMovementList(
            @Parameter(description = "Equipment Prefix of the movement") @RequestParam(required = false) String prefix,
            @Parameter(description = "Equipment number") @RequestParam(required = false) Integer number,
            @Parameter(description = "Equipment type") @RequestParam(required = false) String equipmentType,
            @Parameter(description = "Length of the equipment") @RequestParam(required = false) Integer length,
            @Parameter(description = "Status of the movement") @RequestParam(required = false) String status,
            @Parameter(description = "Comments on the movement") @RequestParam(required = false) String comments,
            @Parameter(description = "Type of movement") @RequestParam(required = false) String movement,
            @Parameter(description = "Shipping line") @RequestParam(required = false) String line,
            @Parameter(description = "Vessel name") @RequestParam(required = false) String vessel,
            @Parameter(description = "Voyage number") @RequestParam(required = false) String voyage,
            @Parameter(description = "Origin location") @RequestParam(required = false) String from,
            @Parameter(description = "Destination location") @RequestParam(required = false) String to,
            @Parameter(description = "Final destination") @RequestParam(required = false) String finalDestination,
            @Parameter(description = "Creation date (YYYY-MM-DD)") @RequestParam(required = false) String createDate,
            @Parameter(description = "Notification code") @RequestParam(required = false) String notifyCode,
            @Parameter(description = "Agent code") @RequestParam(required = false) String agentCode
    ){
        Map<String, Object> filters = new HashMap<>();
        if (prefix != null) filters.put("prefix", prefix);
        if (number != null) filters.put("number", number);
        if (equipmentType != null) filters.put("equipmentType", equipmentType);
        if (length != null) filters.put("length", length);
        if (status != null) filters.put("status", status);
        if (comments != null) filters.put("comments", comments);
        if (movement != null) filters.put("movement", movement);
        if (line != null) filters.put("line", line);
        if (vessel != null) filters.put("vessel", vessel);
        if (voyage != null) filters.put("voyage", voyage);
        if (from != null) filters.put("from", from);
        if (to != null) filters.put("to", to);
        if (finalDestination != null) filters.put("final", finalDestination);
        if (createDate != null) filters.put("createDate", createDate);
        if (notifyCode != null) filters.put("notifyCode", notifyCode);
        if (agentCode != null) filters.put("agentCode", agentCode);
        return movementQuery.ReturnFilteredMovementListAsync(filters)
                .thenApply(movementDtos -> ResponseEntity.ok(movementDtos))
                .exceptionally(ex -> {
                    log.error("Could not retrieve filtered movements due to error {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                });
    }
}
