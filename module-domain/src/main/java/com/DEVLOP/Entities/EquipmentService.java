package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EquipmentService")
public class EquipmentService extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "EquipmentServiceCode", nullable = false)
    private String equipmentServiceCode;
    //equipmentServiceName
    @Getter @Setter
    @Column(name = "EquipmentServiceName", nullable = false)
    private String equipmentServiceName;
    //equipmentServiceComments
    @Getter @Setter
    @Column(name = "EquipmentServiceComments")
    private String equipmentServiceComments;

    public EquipmentService() {
    }
}
