package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "equipmentType")
public class EquipmentType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //equipmentClassID
    //many to one
    @Column(name = "equipmentClassID", nullable = false, unique = true)
    private int equipmentClassID;

    //equipmentTypeCode
    @Column(name = "equipmentTypeCode", nullable = false, unique = true)
    private int equipmentTypeCode;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEquipmentClassID() {
        return equipmentClassID;
    }

    public void setEquipmentClassID(int equipmentClassID) {
        this.equipmentClassID = equipmentClassID;
    }

    public int getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(int equipmentTypeCode) {
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
