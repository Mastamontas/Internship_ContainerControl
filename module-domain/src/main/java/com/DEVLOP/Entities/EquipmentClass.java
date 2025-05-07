package com.DEVLOP.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
todo: rever esta table que esta sem lombok e com dados provavelmente errados

 */
@Entity
@Table(name = "EquipmentClass")
public class EquipmentClass extends BaseEntity {
    @NotNull
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @OneToMany(mappedBy = "equipmentClass", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<EquipmentType> equipmentTypes;


    @NotNull
    @Getter @Setter
    @Column(name = "EquipmentClassCode", nullable = false, unique = true)
    private String equipmentClassCode;

    @NotNull
    @Getter @Setter
    @Column(name = "EquipmentClassName",nullable = false)
    private String equipmentClassName;


    //ISTO É UM ENUM
    @NotNull
    @Getter @Setter
    @Column(name = "EquipmentClassType", nullable = false)
    private String equipmentClassType;

    public EquipmentClass() {
    }
}

