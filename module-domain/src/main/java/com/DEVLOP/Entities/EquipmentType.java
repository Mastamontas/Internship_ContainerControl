package com.DEVLOP.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "EquipmentType")
public class EquipmentType extends BaseEntity{
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id", nullable = false, unique = true)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EquipmentClassID", nullable = false, unique = true)
    private EquipmentClass equipmentClassID;

    @NotNull
    @Column(name = "EquipmentTypeCode", nullable = false, unique = true)
    private String equipmentTypeCode;

    @NotNull
    @Column(name = "EquipmentTypeLength",nullable = false)
    private double equipmentTypeLength;

    @NotNull
    @Column(name = "EquipmentTypeName",nullable = false)
    private String equipmentTypeName;

    @NotNull
    @Column(name = "EquipmentTypeTareWeight",nullable = false)
    private double equipmentTypeTareWeight;

    @NotNull
    @Column(name = "EquipmentTypeHeight" ,nullable = false)
    private double equipmentTypeHeight;

    //equipmentTypeComments
    @Column(name = "EquipmentTypeComments")
    private String equipmentTypeComments;

    public EquipmentType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EquipmentClass getEquipmentClassID() {
        return equipmentClassID;
    }

    public void setEquipmentClassID(EquipmentClass equipmentClassID) {
        this.equipmentClassID = equipmentClassID;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public double getEquipmentTypeLength() {
        return equipmentTypeLength;
    }

    public void setEquipmentTypeLength(double equipmentTypeLength) {
        this.equipmentTypeLength = equipmentTypeLength;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public double getEquipmentTypeTareWeight() {
        return equipmentTypeTareWeight;
    }

    public void setEquipmentTypeTareWeight(double equipmentTypeTareWeight) {
        this.equipmentTypeTareWeight = equipmentTypeTareWeight;
    }

    public double getEquipmentTypeHeight() {
        return equipmentTypeHeight;
    }

    public void setEquipmentTypeHeight(double equipmentTypeHeight) {
        this.equipmentTypeHeight = equipmentTypeHeight;
    }

    public String getEquipmentTypeComments() {
        return equipmentTypeComments;
    }

    public void setEquipmentTypeComments(String equipmentTypeComments) {
        this.equipmentTypeComments = equipmentTypeComments;
    }
}
