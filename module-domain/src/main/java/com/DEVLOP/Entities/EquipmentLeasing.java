package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "EquipmentLeasing")
public class EquipmentLeasing extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    //leasingContractCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "LeasingContractCode", nullable = false, unique = true)
    private String leasingContractCode;
    //leasingContractName
    @Column(name = "LeasingContractName", nullable = false, unique = true)
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
