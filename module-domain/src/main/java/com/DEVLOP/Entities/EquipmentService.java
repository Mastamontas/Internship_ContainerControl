package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "EquipmentService")
public class EquipmentService extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //equipmentServiceCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "EquipmentServiceCode", nullable = false, unique = true)
    private String equipmentServiceCode;
    //equipmentServiceName
    @Column(name = "EquipmentServiceName", nullable = false, unique = true)
    private String equipmentServiceName;
    //equipmentServiceComments
    @Column(name = "EquipmentServiceComments")
    private String equipmentServiceComments;

    public EquipmentService() {
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
