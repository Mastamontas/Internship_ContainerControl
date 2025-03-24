package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EquipmentStatus")
public class EquipmentStatus extends BaseEntity{


    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "EquipmentStatusCode", nullable = false)
    private String equipmentStatusCode;

    @Getter @Setter
    @Column(name = "EquipmentStatusName", nullable = false)
    private String equipmentStatusName;

    @Getter @Setter
    @Column(name = "EquipmentStatusLevel1", nullable = false)
    private String equipmentStatusLevel1;

    @Getter @Setter
    @Column(name = "EquipmentStatusLevel2", nullable = false)
    private String equipmentStatusLevel2;

    @Getter @Setter
    @Column(name = "EquipmentStatusComments")
    private String equipmentStatusComments;

    public EquipmentStatus() {
    }
}
