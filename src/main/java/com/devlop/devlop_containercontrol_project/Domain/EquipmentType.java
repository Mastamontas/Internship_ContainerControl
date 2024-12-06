package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "equipmentType")
public class EquipmentType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //equipmentClassID
    @ManyToOne
    @JoinColumn(name = "equipmentClassID", nullable = false, unique = true)
    private EquipmentClass equipmentClassID;

    //equipmentTypeCode
    @Column(name = "equipmentTypeCode", nullable = false, unique = true)
    private String equipmentTypeCode;

    //equipmentTypeLength
    @Column(name = "equipmentTypeLength")
    private int equipmentTypeLength;

    //equipmentTypeName
    @Column(name = "equipmentTypeName")
    private String equipmentTypeName;

    //equipmentTypeTareWeight
    @Column(name = "equipmentTypeTareWeight")
    private float equipmentTypeTareWeight;

    //equipmentTypeHeight
    @Column(name = "equipmentTypeHeight")
    private float equipmentTypeHeight;

    //equipmentTypeComments
    @Column(name = "equipmentTypeComments")
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

    public int getEquipmentTypeLength() {
        return equipmentTypeLength;
    }

    public void setEquipmentTypeLength(int equipmentTypeLength) {
        this.equipmentTypeLength = equipmentTypeLength;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public float getEquipmentTypeTareWeight() {
        return equipmentTypeTareWeight;
    }

    public void setEquipmentTypeTareWeight(float equipmentTypeTareWeight) {
        this.equipmentTypeTareWeight = equipmentTypeTareWeight;
    }

    public float getEquipmentTypeHeight() {
        return equipmentTypeHeight;
    }

    public void setEquipmentTypeHeight(float equipmentTypeHeight) {
        this.equipmentTypeHeight = equipmentTypeHeight;
    }

    public String getEquipmentTypeComments() {
        return equipmentTypeComments;
    }

    public void setEquipmentTypeComments(String equipmentTypeComments) {
        this.equipmentTypeComments = equipmentTypeComments;
    }
}
