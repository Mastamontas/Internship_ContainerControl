package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table(name = "equipmentClass")
public class EquipmentClassPersistenceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //equipmentCode
    @Column(name = "equipmentClassCode", nullable = false, unique = true)
    private String equipmentClassCode;

    //equipmentName
    @Column(name = "equipmentClassName")
    private String equipmentClassName;

    //equipmentType
    @Column(name = "equipmentClassType")
    private String equipmentClassType;

    //equipment type tare
    @Column(name = "equipmentClassTypeTare")
    private double equipmentClassTypeTare;

    public EquipmentClassPersistenceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentClassCode() {
        return equipmentClassCode;
    }

    public void setEquipmentClassCode(String equipmentClassCode) {
        this.equipmentClassCode = equipmentClassCode;
    }

    public String getEquipmentClassName() {
        return equipmentClassName;
    }

    public void setEquipmentClassName(String equipmentClassName) {
        this.equipmentClassName = equipmentClassName;
    }

    public String getEquipmentClassType() {
        return equipmentClassType;
    }

    public void setEquipmentClassType(String equipmentClassType) {
        this.equipmentClassType = equipmentClassType;
    }

    public double getEquipmentClassTypeTare() {
        return equipmentClassTypeTare;
    }

    public void setEquipmentClassTypeTare(double equipmentClassTypeTare) {
        this.equipmentClassTypeTare = equipmentClassTypeTare;
    }
}

