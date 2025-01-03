package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "equipmentService")
public class EquipmentServicePersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //equipmentServiceCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "equipmentServiceCode", nullable = false, unique = true)
    private String equipmentServiceCode;
    //equipmentServiceName
    @Column(name = "equipmentServiceName", nullable = false, unique = true)
    private String equipmentServiceName;
    //equipmentServiceComments
    @Column(name = "equipmentServiceComments")
    private String equipmentServiceComments;

    public EquipmentServicePersistenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentServiceCode() {
        return equipmentServiceCode;
    }

    public void setEquipmentServiceCode(String equipmentServiceCode) {
        this.equipmentServiceCode = equipmentServiceCode;
    }

    public String getEquipmentServiceName() {
        return equipmentServiceName;
    }

    public void setEquipmentServiceName(String equipmentServiceName) {
        this.equipmentServiceName = equipmentServiceName;
    }

    public String getEquipmentServiceComments() {
        return equipmentServiceComments;
    }

    public void setEquipmentServiceComments(String equipmentServiceComments) {
        this.equipmentServiceComments = equipmentServiceComments;
    }
}
