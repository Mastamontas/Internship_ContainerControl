package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EquipmentLeasing")
public class EquipmentLeasing extends BaseEntity{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "LeasingContractCode", nullable = false)
    private String leasingContractCode;

    @Getter @Setter
    @Column(name = "LeasingContractName", nullable = false)
    private String leasingContractName;

    public EquipmentLeasing() {
    }
}
