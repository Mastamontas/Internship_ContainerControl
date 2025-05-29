package com.DEVLOP.Application.DTOS;

import lombok.Data;

@Data
public class EquipmentTypeDto {
    private Integer id;
    private Integer equipmentClassID;
    private String equipmentTypeCode;
    private String equipmentTypeName;
    private double equipmentTypeLength;
    private double equipmentTypeHeight;
    private String equipmentTypeComments;


}

