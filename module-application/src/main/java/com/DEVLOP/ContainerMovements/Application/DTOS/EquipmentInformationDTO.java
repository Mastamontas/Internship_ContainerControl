package com.DEVLOP.ContainerMovements.Application.DTOS;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Year;
@Data
public class EquipmentInformationDTO {
    @NotNull (message = "Prefix must not be null")
    private String prefix;
    @NotNull (message ="number must not be null")
    private int number;
    @NotNull (message ="check digit must not be null")
    private int checkDigit;
    private double insideHeight;
    private double grossWeight;
    private double payload;
    private double tare;
    private double insideLength;
    private double insideWidth;
    private double insideCubic;
    private Year yearBuilt;
    private String equipmentComment;
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

    public EquipmentInformationDTO() {
    }
    public EquipmentInformationDTO(String prefix, int number, int checkDigit, double insideHeight, double grossWeight, double payload, double tare, double insideLength, double insideWidth, double insideCubic, Year yearBuilt, String equipmentComment, String equipmentTypeCode, double equipmentTypeLength, double equipmentTypeTareWeight, String equipmentClassCode) {
        this.prefix = prefix;
        this.number = number;
        this.checkDigit = checkDigit;
        this.insideHeight = insideHeight;
        this.grossWeight = grossWeight;
        this.payload = payload;
        this.tare = tare;
        this.insideLength = insideLength;
        this.insideWidth = insideWidth;
        this.insideCubic = insideCubic;
        this.yearBuilt = yearBuilt;
        this.equipmentComment = equipmentComment;
        this.equipmentTypeCode = equipmentTypeCode;
        this.equipmentTypeLength = equipmentTypeLength;
        this.equipmentTypeTareWeight = equipmentTypeTareWeight;
        this.equipmentClassCode = equipmentClassCode;
    }
}
