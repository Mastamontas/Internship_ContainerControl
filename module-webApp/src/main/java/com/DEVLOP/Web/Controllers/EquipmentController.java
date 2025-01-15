package com.DEVLOP.Web.Controllers;

import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.DEVLOP.ContainerMovements.Application.Queries.EquipmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling requests related to equipment operations.
 * This class exposes endpoints for interacting with equipment data.
 */
@RestController
public class EquipmentController {
    private final EquipmentQuery equipmentQuery;

    /**
     * Constructs the {@code EquipmentController} with the required {@link EquipmentQuery} dependency.
     *
     * @param equipmentQuery The query service responsible for fetching and processing equipment data.
     */
    @Autowired
    public EquipmentController(EquipmentQuery equipmentQuery){
        this.equipmentQuery = equipmentQuery;
    }

    /**
     * Retrieves all equipment details from the system.
     * This endpoint handles HTTP GET requests to "/getAllEquipments".
     *
     * @return A list of {@link EquipmentInformationDTO} containing details of all equipment.
     */
    @GetMapping("/getAllEquipments")
    public List<EquipmentInformationDTO> getAllEquipments(){
        return equipmentQuery.fetchAndMapEquipments();
    }
}
