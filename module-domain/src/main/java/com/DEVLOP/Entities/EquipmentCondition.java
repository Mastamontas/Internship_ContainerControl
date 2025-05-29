package com.DEVLOP.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EquipmentCondition")
public class EquipmentCondition extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "PhysicalConditionCode", nullable = false)
    private String physicalConditionCode;

    @Getter @Setter
    @Column(name = "PhysicalConditionName", nullable = false)
    private String physicalConditionName;

    @Getter @Setter
    @Column(name = "PhysicalConditionType", nullable = false)
    private String physicalConditionType;

    public EquipmentCondition() {
    }

}
