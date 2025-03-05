package com.DEVLOP.Entities;
import java.time.Year;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
/*
adicionar aqui lombok para remover boilerplate code
faltam aqui enums da classe
 */
@Entity
@Table(name = "Equipment")
public class Equipment extends BaseEntity{
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id", nullable = false, unique = true, updatable = false)
    private int id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "EquipmentType", nullable = false)
    private EquipmentType equipmentType;

    @NotNull
    @Column(name = "Prefix", nullable = false)
    private String prefix;

    @NotNull
    @Column(name = "Number", nullable = false,updatable = false)
    private int number;

    @NotNull
    @Column(name = "CheckDigit", nullable = false,updatable = false)
    private int checkDigit;

    @Column(name = "GrossWeight")
    private double grossWeight;

    @Column(name = "InsideCubic")
    private double insideCubic;

    @Column(name = "InsideHeight")
    private double insideHeight;

    @Column(name = "InsideLength")
    private double insideLength;

    @Column(name = "InsideWidth")
    private double insideWidth;

    @Column(name="TareWeight")
    private double tareWeight;

    //yearOfManufacture
    @Column(name = "YearOfManufacture")
    private Year yearOfManufacture;

    //payload
    @Column(name = "Payload")
    private double payload;

    //comment
    @Column(name = "Comment")
    private String comment;

    //lineID
    //foreign key algures?
    @Column(name = "EquipmentLineID")
    private int lineID;

    //ownerID
    @Column(name = "EquipmentOwnerID")
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

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentTypeID) {
        this.equipmentType = equipmentTypeID;
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

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double equipmentTareWeight) {
        this.tareWeight = equipmentTareWeight;
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
