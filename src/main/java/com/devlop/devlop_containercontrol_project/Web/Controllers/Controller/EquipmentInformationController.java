package com.devlop.devlop_containercontrol_project.Web.Controllers.Controller;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Queries.EquipmentInformationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentInformationController {
    private final EquipmentInformationQuery equipmentInformationQuery;

    @Autowired
    public EquipmentInformationController(EquipmentInformationQuery equipmentInformationQuery) {
        this.equipmentInformationQuery = equipmentInformationQuery;
    }

    /**
     * endpoint to fetch all equipment information
     *
     * @return list of equipmentInformationDTO
     */
    /*@GetMapping
    public ResponseEntity<List<EquipmentInformationDTO>> getAllEquipments(){
        List<EquipmentInformationDTO> equipmentList = equipmentInformationService.getAllEquipmentinformation();
    }*/


}
