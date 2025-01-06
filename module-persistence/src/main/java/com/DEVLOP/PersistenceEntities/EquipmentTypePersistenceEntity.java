package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "equipmentType")
public class EquipmentTypePersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //equipmentClassID
    @ManyToOne
    @JoinColumn(name = "equipmentClassID", nullable = false, unique = true)
    private EquipmentClassPersistenceEntity equipmentClassID;

    //equipmentTypeCode
    @Column(name = "equipmentTypeCode", nullable = false, unique = true)
    private String equipmentTypeCode;

    //equipmentTypeLength
    @Column(name = "equipmentTypeLength")
    private double equipmentTypeLength;

    //equipmentTypeName
    @Column(name = "equipmentTypeName")
    private String equipmentTypeName;

    //equipmentTypeTareWeight
    @Column(name = "equipmentTypeTareWeight")
    private double equipmentTypeTareWeight;

    //equipmentTypeHeight
    @Column(name = "equipmentTypeHeight")
    private double equipmentTypeHeight;

    //equipmentTypeComments
    @Column(name = "equipmentTypeComments")
    private String equipmentTypeComments;

    public EquipmentTypePersistenceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EquipmentClassPersistenceEntity getEquipmentClassID() {
        return equipmentClassID;
    }

    public void setEquipmentClassID(EquipmentClassPersistenceEntity equipmentClassID) {
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
