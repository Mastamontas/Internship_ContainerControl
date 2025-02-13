package com.DEVLOP.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "EquipmentClass")
public class EquipmentClass extends BaseEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;


    @NotNull
    @Column(name = "EquipmentClassCode", nullable = false, unique = true)
    private String equipmentClassCode;

    @NotNull
    @Column(name = "EquipmentClassName",nullable = false)
    private String equipmentClassName;


    //ISTO É UM ENUM
    @NotNull
    @Column(name = "EquipmentClassType", nullable = false)
    private String equipmentClassType;

    /*//equipment type tare
    @Column(name = "EquipmentClassTypeTare")
    private double equipmentClassTypeTare;
*/
    public EquipmentClass() {
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

    /*public double getEquipmentClassTypeTare() {
        return equipmentClassTypeTare;
    }

    public void setEquipmentClassTypeTare(double equipmentClassTypeTare) {
        this.equipmentClassTypeTare = equipmentClassTypeTare;
    }*/
}

