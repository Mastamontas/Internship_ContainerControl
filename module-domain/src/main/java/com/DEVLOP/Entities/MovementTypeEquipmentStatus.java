package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "MovementTypeEquipmentStatus")
//tabela intermédia entre movementType e equipmentStatus
public class MovementTypeEquipmentStatus extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //movementTypeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "MovementTypeCode", nullable = false)
    private String movementTypeCode;

    //movementTypeName
    @Column(name = "MovementTypeName", nullable = false)
    private String movementTypeName;

    //equipmentStatusCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "EquipmentStatusCode", nullable = false)
    private String equipmentStatusCode;

    //equipmentStatusName
    @Column(name = "EquipmentStatusName", nullable = false)
    private String equipmentStatusName;
    //equipmentStatusLevel1
    @Column(name = "EquipmentStatusLevel1", nullable = false)
    private String equipmentStatusLevel1;
    //equipmentStatusLevel2
    @Column(name = "EquipmentStatusLevel2", nullable = false)
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
