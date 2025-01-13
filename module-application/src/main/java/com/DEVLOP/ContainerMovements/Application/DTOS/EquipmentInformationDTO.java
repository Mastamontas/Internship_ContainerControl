package com.DEVLOP.ContainerMovements.Application.DTOS;
import lombok.Data;
import java.time.Year;
@Data
public class EquipmentInformationDTO {
    //equipamento
    private String prefix;
    private int number;
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
    private String equipmentTypeCode;
    private double equipmentTypeLength;
    private double equipmentTypeTareWeight;
    //equipment class
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
