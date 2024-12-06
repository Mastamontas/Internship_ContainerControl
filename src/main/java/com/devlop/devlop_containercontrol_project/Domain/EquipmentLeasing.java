package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "equipmentLeasing")
public class EquipmentLeasing extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    //leasingContractCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "leasingContractCode", nullable = false, unique = true)
    private String leasingContractCode;
    //leasingContractName
    @Column(name = "leasingContractName", nullable = false, unique = true)
    private String leasingContractName;

    public EquipmentLeasing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeasingContractCode() {
        return leasingContractCode;
    }

    public void setLeasingContractCode(String leasingContractCode) {
        this.leasingContractCode = leasingContractCode;
    }

    public String getLeasingContractName() {
        return leasingContractName;
    }

    public void setLeasingContractName(String leasingContractName) {
        this.leasingContractName = leasingContractName;
    }
}
