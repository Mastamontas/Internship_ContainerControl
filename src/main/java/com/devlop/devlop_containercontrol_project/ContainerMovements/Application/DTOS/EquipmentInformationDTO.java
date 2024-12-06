package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS;


import java.time.Year;

/**
 * DTO que é enviado para o frontend com a informação do equipamento
 * este DTO tem de ter a informação referente aos dados que vem das outras entidades
 */
public class EquipmentInformationDTO {
    private String prefix;
    private int number;
    private int checkDigit;
    private double insideHeight;
    private double grossWeight;
    private double insideCubic;
    private double insideWidth;
    private double insideLength;
    private double equipmentTareWeight;
    private Year yearOfManufacture;
    private double payload;
    private String comment;

    //foreign key equipment type
    private String equipmentTypeID;
    private String equipmentType;
    private double equipmentTypeLength;
    private String equipmentTypeClassCode;
    private double equipmentTypeTare;
/*
    //foreign key equipment line
    private String equipmentLineCode;
    private String equipmentLineName;
    //foreign key equipment owner
    private String equipmentOwnerCode;
    private String equipmentOwnerName;
    private boolean SOC;*/

    public EquipmentInformationDTO(String prefix, int number, int checkDigit, double insideHeight, double grossWeight, double insideCubic, double insideWidth, double insideLength, double equipmentTareWeight, Year yearOfManufacture, double payload, String comment, String equipmentTypeID, String equipmentType, double equipmentTypeLength, String equipmentTypeClassCode, double equipmentTypeTare, String equipmentLineCode, String equipmentLineName, String equipmentOwnerCode, String equipmentOwnerName, boolean SOC) {
        this.prefix = prefix;
        this.number = number;
        this.checkDigit = checkDigit;
        this.insideHeight = insideHeight;
        this.grossWeight = grossWeight;
        this.insideCubic = insideCubic;
        this.insideWidth = insideWidth;
        this.insideLength = insideLength;
        this.equipmentTareWeight = equipmentTareWeight;
        this.yearOfManufacture = yearOfManufacture;
        this.payload = payload;
        this.comment = comment;
        this.equipmentTypeID = equipmentTypeID;
        this.equipmentType = equipmentType;
        this.equipmentTypeLength = equipmentTypeLength;
        this.equipmentTypeClassCode = equipmentTypeClassCode;
        this.equipmentTypeTare = equipmentTypeTare;
       /* this.equipmentLineCode = equipmentLineCode;
        this.equipmentLineName = equipmentLineName;
        this.equipmentOwnerCode = equipmentOwnerCode;
        this.equipmentOwnerName = equipmentOwnerName;
        this.SOC = SOC;*/
    }

    public EquipmentInformationDTO() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(int checkDigit) {
        this.checkDigit = checkDigit;
    }

    public double getInsideHeight() {
        return insideHeight;
    }

    public void setInsideHeight(double insideHeight) {
        this.insideHeight = insideHeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public double getInsideCubic() {
        return insideCubic;
    }

    public void setInsideCubic(double insideCubic) {
        this.insideCubic = insideCubic;
    }

    public double getInsideWidth() {
        return insideWidth;
    }

    public void setInsideWidth(double insideWidth) {
        this.insideWidth = insideWidth;
    }

    public double getInsideLength() {
        return insideLength;
    }

    public void setInsideLength(double insideLength) {
        this.insideLength = insideLength;
    }

    public double getEquipmentTareWeight() {
        return equipmentTareWeight;
    }

    public void setEquipmentTareWeight(double equipmentTareWeight) {
        this.equipmentTareWeight = equipmentTareWeight;
    }

    public Year getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Year yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(String equipmentTypeID) {
        this.equipmentTypeID = equipmentTypeID;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public double getEquipmentTypeLength() {
        return equipmentTypeLength;
    }

    public void setEquipmentTypeLength(double equipmentTypeLength) {
        this.equipmentTypeLength = equipmentTypeLength;
    }

    public String getEquipmentTypeClassCode() {
        return equipmentTypeClassCode;
    }

    public void setEquipmentTypeClassCode(String equipmentTypeClassCode) {
        this.equipmentTypeClassCode = equipmentTypeClassCode;
    }

    public double getEquipmentTypeTare() {
        return equipmentTypeTare;
    }

    public void setEquipmentTypeTare(double equipmentTypeTare) {
        this.equipmentTypeTare = equipmentTypeTare;
    }

/*    public String getEquipmentLineCode() {
        return equipmentLineCode;
    }

    public void setEquipmentLineCode(String equipmentLineCode) {
        this.equipmentLineCode = equipmentLineCode;
    }

    public String getEquipmentLineName() {
        return equipmentLineName;
    }

    public void setEquipmentLineName(String equipmentLineName) {
        this.equipmentLineName = equipmentLineName;
    }

    public String getEquipmentOwnerCode() {
        return equipmentOwnerCode;
    }

    public void setEquipmentOwnerCode(String equipmentOwnerCode) {
        this.equipmentOwnerCode = equipmentOwnerCode;
    }

    public String getEquipmentOwnerName() {
        return equipmentOwnerName;
    }

    public void setEquipmentOwnerName(String equipmentOwnerName) {
        this.equipmentOwnerName = equipmentOwnerName;
    }

    public boolean isSOC() {
        return SOC;
    }

    public void setSOC(boolean SOC) {
        this.SOC = SOC;
    }*/
}
