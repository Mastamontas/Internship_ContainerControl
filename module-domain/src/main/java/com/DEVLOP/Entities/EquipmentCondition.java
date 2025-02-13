package com.DEVLOP.Entities;

import jakarta.persistence.*;
@Entity
@Table(name = "EquipmentCondition")
public class EquipmentCondition extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //physicalConditionCode
    //primary key desta entidade
    @Column(name = "PhysicalConditionCode", nullable = false, unique = true)
    private String physicalConditionCode;
    //physicalConditionName
    @Column(name = "PhysicalConditionName", nullable = false, unique = true)
    private String physicalConditionName;
    //physicalConditionType
    @Column(name = "PhysicalConditionType", nullable = false, unique = true)
    private String physicalConditionType;

    public EquipmentCondition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhysicalConditionCode() {
        return physicalConditionCode;
    }

    public void setPhysicalConditionCode(String physicalConditionCode) {
        this.physicalConditionCode = physicalConditionCode;
    }

    public String getPhysicalConditionName() {
        return physicalConditionName;
    }

    public void setPhysicalConditionName(String physicalConditionName) {
        this.physicalConditionName = physicalConditionName;
    }

    public String getPhysicalConditionType() {
        return physicalConditionType;
    }

    public void setPhysicalConditionType(String physicalConditionType) {
        this.physicalConditionType = physicalConditionType;
    }
}
