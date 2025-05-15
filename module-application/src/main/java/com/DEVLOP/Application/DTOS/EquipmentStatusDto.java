package com.DEVLOP.Application.DTOS;

import lombok.Data;

@Data
public class EquipmentStatusDto {
    private int id;
    private String equipmentStatusCode;
    private String equipmentStatusName;
    private String equipmentStatusLevel1;
    private String equipmentStatusLevel2;
    private String equipmentStatusComments;
}
