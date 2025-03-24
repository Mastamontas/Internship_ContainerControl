package com.DEVLOP.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EquipmentCondition")
public class EquipmentCondition extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "PhysicalConditionCode", nullable = false, unique = true)
    private String physicalConditionCode;

    @Getter @Setter
    @Column(name = "PhysicalConditionName", nullable = false, unique = true)
    private String physicalConditionName;

    @Getter @Setter
    @Column(name = "PhysicalConditionType", nullable = false, unique = true)
    private String physicalConditionType;

    public EquipmentCondition() {
    }

}
