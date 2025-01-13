package com.DEVLOP.Entities;

import java.sql.Timestamp;
import jakarta.persistence.*;
@Entity
@Table(name = "Movement")
public class Movement extends BaseEntity{

    //aqui é só ID, não movement ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovementID", nullable = false, unique = true)
    private int movementID;

    //businessUnitID
    //foreignKEY
    //um movimento pode ter uma businessUnit ou várias?
    @Column(name = "BusinessUnitID", nullable = false)
    private int businessUnitID;

    //businessUnitShortNumber?
    @Column(name = "BusinessUnitShortNumber", nullable = false)
    private int businessUnitShortNumber;

    @Column(name ="EquipmentID", nullable = false)
    private int equipmentID;

    //accessUserID
    //foreignKEY
    //um user por ter vários movements, mas um movement só tem um accessUserID
    //one to many
    @Column(name = "AccessUserID", nullable = false)
    private int accessUserID;

    //equipmentPrefix
    //foreignKEY
    //um equipmentPrefix pode ter vários movements, mas um movement só tem um equipmentPrefix
    //one to many
    @Column(name = "EquipmentPrefix", nullable = false)
    private String equipmentPrefix;
    //equipmentNumber
    @Column(name = "EquipmentNumber", nullable = false)
    private int equipmentNumber;
    //equipmentCheckDigit
    @Column(name = "EquipmentCheckDigit", nullable = false)
    private int equipmentCheckDigit;

    //movementTypeCode
    //primary key desta entidade, codigo tem de ser unique
    @Column(name = "MovementTypeCode", nullable = false, unique = true)
    private String movementTypeCode;

    //movementKey
    //igual, esta movement key é unica para o movimento
    @Column(name = "MovementKey", nullable = false, unique = true)
    private String movementKey;

    //movementDate
    @Column(name = "MovementDate", nullable = false)
    private Timestamp movementDate;
    //movementTime
    @Column(name = "MovementTime", nullable = false)
    private Timestamp movementTime;
    //movementBookingRegistration
    @Column(name = "MovementBookingRegistration", nullable = false)
    private String movementBookingRegistration;
    //bookingReleaseRef
    @Column(name = "BookingReleaseRef", nullable = false)
    private String bookingReleaseRef;
    //movementBLReference
    @Column(name = "MovementBLReference", nullable = false)
    private String movementBLReference;

    //equipmentServiceCode?
    //foreignKEY
    //um equipmentServiceCode pode ter vários movements, mas um movement só tem um equipmentServiceCode
    //one to many
    @Column(name = "EquipmentServiceCode", nullable = false)
    private String equipmentServiceCode;

    //shipmentConditionCode?
    //shipping condition é uma entidade externa, este codigo é um evento
    //foreignKEY
    //um shipmentConditionCode pode ter vários movements, mas um movement só tem um shipmentConditionCode
    //one to many
    @Column(name = "ShipmentConditionCode", nullable = false)
    private String shipmentConditionCode;

    //physicalConditionCode
    //foreignKEY
    //um physicalConditionCode pode ter vários movements, mas um movement só tem um physicalConditionCode
    //one to many
    @Column(name = "PhysicalConditionCode", nullable = false)
    private String physicalConditionCode;

    //leasingContractCode
    //foreignKEY
    //um leasingContractCode pode ter vários movements, mas um movement só tem um leasingContractCode
    //one to many
    @Column(name = "LeasingContractCode", nullable = false)
    private String leasingContractCode;

    //transportMeansCode
    //entidade precisa de ser reformulada para incluir campo de codigo
    //foreignKEY
    //um transportMeansCode pode ter vários movements, mas um movement só tem um transportMeansCode
    //one to many
    @Column(name = "TransportMeansCode", nullable = false)
    private String transportMeansCode;

    //transportResponsibility
    //alterar table que tem um erro
    @Column(name = "TransportResponsability", nullable = false)
    private String transportResponsability;
    //movementOfHire
    @Column(name = "MovementOfHire", nullable = false)
    private String movementOfHire;
    //movementOfComment
    @Column(name = "MovementComment", nullable = false)
    private String movementComment;

    //equipmentStatusCode
    //foreign key
    //um movimento so pode ter um eq status code, mas um eq status code pode ter varios movimentos
    @Column(name = "EquipmentStatusCode", nullable = false)
    private String equipmentStatusCode;

    //movementFromCode
    //foreign key com location?
    @Column(name = "MovementFromCode", nullable = false)
    private String movementFromCode;
    //movementFromName
    @Column(name = "MovementFromName", nullable = false)
    private String movementFromName;
    //movementToCode
    //igual à anterior, vem de location?
    @Column(name = "MovementToCode", nullable = false)
    private String movementToCode;
    //movementToName
    @Column(name = "MovementToName", nullable = false)
    private String movementToName;
    //movementFinalCode
    //foreign key de location
    @Column(name = "MovementFinalCode", nullable = false)
    private String movementFinalCode;
    //movementFinalName
    @Column(name = "MovementFinalName", nullable = false)
    private String movementFinalName;
    //movementRestitutionCode
    @Column(name = "MovementRestitutionCode", nullable = false)
    private String movementRestitutionCode;

    //movementRestitutionName
    //esta foreign key vem de onde? Tambem de location?
    @Column(name = "MovementRestitutionName", nullable = false)
    private String movementRestitutionName;

    //movementLineID
    //este movement line ID vem de onde?
    //foreign key um movement so pode ter um movement line id, mas um movement line id pode ter varios movements
    //one to many
    @Column(name = "MovementLineID", nullable = false)
    private int movementLineID;

    //movementVesselCode
    //foreign key codigo de vessel. Outra entidade externa que é um evento. Pode ser feito por ID ou por um código unico
    //one to many
    @Column(name = "MovementVesselCode", nullable = false)
    private String movementVesselCode;
    //movementVoyageNumber
    @Column(name = "MovementVoyageNumber", nullable = false)
    private String movementVoyageNumber;
    //movementServiceCode
    @Column(name = "MovementServiceCode", nullable = false)
    private String movementServiceCode;
    //movementServiceBound?
    @Column(name = "MovementServiceBound", nullable = false)
    private String movementServiceBound;

    //movementProcessKey
    //foreign key - ha alguma entidade de movement process?
    //um movement so pode ter um movement process key, mas um movement process key pode ter varios movements?
    @Column(name = "MovementProcessKey", nullable = false)
    private String movementProcessKey;

    //movementTransport
    //ID de transport?
    //foreign key
    //um movement so pode ter um transport, mas o mesmo transport pode ter vários movements?
    @Column(name = "MovementTransport", nullable = false)
    private String movementTransport;

    //equipmentOwnerID
    //foreign key
    //vem de equipment -> owner ID
    @Column(name = "EquipmentOwnerID", nullable = false)
    private int equipmentOwnerID;

    //movementDays
    //calculated?
    @Column(name = "MovementDays", nullable = false)
    private int movementDays;
    //movementLast
    @Column(name = "MovementLast", nullable = false)
    private int movementLast;
    //equipmentTypeID
    @Column(name = "EquipmentTypeID", nullable = false)
    private int equipmentTypeID;
    //equipmentTypeCode
    @Column(name = "EquipmentTypeCode", nullable = false)
    private String equipmentTypeCode;
    //equipmentTypeLength
    @Column(name = "EquipmentTypeLength", nullable = false)
    private int equipmentTypeLength;
    //shipmentUCN
    @Column(name = "ShipmentUCN", nullable = false)
    private String shipmentUCN;
    //bookingTransportID
    @Column(name = "BookingTransportID", nullable = false)
    private int bookingTransportID;



    public Movement() {
    }

    public int getMovementID() {
        return movementID;
    }

    public void setMovementID(int movementID) {
        this.movementID = movementID;
    }

    public int getBusinessUnitID() {
        return businessUnitID;
    }

    public void setBusinessUnitID(int businessUnitID) {
        this.businessUnitID = businessUnitID;
    }

    public int getBusinessUnitShortNumber() {
        return businessUnitShortNumber;
    }

    public void setBusinessUnitShortNumber(int businessUnitShortNumber) {
        this.businessUnitShortNumber = businessUnitShortNumber;
    }

    public int getAccessUserID() {
        return accessUserID;
    }

    public void setAccessUserID(int accessUserID) {
        this.accessUserID = accessUserID;
    }

    public String getEquipmentPrefix() {
        return equipmentPrefix;
    }

    public void setEquipmentPrefix(String equipmentPrefix) {
        this.equipmentPrefix = equipmentPrefix;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public int getEquipmentCheckDigit() {
        return equipmentCheckDigit;
    }

    public void setEquipmentCheckDigit(int equipmentCheckDigit) {
        this.equipmentCheckDigit = equipmentCheckDigit;
    }

    public String getMovementTypeCode() {
        return movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    public String getMovementKey() {
        return movementKey;
    }

    public void setMovementKey(String movementKey) {
        this.movementKey = movementKey;
    }

    public Timestamp getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Timestamp movementDate) {
        this.movementDate = movementDate;
    }

    public Timestamp getMovementTime() {
        return movementTime;
    }

    public void setMovementTime(Timestamp movementTime) {
        this.movementTime = movementTime;
    }

    public String getMovementBookingRegistration() {
        return movementBookingRegistration;
    }

    public void setMovementBookingRegistration(String movementBookingRegistration) {
        this.movementBookingRegistration = movementBookingRegistration;
    }

    public String getBookingReleaseRef() {
        return bookingReleaseRef;
    }

    public void setBookingReleaseRef(String bookingReleaseRef) {
        this.bookingReleaseRef = bookingReleaseRef;
    }

    public String getMovementBLReference() {
        return movementBLReference;
    }

    public void setMovementBLReference(String movementBLReference) {
        this.movementBLReference = movementBLReference;
    }

    public String getEquipmentServiceCode() {
        return equipmentServiceCode;
    }

    public void setEquipmentServiceCode(String equipmentServiceCode) {
        this.equipmentServiceCode = equipmentServiceCode;
    }

    public String getShipmentConditionCode() {
        return shipmentConditionCode;
    }

    public void setShipmentConditionCode(String shipmentConditionCode) {
        this.shipmentConditionCode = shipmentConditionCode;
    }

    public String getPhysicalConditionCode() {
        return physicalConditionCode;
    }

    public void setPhysicalConditionCode(String physicalConditionCode) {
        this.physicalConditionCode = physicalConditionCode;
    }

    public String getLeasingContractCode() {
        return leasingContractCode;
    }

    public void setLeasingContractCode(String leasingContractCode) {
        this.leasingContractCode = leasingContractCode;
    }

    public String getTransportMeansCode() {
        return transportMeansCode;
    }

    public void setTransportMeansCode(String transportMeansCode) {
        this.transportMeansCode = transportMeansCode;
    }

    public String getTransportResponsability() {
        return transportResponsability;
    }

    public void setTransportResponsability(String transportResponsability) {
        this.transportResponsability = transportResponsability;
    }

    public String getMovementOfHire() {
        return movementOfHire;
    }

    public void setMovementOfHire(String movementOfHire) {
        this.movementOfHire = movementOfHire;
    }

    public String getMovementComment() {
        return movementComment;
    }

    public void setMovementComment(String movementComment) {
        this.movementComment = movementComment;
    }

    public String getEquipmentStatusCode() {
        return equipmentStatusCode;
    }

    public void setEquipmentStatusCode(String equipmentStatusCode) {
        this.equipmentStatusCode = equipmentStatusCode;
    }

    public String getMovementFromCode() {
        return movementFromCode;
    }

    public void setMovementFromCode(String movementFromCode) {
        this.movementFromCode = movementFromCode;
    }

    public String getMovementFromName() {
        return movementFromName;
    }

    public void setMovementFromName(String movementFromName) {
        this.movementFromName = movementFromName;
    }

    public String getMovementToCode() {
        return movementToCode;
    }

    public void setMovementToCode(String movementToCode) {
        this.movementToCode = movementToCode;
    }

    public String getMovementToName() {
        return movementToName;
    }

    public void setMovementToName(String movementToName) {
        this.movementToName = movementToName;
    }

    public String getMovementFinalCode() {
        return movementFinalCode;
    }

    public void setMovementFinalCode(String movementFinalCode) {
        this.movementFinalCode = movementFinalCode;
    }

    public String getMovementFinalName() {
        return movementFinalName;
    }

    public void setMovementFinalName(String movementFinalName) {
        this.movementFinalName = movementFinalName;
    }

    public String getMovementRestitutionCode() {
        return movementRestitutionCode;
    }

    public void setMovementRestitutionCode(String movementRestitutionCode) {
        this.movementRestitutionCode = movementRestitutionCode;
    }

    public String getMovementRestitutionName() {
        return movementRestitutionName;
    }

    public void setMovementRestitutionName(String movementRestitutionName) {
        this.movementRestitutionName = movementRestitutionName;
    }

    public int getMovementLineID() {
        return movementLineID;
    }

    public void setMovementLineID(int movementLineID) {
        this.movementLineID = movementLineID;
    }

    public String getMovementVesselCode() {
        return movementVesselCode;
    }

    public void setMovementVesselCode(String movementVesselCode) {
        this.movementVesselCode = movementVesselCode;
    }

    public String getMovementVoyageNumber() {
        return movementVoyageNumber;
    }

    public void setMovementVoyageNumber(String movementVoyageNumber) {
        this.movementVoyageNumber = movementVoyageNumber;
    }

    public String getMovementServiceCode() {
        return movementServiceCode;
    }

    public void setMovementServiceCode(String movementServiceCode) {
        this.movementServiceCode = movementServiceCode;
    }

    public String getMovementServiceBound() {
        return movementServiceBound;
    }

    public void setMovementServiceBound(String movementServiceBound) {
        this.movementServiceBound = movementServiceBound;
    }

    public String getMovementProcessKey() {
        return movementProcessKey;
    }

    public void setMovementProcessKey(String movementProcessKey) {
        this.movementProcessKey = movementProcessKey;
    }

    public String getMovementTransport() {
        return movementTransport;
    }

    public void setMovementTransport(String movementTransport) {
        this.movementTransport = movementTransport;
    }

    public int getEquipmentOwnerID() {
        return equipmentOwnerID;
    }

    public void setEquipmentOwnerID(int equipmentOwnerID) {
        this.equipmentOwnerID = equipmentOwnerID;
    }

    public int getMovementDays() {
        return movementDays;
    }

    public void setMovementDays(int movementDays) {
        this.movementDays = movementDays;
    }

    public int getMovementLast() {
        return movementLast;
    }

    public void setMovementLast(int movementLast) {
        this.movementLast = movementLast;
    }

    public int getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(int equipmentTypeID) {
        this.equipmentTypeID = equipmentTypeID;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public int getEquipmentTypeLength() {
        return equipmentTypeLength;
    }

    public void setEquipmentTypeLength(int equipmentTypeLength) {
        this.equipmentTypeLength = equipmentTypeLength;
    }

    public String getShipmentUCN() {
        return shipmentUCN;
    }

    public void setShipmentUCN(String shipmentUCN) {
        this.shipmentUCN = shipmentUCN;
    }

    public int getBookingTransportID() {
        return bookingTransportID;
    }

    public void setBookingTransportID(int bookingTransportID) {
        this.bookingTransportID = bookingTransportID;
    }
}
