package com.DEVLOP.Web.Controllers;

import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.DEVLOP.ContainerMovements.Application.Queries.EquipmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentController {
    private final EquipmentQuery equipmentQuery;

    @Autowired
    public EquipmentController(EquipmentQuery equipmentQuery){
        this.equipmentQuery = equipmentQuery;
    }

    @GetMapping("/getAllEquipments")
    public List<EquipmentInformationDTO> getAllEquipments(){
        return equipmentQuery.fetchAndMapEquipments();
    }
}
