package com.DEVLOP.Application.DTOS;

import lombok.Data;

@Data
public class EquipmentConditionDto {
    private Integer id;
    private String physicalConditionCode;
    private String physicalConditionName;
    private String physicalConditionType;
}
