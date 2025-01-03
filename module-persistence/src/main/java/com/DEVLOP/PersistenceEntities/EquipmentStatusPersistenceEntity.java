package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "equipmentStatus")
public class EquipmentStatusPersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //equipmentStatusCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "equipmentStatusCode", nullable = false, unique = true)
    private String equipmentStatusCode;
    //equipmentStatusName
    @Column(name = "equipmentStatusName", nullable = false, unique = true)
    private String equipmentStatusName;
    //equipmentStatusLevel1
    @Column(name = "equipmentStatusLevel1", nullable = false, unique = true)
    private String equipmentStatusLevel1;
    //equipmentStatusLevel2
    @Column(name = "equipmentStatusLevel2", nullable = false, unique = true)
    private String equipmentStatusLevel2;
    //equipmentStatusComments
    @Column(name = "equipmentStatusComments", nullable = false, unique = true)
    private String equipmentStatusComments;

    public EquipmentStatusPersistenceEntity() {
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
