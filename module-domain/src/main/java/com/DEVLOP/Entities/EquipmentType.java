package com.DEVLOP.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "EquipmentType")
public class EquipmentType extends BaseEntity{
    @NotNull
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="Id", nullable = false, unique = true)
    private int id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter
    @Setter
    @JoinColumn(name = "EquipmentClass", nullable = false, unique = true)
    private EquipmentClass equipmentClass;


    //ver se esta relação faz sentido estar aqui na entidade
    //isto nao tem de estar aqui, quem é dono da relação é a classe equipments
    @Getter
    @Setter
    @OneToMany(mappedBy = "equipmentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipments;

    @NotNull
    @Getter
    @Setter
    @Column(name = "EquipmentTypeCode", nullable = false, unique = true)//ver se este code tem de ser unique
    private String equipmentTypeCode;

    @NotNull
    @Getter
    @Setter
    @Column(name = "EquipmentTypeLength",nullable = false)
    private double equipmentTypeLength;

    @NotNull
    @Getter
    @Setter
    @Column(name = "EquipmentTypeName",nullable = false)
    private String equipmentTypeName;

    @NotNull
    @Getter
    @Setter
    @Column(name = "EquipmentTypeTareWeight",nullable = false)
    private double equipmentTypeTareWeight;

    @NotNull
    @Getter
    @Setter
    @Column(name = "EquipmentTypeHeight" ,nullable = false)
    private double equipmentTypeHeight;

    //equipmentTypeComments
    @Getter
    @Setter
    @Column(name = "EquipmentTypeComments")
    private String equipmentTypeComments;

    public EquipmentType() {
    }
}
