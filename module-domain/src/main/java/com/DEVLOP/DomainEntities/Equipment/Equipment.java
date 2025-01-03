package com.DEVLOP.DomainEntities.Equipment;

import lombok.Data;

import java.time.Year;
@Data //lombok annotation to create getters and setters
public class Equipment {
    //not null
    private int id;
    //not null
    private int equipmentTypeID;
    private int movementID;
    //not null
    private String prefix;
    //not null
    private int number;
    //not null
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
    private int lineID;
    private int ownerID;
    private boolean SOC;


    public Equipment() {
    }

    public Equipment(int id, int equipmentTypeID,int movementID, String prefix, int number, int checkDigit, double grossWeight, double insideCubic, double insideHeight, double insideLength, double insideWidth, double equipmentTareWeight, Year yearOfManufacture, double payload, String comment, int lineID, int ownerID, boolean SOC) {
        this.id = id;
        this.equipmentTypeID = equipmentTypeID;
        this.movementID = movementID;
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
        this.lineID = lineID;
        this.ownerID = ownerID;
        this.SOC = SOC;
    }
}
