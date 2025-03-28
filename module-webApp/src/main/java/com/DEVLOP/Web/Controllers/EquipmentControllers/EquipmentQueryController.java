package com.DEVLOP.Web.Controllers.EquipmentControllers;

import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.Queries.EquipmentQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/*
todo - codigos e convencoes api
kebab-case for urls
camelCase for json properties

API codes
200 OK	Successful request
201 Created	Resource created
204 No Content	Successful but no response body
400 Bad Request	Invalid request parameters
401 Unauthorized	Not authenticated
403 Forbidden	Not allowed to access
404 Not Found	Resource doesn’t exist
409 Conflict	Resource conflict (e.g., duplicate)
500 Internal Server Error	Unexpected server error

@Valid for requestbody parameters


 */
/**
 * REST controller for handling requests related to equipment operations.
 * This class exposes endpoints for interacting with equipment data.
 */
@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipments")
@Tag(name ="Equipments", description = "Endpoints for querying equipment data. Version 1")
public class EquipmentQueryController {
    private final EquipmentQuery equipmentQuery;

    /**
     * Constructs the {@code EquipmentController} with the required {@link EquipmentQuery} dependency.
     *
     * @param equipmentQuery The query service responsible for fetching and processing equipment data.
     */
    @Autowired
    public EquipmentQueryController(EquipmentQuery equipmentQuery){
        this.equipmentQuery = equipmentQuery;
    }

    /**
     * Retrieves all equipment details from the system.
     * This endpoint handles HTTP GET requests to "/getAllEquipments".
     *
     * @return A list of {@link EquipmentDto} containing details of all equipment.
     */
    @GetMapping("/")
    @Async
    @Operation(
            summary = "Get all equipment",
            description = "Retrieves all equipment details from the system."
    )
    public CompletableFuture<ResponseEntity<List<EquipmentDto>>> GetAllEquipments(){
        log.info("Returning all equipment from database");
        return equipmentQuery.FindAllEquipmentsAsync()
                .thenApply(equipments -> {
                    if (equipments == null || equipments.isEmpty()) {
                        log.warn("No equipment found, returning empty JSON list");
                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Collections.<EquipmentDto>emptyList());
                    }
                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(equipments);
                })
                .exceptionally(ex -> {
                    log.error("Error retrieving equipment list: {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).build();
                });
    }

    @GetMapping("/filter")
    @Async
    @Operation(
            summary = "returns list of equipments filtered or not according to the filtered attributes",
            description = "Returns equipment list. can be filtered by prefix, number or check digit"
    )
    public CompletableFuture<ResponseEntity<List<EquipmentDto>>> ReturnAllEquipmentsAsync(
            @Parameter(description = "Equipment prefix") @RequestParam(required = false) String prefix,
            @Parameter(description = "Equipment number") @RequestParam(required = false) Integer equipmentNumber,
            @Parameter(description = "Equipment check digit") @RequestParam(required = false) Integer checkDigit
    ){
        Map<String, Object> filters = new HashMap<>();
        if (prefix != null) filters.put("prefix", prefix);
        if (equipmentNumber != null) filters.put("number", equipmentNumber);
        if (checkDigit != null) filters.put("number", checkDigit);
        return equipmentQuery.ReturnEquipmentsFilteredASync(filters).thenApply(equipmentDtos -> ResponseEntity.ok(equipmentDtos))
                .exceptionally(ex ->{
                    log.error("could not retrieve filtered equipments");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                });
    }




    @GetMapping("/{id}")
    @Async
    @Operation(
            summary = "Returns an equipment by the id",
            description = "returns a equipment DTO according to the ID"
    )
    public CompletableFuture<ResponseEntity<EquipmentDto>> GetEquipmentByIDAsync(@Parameter(description = "Id of equipment") @PathVariable ("id") int id){
        return equipmentQuery.GetEquipmentByIDAsync(id).thenApply(equipmentDTO -> ResponseEntity.ok(equipmentDTO)).exceptionally(
                ex ->{
                    log.error("could not retrieve equipment with ID {} due to error {}",id, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
        );
    }
}
