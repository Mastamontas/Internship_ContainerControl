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
    private LocalDateTime date;

    //event information
    @Getter @Setter
    @Column(name = "BusinessUnitID", nullable = false)
    private int businessUnitID;

    //event information
    @Getter @Setter
    @Column(name = "AccessUserID", nullable = false)
    private int accessUserID;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name ="EquipmentID", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "EquipmentTypeID", nullable = false)
    private EquipmentType equipmentType;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "MovementTypeID", nullable = false, unique = true)
    private MovementType movementType;

    //message event
    @Getter @Setter
    @Column(name = "BookingEquipmentID")
    private int bookingEquipmentID;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "EquipmentServiceID", nullable = false)
    private EquipmentService equipmentService;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name ="EquipmentConditionID", nullable = false)
    private EquipmentCondition equipmentCondition;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name ="EquipmentLeasingID", nullable = false)
    private EquipmentLeasing equipmentLeasing;

    @ManyToOne
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
    @Column(name = "MovementComment", nullable = false)
    private String movementComment;

    @Getter @Setter
    @Column(name = "EquipmentStatusID", nullable = false)
    private String equipmentStatusID;

    //event information
    @Getter @Setter
    @Column(name = "MovementFromID")
    private String movementFromID;
    //event information
    @Getter @Setter
    @Column(name = "MovementToID")
    private String movementToID;

    //event information
    @Getter @Setter
    @Column(name = "MovementFinalID")
    private String movementFinalID;
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
