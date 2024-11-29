package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "movementTypeEquipmentStatus")
//tabela intermédia entre movementType e equipmentStatus
public class MovementTypeEquipmentStatus extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //movementTypeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementTypeCode", nullable = false)
    private String movementTypeCode;

    //movementTypeName
    @Column(name = "movementTypeName", nullable = false)
    private String movementTypeName;

    //equipmentStatusCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "equipmentStatusCode", nullable = false)
    private String equipmentStatusCode;

    //equipmentStatusName
    @Column(name = "equipmentStatusName", nullable = false)
    private String equipmentStatusName;
    //equipmentStatusLevel1
    @Column(name = "equipmentStatusLevel1", nullable = false)
    private String equipmentStatusLevel1;
    //equipmentStatusLevel2
    @Column(name = "equipmentStatusLevel2", nullable = false)
    private String equipmentStatusLevel2;

    public MovementTypeEquipmentStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementTypeCode() {
        return movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    public String getMovementTypeName() {
        return movementTypeName;
    }

    public void setMovementTypeName(String movementTypeName) {
        this.movementTypeName = movementTypeName;
    }

    public String getEquipmentStatusCode() {
        return equipmentStatusCode;
    }

    public void setEquipmentStatusCode(String equipmentStatusCode) {
        this.equipmentStatusCode = equipmentStatusCode;
    }

    public String getEquipmentStatusName() {
        return equipmentStatusName;
    }

    public void setEquipmentStatusName(String equipmentStatusName) {
        this.equipmentStatusName = equipmentStatusName;
    }

    public String getEquipmentStatusLevel1() {
        return equipmentStatusLevel1;
    }

    public void setEquipmentStatusLevel1(String equipmentStatusLevel1) {
        this.equipmentStatusLevel1 = equipmentStatusLevel1;
    }

    public String getEquipmentStatusLevel2() {
        return equipmentStatusLevel2;
    }

    public void setEquipmentStatusLevel2(String equipmentStatusLevel2) {
        this.equipmentStatusLevel2 = equipmentStatusLevel2;
    }
}
