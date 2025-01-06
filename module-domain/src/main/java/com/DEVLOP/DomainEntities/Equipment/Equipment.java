package com.DEVLOP.DomainEntities.Equipment;

import lombok.Data;

import java.time.Year;
@Data //lombok annotation to create getters and setters
public class Equipment {
    private int id;
    private String prefix;
    private int number;
    private int checkDigit;
    private double grossWeight;
    private double insideCubic;
    private double insideHeight;
    private double insideLength;
    private double insideWidth;
    private double equipmentTareWeight;
    private Year yearOfManufacture;
    private double payload;
    private String comment;
    private boolean SOC;
    //equipment type
    private String equipmentTypeCode;
    private double equipmentTypeLength;
    private double equipmentTypeTareWeight;
    //equipment class
    private String equipmentClassCode;

    //ID's para serviços externos depois
    //equipment line
    private int equipmentLineID;
    //equipment owner
    private int equipmentOwnerID;



    public Equipment() {
    }

    public Equipment(int id, String prefix, int number, int checkDigit, double grossWeight, double insideCubic, double insideHeight, double insideLength, double insideWidth, double equipmentTareWeight, Year yearOfManufacture, double payload, String comment, boolean SOC, String equipmentTypeCode, double equipmentTypeLength, double equipmentTypeTareWeight, String equipmentClassCode, int equipmentLineID, int equipmentOwnerID) {
        this.id = id;
        this.prefix = prefix;
        this.number = number;
        this.checkDigit = checkDigit;
        this.grossWeight = grossWeight;
        this.insideCubic = insideCubic;
        this.insideHeight = insideHeight;
        this.insideLength = insideLength;
        this.insideWidth = insideWidth;
        this.equipmentTareWeight = equipmentTareWeight;
        this.yearOfManufacture = yearOfManufacture;
        this.payload = payload;
        this.comment = comment;
        this.SOC = SOC;
        this.equipmentTypeCode = equipmentTypeCode;
        this.equipmentTypeLength = equipmentTypeLength;
        this.equipmentTypeTareWeight = equipmentTypeTareWeight;
        this.equipmentClassCode = equipmentClassCode;
        this.equipmentLineID = equipmentLineID;
        this.equipmentOwnerID = equipmentOwnerID;
    }
}
