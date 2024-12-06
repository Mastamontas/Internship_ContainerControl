package com.devlop.devlop_containercontrol_project.Domain;
import jakarta.persistence.*;
import java.time.Year;
@Entity
@Table(name = "equipment")
public class Equipment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "equipmentTypeID", nullable = false)
    private EquipmentType equipmentTypeID;

    @Column(name = "prefix", nullable = false,updatable = false)
    private String prefix;

    @Column(name = "number", nullable = false,updatable = false)
    private int number;

    @Column(name = "checkDigit", nullable = false,updatable = false)
    private int checkDigit;

    @Column(name = "grossWeight")
    private double grossWeight;

    @Column(name = "insideCubic")
    private double insideCubic;

    @Column(name = "insideHeight")
    private double insideHeight;

    @Column(name = "insideLength")
    private double insideLength;

    @Column(name = "insideWidth")
    private double insideWidth;

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
    //foreign key algures?
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

    public EquipmentType getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(EquipmentType equipmentTypeID) {
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
