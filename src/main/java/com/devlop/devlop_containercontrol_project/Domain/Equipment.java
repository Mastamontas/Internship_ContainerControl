package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.time.Year;
/**
 * QUERY 1 CONTAINER INFORMATION
 * SELECT
 * e.prefix, (equipment) as prefix
 * e.number, AS number
 * e.insideHeight AS insideHeight,
 * e.grossWeight AS grossWeight,
 * e.payload AS payload,
 * e.tareWeight AS tareWeight,
 * e.insideLength AS insideLength,
 * e.insideCubic AS insideCubic,
 * e.yearOfManufacture AS yearOfManufacture,
 * e.comment AS comment,
 * eType.ID AS typeID, (equipmentType)
 * eType.equipmentType AS equipmentType,
 * eType.code AS typeCode,
 * eType.length AS typeLength,
 * eType.tare AS typeTare,
 * eLine.code AS lineCode, (equipmentLine)
 * eLine.name AS lineName,
 * eLeasing.ownerName AS equipmentOwner, (equipmentLeasing)
 * eLeasing.clusterID AS clusterID (?),
 *FROM
 * equipment e
 * JOIN
 *  equipmentType eType ON e.equipmentTypeID = eType.ID
 * JOIN
 *  equipmentLine eLine ON e.lineID = eLine.ID
 * JOIN
 *  equipmentLeasing eLeasing ON e.ownerID = eLeasing.ID
 */

/**
 * QUERY 2 INFORMAÇÂO HISTORICO DE MOVIMENTO
 * é necessário o movement aqui porque esta feature refere a movimentação de um container especifico
 * SELECT
 * e.prefix AS prefix,
 * e.number AS number,
 * e.checkDigit AS checkDigit,
 * e.equiptmentTypeID AS typeID,
 * e.length AS length,
 * e.equipmentTypeName AS equipmentType,
 * e.payload AS payload,
 * m.movementKey AS movementKey,
 * m.movementCode AS movementCode,
 * m.movementStatus AS movementStatus,
 * m.movementDate AS movementDate,
 * m.movementTime AS movementTime,
 * m.equipmentServiceCode AS serviceCode,
 * m.conditionCode AS conditionCode,
 * m.movementBound AS movementBound,
 * m.leasingCode AS leasingCode, (leasing code do equipamento? Se assim for tem de vir do equipmentLeasing)
 */

@Entity
@Table(name = "equipment")
public class Equipment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;


    //equipmentTypeID
    //many to one
    @Column(name = "equipmentTypeID", nullable = false)
    private int equipmentTypeID;

    //prefix
    //tem de ser unique
    @Column(name = "prefix", nullable = false)
    private String prefix;

    //number
    @Column(name = "number", nullable = false)
    private int number;

    //checkDigit
    @Column(name = "checkDigit", nullable = false)
    private int checkDigit;

    //grossWeight
    @Column(name = "grossWeight")
    private double grossWeight;

    //insideCubic
    @Column(name = "insideCubic")
    private double insideCubic;

    //insideHeight
    @Column(name = "insideHeight")
    private double insideHeight;

    //insideLength
    @Column(name = "insideLength")
    private double insideLength;

    //insideWidth
    @Column(name = "insideWidth")
    private double insideWidth;
    
    //tareWeight
    @Column(name="equipmentTareWeight")
    private double equipmentTareWeight;

    //yearOfManufacture
    @Column(name = "yearOfManufacture")
    private Year yearOfManufacture;

    //payload
    @Column(name = "payload")
    private double payload;

    //comment
    @Column(name = "comment")
    private String comment;

    //lineID
    @Column(name = "equipmentLineID")
    private int lineID;

    //ownerID
    @Column(name = "equipmentOwnerID")
    private int ownerID;

    //SOC (shipper owner container)
    @Column(name = "SOC")
    private boolean SOC;

    //tem de ter movement ID para retornar informações


    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(int equipmentTypeID) {
        this.equipmentTypeID = equipmentTypeID;
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

    public double getInsideHeight() {
        return insideHeight;
    }

    public void setInsideHeight(double insideHeight) {
        this.insideHeight = insideHeight;
    }

    public double getInsideLength() {
        return insideLength;
    }

    public void setInsideLength(double insideLength) {
        this.insideLength = insideLength;
    }

    public double getInsideWidth() {
        return insideWidth;
    }

    public void setInsideWidth(double insideWidth) {
        this.insideWidth = insideWidth;
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

    public int getLineID() {
        return lineID;
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public boolean isSOC() {
        return SOC;
    }

    public void setSOC(boolean SOC) {
        this.SOC = SOC;
    }
}
