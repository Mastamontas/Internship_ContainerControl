package com.DEVLOP.Web.Controllers.EquipmentControllers;

import com.DEVLOP.Application.Commands.EquipmentCommand;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


/*
todo
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

update equipment - put or patch?
 */
@Slf4j
@RestController
@EnableAsync
@RequestMapping("/v1/equipments")
@Tag(name ="Equipment", description = "Endpoints for controlling equipment data. Version 1")
public class EquipmentCommandController {
    private final EquipmentCommand equipmentCommand;

    @Autowired
    public EquipmentCommandController(EquipmentCommand equipmentCommand){
        this.equipmentCommand = equipmentCommand;
    }


    @PutMapping("/{id}/update")
    @Async
    @Operation(
            summary = "Updates existing equipment",
            description = "receives a equipment DTO, gets the database reference, and updates fields"
    )
    public CompletableFuture<ResponseEntity<String>> UpdateEquipment(@PathVariable("id") int id, @Valid @RequestBody EquipmentDTO eqDTO) {
        return equipmentCommand.UpdateEquipment(id, eqDTO)
                .thenApply(updatedEquipment -> ResponseEntity.ok("Equipment was updated successfully"))
                .exceptionally(ex -> {
                    log.error("error updating equipment with ID {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Failed to update equipment");
                });
    }
}
