package com.DEVLOP.Web.Controllers.MovementControllers;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Queries.MovementQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/movements")
@Tag(name ="Movement queries", description = "Endpoints for querying movement data from movements. Version 1")
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
            description = "asynchronously returns a list of movements according to the ID of the input equipment. "
    )
    public CompletableFuture<ResponseEntity<List<MovementDto>>> GetMovementsOfEquipment(@Parameter(description = "Id of equipment") @PathVariable("id") int id){
        System.out.println(Thread.currentThread().getName());
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
    //todo: paginação das listas retornadas
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
            @Parameter(description = "Origin location") @RequestParam(value ="fromLocation", required = false) String from,
            @Parameter(description = "Destination location") @RequestParam(value ="toLocation",required = false) String to,
            @Parameter(description = "Final destination") @RequestParam(required = false) String finalDestination,
            @Parameter(description = "Creation date (YYYY-MM-DD)") @RequestParam(required = false) String createDate,
            @Parameter(description = "Notification code") @RequestParam(required = false) String notifyCode,
            @Parameter(description = "Agent code") @RequestParam(required = false) String agentCode,
            @Parameter(description = "Equipment status code of movement") @RequestParam(required = false) String equipmentStatusCode
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
        if(equipmentStatusCode != null) filters.put("equipmentStatus.equipmentStatusCode", equipmentStatusCode);
        log.info("Received filters: {}", filters);
        return movementQuery.ReturnFilteredMovementListAsync(filters)
                .thenApply(movementDtos -> ResponseEntity.ok(movementDtos))
                .exceptionally(ex -> {
                    log.error("Could not retrieve filtered movements due to error {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                });
    }


    //range movements filtered - same as above but dates are mandatory
    /*
    recebe dois filters
     */
    @GetMapping("/rangeFilter")
    @Async
    @Operation(
            summary = "Returns filtered list of movements",
            description = "Asynchronously returns a list of filtered movements according to the selected filters"
    )
    //todo: paginação das listas retornadas
    public CompletableFuture<ResponseEntity<List<MovementDto>>> ReturnRangeMovementList(
            //from and to. repeat the parameters?
            @Parameter(description = "date of the movements to range")
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate dateFrom,

            @Parameter(description = "Equipment Prefix of the movement") @RequestParam(required = false) String fromPrefix,
            @Parameter(description = "Equipment number") @RequestParam(required = false) Integer fromNumber,
            @Parameter(description = "Equipment type") @RequestParam(required = false) String fromEquipmentType,
            @Parameter(description = "Length of the equipment") @RequestParam(required = false) Integer fromLength,
            @Parameter(description = "Status of the movement") @RequestParam(required = false) String fromStatus,
            @Parameter(description = "Comments on the movement") @RequestParam(required = false) String fromComments,
            @Parameter(description = "Type of movement") @RequestParam(required = false) String fromMovement,
            @Parameter(description = "Shipping line") @RequestParam(required = false) String fromLine,
            @Parameter(description = "Vessel name") @RequestParam(required = false) String fromVessel,
            @Parameter(description = "Voyage number") @RequestParam(required = false) String fromVoyage,
            @Parameter(description = "Origin location") @RequestParam(required = false) String fromFilterFromLocation,
            @Parameter(description = "Destination location") @RequestParam(required = false) String fromFilterToLocation,
            @Parameter(description = "Final destination") @RequestParam(required = false) String fromFinalDestination,
            @Parameter(description = "Creation date (YYYY-MM-DD)") @RequestParam(required = false) String fromCreateDate,
            @Parameter(description = "Notification code") @RequestParam(required = false) String fromNotifyCode,
            @Parameter(description = "Agent code") @RequestParam(required = false) String fromAgentCode,
            @Parameter(description = "Equipment status code of movement") @RequestParam(required = false) String fromEquipmentStatusCode,



            @Parameter(description = "date of the movements from range")
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate dateTo,

            @Parameter(description = "Equipment Prefix of the movement") @RequestParam(required = false) String toPrefix,
            @Parameter(description = "Equipment number") @RequestParam(required = false) Integer toNumber,
            @Parameter(description = "Equipment type") @RequestParam(required = false) String toEquipmentType,
            @Parameter(description = "Length of the equipment") @RequestParam(required = false) Integer toLength,
            @Parameter(description = "Status of the movement") @RequestParam(required = false) String toStatus,
            @Parameter(description = "Comments on the movement") @RequestParam(required = false) String toComments,
            @Parameter(description = "Type of movement") @RequestParam(required = false) String toMovement,
            @Parameter(description = "Shipping line") @RequestParam(required = false) String toLine,
            @Parameter(description = "Vessel name") @RequestParam(required = false) String toVessel,
            @Parameter(description = "Voyage number") @RequestParam(required = false) String toVoyage,
            @Parameter(description = "Origin location") @RequestParam(required = false) String toFilterFromLocation,
            @Parameter(description = "Destination location") @RequestParam(required = false) String toFilterToLocation,
            @Parameter(description = "Final destination") @RequestParam(required = false) String toFinalDestination,
            @Parameter(description = "Creation date (YYYY-MM-DD)") @RequestParam(required = false) String toCreateDate,
            @Parameter(description = "Notification code") @RequestParam(required = false) String toNotifyCode,
            @Parameter(description = "Agent code") @RequestParam(required = false) String toAgentCode,
            @Parameter(description = "Equipment status code of movement") @RequestParam(required = false) String toEquipmentStatusCode

    ){
        Map<String, Object> fromFilter = new HashMap<>();
        if (dateFrom != null) fromFilter.put("date", dateFrom);
        if (fromPrefix != null) fromFilter.put("prefix", fromPrefix);
        if (fromNumber != null) fromFilter.put("number", fromNumber);
        if (fromEquipmentType != null) fromFilter.put("equipmentType", fromEquipmentType);
        if (fromLength != null) fromFilter.put("length", fromLength);
        if (fromStatus != null) fromFilter.put("status", fromStatus);
        if (fromComments != null) fromFilter.put("comments", fromComments);
        if (fromMovement != null) fromFilter.put("movement", fromMovement);
        if (fromLine != null) fromFilter.put("line", fromLine);
        if (fromVessel != null) fromFilter.put("vessel", fromVessel);
        if (fromVoyage != null) fromFilter.put("voyage", fromVoyage);
        if (fromFilterFromLocation != null) fromFilter.put("from", fromFilterFromLocation);
        if (fromFilterToLocation != null) fromFilter.put("to", fromFilterToLocation);
        if (fromFinalDestination != null) fromFilter.put("final", fromFinalDestination);
        if (fromCreateDate != null) fromFilter.put("createDate", fromCreateDate);
        if (fromNotifyCode != null) fromFilter.put("notifyCode", fromNotifyCode);
        if (fromAgentCode != null) fromFilter.put("agentCode", fromAgentCode);
        if(fromEquipmentStatusCode != null) fromFilter.put("equipmentStatus.equipmentStatusCode", fromEquipmentStatusCode);
        log.info("Received filters: {}", fromFilter);

        Map<String, Object> toFilter = new HashMap<>();
        if (dateTo != null) toFilter.put("date", dateTo);
        if (toPrefix != null) toFilter.put("prefix", toPrefix);
        if (toNumber != null) toFilter.put("number", toNumber);
        if (toEquipmentType != null) toFilter.put("equipmentType", toEquipmentType);
        if (toLength != null) toFilter.put("length", toLength);
        if (toStatus != null) toFilter.put("status", toStatus);
        if (toComments != null) toFilter.put("comments", toComments);
        if (toMovement != null) toFilter.put("movement", toMovement);
        if (toLine != null) toFilter.put("line", toLine);
        if (toVessel != null) toFilter.put("vessel", toVessel);
        if (toVoyage != null) toFilter.put("voyage", toVoyage);
        if (toFilterFromLocation != null) toFilter.put("from", toFilterFromLocation);
        if (toFilterToLocation != null) toFilter.put("to", toFilterToLocation);
        if (toFinalDestination != null) toFilter.put("final", toFinalDestination);
        if (toCreateDate != null) toFilter.put("createDate", toCreateDate);
        if (toNotifyCode != null) toFilter.put("notifyCode", toNotifyCode);
        if (toAgentCode != null) toFilter.put("agentCode", toAgentCode);
        if(toEquipmentStatusCode != null) toFilter.put("equipmentStatus.equipmentStatusCode", toEquipmentStatusCode);
        log.info("Received filters: {}", toFilter);
        System.out.println(Thread.currentThread().getName());
        return movementQuery.ReturnRangeFilteredMovementListAsync(fromFilter, toFilter)
                .thenApply(movementDtos -> ResponseEntity.ok(movementDtos))
                .exceptionally(ex -> {
                    log.error("Could not retrieve filtered movements due to error {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                });
    }

    /*
    função tem de ser asincrona
     */
    /*@GetMapping("export")
    @Async
    @Operation(summary = "Returns an excel with movement information")*/

}
