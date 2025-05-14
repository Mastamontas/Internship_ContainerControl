package com.DEVLOP.Application.DTOS;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Valid
@Data
public class MovementTypeDto {
    private Integer id;
    private String movementTypeCode;
    private String movementTypeName;
    private String movementTypeComments;
    private boolean isEmpty;
}
