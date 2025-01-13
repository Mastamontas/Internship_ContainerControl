package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "EquipmentStatus")
public class EquipmentStatus extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //equipmentStatusCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "EquipmentStatusCode", nullable = false, unique = true)
    private String equipmentStatusCode;
    //equipmentStatusName
    @Column(name = "EquipmentStatusName", nullable = false, unique = true)
    private String equipmentStatusName;
    //equipmentStatusLevel1
    @Column(name = "EquipmentStatusLevel1", nullable = false, unique = true)
    private String equipmentStatusLevel1;
    //equipmentStatusLevel2
    @Column(name = "EquipmentStatusLevel2", nullable = false, unique = true)
    private String equipmentStatusLevel2;
    //equipmentStatusComments
    @Column(name = "EquipmentStatusComments", nullable = false, unique = true)
    private String equipmentStatusComments;

    public EquipmentStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEquipmentStatusComments() {
        return equipmentStatusComments;
    }

    public void setEquipmentStatusComments(String equipmentStatusComments) {
        this.equipmentStatusComments = equipmentStatusComments;
    }
}
