package com.DEVLOP.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Movement")
@NoArgsConstructor
public class Movement extends BaseEntity{

    @NotNull
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name ="Date", nullable = false)
    private LocalDate date;

    @Getter @Setter
    @Column(name ="MovementStatus", nullable = false)
    private String movementStatus;//tem de ser enum

    //event information
    @Getter @Setter
    @Column(name = "BusinessUnitID", nullable = false)
    private int businessUnitID;

    //event information
    @Getter @Setter
    @Column(name = "AccessUserID", nullable = false)
    private int accessUserID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name ="EquipmentID", nullable = false)
    private Equipment equipment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name = "EquipmentTypeID", nullable = false)
    private EquipmentType equipmentType;

    //verificar se movementType existe quando se persiste um movement
    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name = "MovementTypeID", nullable = false)
    private MovementType movementType;

    //message event
    @Getter @Setter
    @Column(name = "BookingEquipmentID")
    private int bookingEquipmentID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name = "EquipmentServiceID", nullable = false)
    private EquipmentService equipmentService;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name ="EquipmentConditionID", nullable = false)
    private EquipmentCondition equipmentCondition;



    @ManyToOne//(cascade = CascadeType.PERSIST)
    @Getter @Setter
    @JoinColumn(name ="EquipmentLeasingID", nullable = false)
    private EquipmentLeasing equipmentLeasing;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Getter @Setter
    @JoinColumn(name="TransportMeansID", nullable = false)
    private TransportMeans transportMeans;

    @Getter @Setter
    @Column(name = "TransportResponsibility", nullable = false)
    private String transportResponsibility;

    @Getter @Setter
    @Column(name ="MovementOfHire", nullable = false)
    private String movementOfHire;

    @Getter @Setter
    @Column(name = "MovementComment")
    private String movementComment;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "EquipmentStatusID", nullable = false)
    private EquipmentStatus equipmentStatus;

    //event information
    @Getter @Setter
    @Column(name = "MovementFromID")
    private int movementFromID;
    //event information
    @Getter @Setter
    @Column(name = "MovementToID")
    private int movementToID;

    //event information
    @Getter @Setter
    @Column(name = "MovementFinalID")
    private int movementFinalID;
    //event information
    @Getter @Setter
    @Column(name = "MovementRestitutionCode", nullable = false)
    private int movementRestitutionCode;

    //event information
    @Getter @Setter
    @Column(name = "MovementVoyageID", nullable = false)
    private int movementVoyageID;

    @Getter @Setter
    @Column(name = "MovementTransport", nullable = false)
    private String movementTransport;

    //event information
    @Getter @Setter
    @Column(name = "EquipmentOwnerID", nullable = false)
    private int equipmentOwnerID;

    @Getter @Setter
    @Column(name = "MovementDays", nullable = false)
    private int movementDays;

    @Getter @Setter
    @Column(name = "IsMovementLast", nullable = false)
    private boolean isMovementLast;

    @Getter @Setter
    @Column(name = "ShipmentUCN", nullable = false)
    private String shipmentUCN;

}
