package com.DEVLOP.Web.Controllers;
import com.DEVLOP.Application.Commands.EquipmentCommand;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Application.Queries.EquipmentQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller for handling requests related to equipment operations.
 * This class exposes endpoints for interacting with equipment data.
 */
@RestController
@RequestMapping("/equipment")
@Tag(name ="Equipment", description = "Endpoints for managing equipment data")
public class EquipmentController {
    private final EquipmentQuery equipmentQuery;
    private final EquipmentCommand equipmentCommand;

    /**
     * Constructs the {@code EquipmentController} with the required {@link EquipmentQuery} dependency.
     *
     * @param equipmentQuery The query service responsible for fetching and processing equipment data.
     */
    @Autowired
    public EquipmentController(EquipmentQuery equipmentQuery, EquipmentCommand equipmentCommand){
        this.equipmentQuery = equipmentQuery;
        this.equipmentCommand = equipmentCommand;
    }

    /**
     * Retrieves all equipment details from the system.
     * This endpoint handles HTTP GET requests to "/getAllEquipments".
     *
     * @return A list of {@link EquipmentDTO} containing details of all equipment.
     */
    @GetMapping("/getAllEquipments")
    @Operation(
            summary = "Get all equipment",
            description = "Retrieves all equipment details from the system."
    )
    public CompletableFuture<List<EquipmentDTO>> getAllEquipments(){
        return equipmentQuery.findAllAsync();
    }
    @GetMapping("/{prefix}/{checkDigit}/{number}")
    @Operation(
            summary = "Get equipment by details",
            description = "Retrieve an equipment item by its unique details."
    )
    public CompletableFuture<EquipmentDTO> getEqByUniqueDetails(
            @Parameter(description = "Prefix of equipment") @PathVariable ("prefix") String prefix,
            @Parameter(description = "Check digit") @PathVariable ("checkDigit") int checkDigit,
            @Parameter(description = "Equipment number") @PathVariable ("number") int number){
        return equipmentQuery.returnEqDTOByUniqueDetailsAsync(prefix, checkDigit, number);
    }
    /*
    update equipment async
    recebe dto
     */
    @PostMapping("/updateEq")
    @Operation(
            summary = "Updates existing equipment",
            description = "receives a equipment DTO, gets the database reference, and updates fields"
    )
    public CompletableFuture<Void> updateEquipmentAsync(@Valid @RequestBody EquipmentDTO eqDTO){
       return equipmentCommand.updateAsync(eqDTO).exceptionally(ex -> {
           //por o logger
           System.out.println("Error updating equipment");
           //custom response
           return null;
       });
    }
}
