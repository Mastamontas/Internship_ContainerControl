package com.DEVLOP.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MovementLink")
public class MovementLink extends BaseEntity{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "MovementLinkType", nullable = false)
    private String movementLinkType;

    @Getter @Setter
    @Column(name = "AccountID", nullable = false)
    private int accountID;

    @Getter @Setter
    @Column(name = "MovementName", nullable = false)
    private String movementName;

    @Getter @Setter
    @Column(name = "AccountVATNumber", nullable = false)
    private int accountVATNumber;

    @Getter @Setter
    @Column(name = "MovementLinkOutCode", nullable = false)
    private String movementLinkOutCode;

    @Getter @Setter
    @Column(name = "MovementLinkOutName", nullable = false)
    private String movementLinkOutName;

    @Getter @Setter
    @Column(name = "MovementEquipmentStatusOutCode", nullable = false)
    private String movementEquipmentStatusOutCode;

    @Getter @Setter
    @Column(name = "MovementFromOutCode", nullable = false)
    private String movementFromOutCode;

    @Getter @Setter
    @Column(name = "MovementToOutCode", nullable = false)
    private String movementToOutCode;

    @Getter @Setter
    @Column(name = "MovementConditionOutCode", nullable = false)
    private String movementConditionOutCode;

    @Getter @Setter
    @Column(name = "MovementTypeOutBound", nullable = false)
    private String movementTypeOutBound;

    @Getter @Setter
    @Column(name = "MovementLinkInCode", nullable = false)
    private String movementLinkInCode;

    @Getter @Setter
    @Column(name = "MovementLinkInName", nullable = false)
    private String movementLinkInName;

    @Getter @Setter
    @Column(name = "MovementEquipmentStatusInCode", nullable = false)
    private String movementEquipmentStatusInCode;

    @Getter @Setter
    @Column(name = "MovementFromInCode", nullable = false)
    private String movementFromInCode;

    @Getter @Setter
    @Column(name = "MovementToInCode", nullable = false)
    private String movementToInCode;

    @Getter @Setter
    @Column(name = "MovementConditionInCode", nullable = false)
    private String movementConditionInCode;

    @Getter @Setter
    @Column(name = "MovementTypeInBound", nullable = false)
    private String movementTypeInBound;

    @Getter @Setter
    @Column(name = "OutLocationField", nullable = false)
    private String outLocationField;

    @Getter @Setter
    @Column(name = "InLocationField", nullable = false)
    private String inLocationField;

    public MovementLink() {
    }
}
