package com.DEVLOP.Application.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.Year;
@Data
public class EquipmentDto {
    @NotNull
    private int id;
    @NotNull (message = "Prefix must not be null")
    @Pattern(
        regexp = "^[A-Z]{4}$",
        message = "Prefix must be exactly 4 uppercase letters"
    )
    private String prefix;
    @NotNull (message ="number must not be null")
    private int number;
    @NotNull (message ="check digit must not be null")
    private int checkDigit;
    private double insideHeight;
    private double grossWeight;
    private double payload;
    private double tareWeight;
    private double insideLength;
    private double insideWidth;
    private double insideCubic;
    private Year yearOfManufacture;
    private String comment;
    //Equipment Type
    @NotNull (message =" equipment type code must not be null")
    private String equipmentTypeCode;
    @NotNull (message = "equipment type length must not be null")
    private double equipmentTypeLength;
    @NotNull (message = "equipment type length cannot be null")
    private double equipmentTypeTareWeight;
    //equipment class
    @NotNull (message =" equipment class code cannot be null")
    private String equipmentClassCode;

    public EquipmentDto() {
    }
    public EquipmentDto(String prefix, int number, int checkDigit, double insideHeight, double grossWeight, double payload, double tare, double insideLength, double insideWidth, double insideCubic, Year yearOfManufacture, String comment, String equipmentTypeCode, double equipmentTypeLength, double equipmentTypeTareWeight, String equipmentClassCode) {
        this.prefix = prefix;
        this.number = number;
        this.checkDigit = checkDigit;
        this.insideHeight = insideHeight;
        this.grossWeight = grossWeight;
        this.payload = payload;
        this.tareWeight = tare;
        this.insideLength = insideLength;
        this.insideWidth = insideWidth;
        this.insideCubic = insideCubic;
        this.yearOfManufacture = yearOfManufacture;
        this.comment = comment;
        this.equipmentTypeCode = equipmentTypeCode;
        this.equipmentTypeLength = equipmentTypeLength;
        this.equipmentTypeTareWeight = equipmentTypeTareWeight;
        this.equipmentClassCode = equipmentClassCode;
    }
}
