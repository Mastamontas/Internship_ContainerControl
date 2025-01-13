package com.DEVLOP.Entities;/*

package com.devlop.devlop_containercontrol_project.Common.Domain.Entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bookingEquipment")
public class BookingEquipment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingEquipmentId", nullable = false, unique = true)
    private Long bookingEquipmentId;

    //createdOn
    @Column(name = "createdOn", nullable = false,updatable = false)
    private Timestamp createdOn;

    //lastUpdated
    @Column(name = "lastUpdated", nullable = false)
    private Timestamp lastUpdated;

    //tenantID
    @Column(name = "tenantID", nullable = false)
    private int tenantID;

    //dbStatus
    @Column(name = "dbStatus", nullable = false)
    private String dbStatus;

    //equipmentTypeID - foreign key
    //many to one
    @Column(name = "equipmentTypeID", nullable = false)
    private int equipmentTypeID;

    //EquipmentID - foreign key
    //many to one
    @Column(name = "EquipmentID")
    private int EquipmentID;

    //physicalConditionID - foreign key
    //o que é isto? o que representa?
    @Column(name = "physicalConditionID")
    private int physicalConditionID;

    //movementID - foreign key
    //o que é o movement? O que representa?
    @Column(name = "movementID")
    private int movementID;

    //terminalLoadLocationID - foreign key
    //
    @Column(name = "terminalLoadLocationID")
    private int terminalLoadLocationID;

    //terminalDischargeLocationID - foreign key
    //many to one
    //um booking equipment so tem um terminal de descarga, mas um terminal de descarga pode ter varios booking equipments
    @Column(name = "terminalDischargeLocationID")
    private int terminalDischargeLocationID;

    //parkLocationID - foreign key
    //vem da location, many to one
    @Column(name = "parkLocationID")
    private int parkLocationID;

    //transportMeansID - foreign key
    //many to one
    @Column(name = "transportMeansID")
    private int transportMeansID;

    //stateID - foreign key
    //one to many
    @Column(name = "stateID")
    private int stateID;

    //equipmentOwnerAccountID - foreign key
    //one to many
    @Column(name = "equipmentOwnerAccountID")
    private int equipmentOwnerAccountID;

    //businessUnitKey
    @Column(name = "businessUnitKey")
    private int businessUnitKey;

    //accessUserID ( nao devia ser uma foreign key?)
    @Column(name = "accessUserID")
    private int accessUserID;

    //businessStatusKey
    @Column(name = "businessStatusKey")
    private int businessStatusKey;

    //goodsDetails
    @Column(name = "goodsDetails")
    private String goodsDetails;

    //transhipment
    @Column(name = "transhipment")
    private String transhipment;

    //bookingDate
    @Column(name = "bookingDate")
    private Date bookingDate;

    //gateDate
    @Column(name = "gateDate")
    private Date gateDate;

    //loadingDate
    @Column(name = "loadingDate")
    private Date loadingDate;

    //releaseReference
    @Column(name = "releaseReference")
    private String releaseReference;

    //turnInReference
    @Column(name = "turnInReference")
    private String turnInReference;

    //transportDetails
    @Column(name = "transportDetails")
    private String transportDetails;

    //sealNumberA
    @Column(name = "sealNumberA")
    private String sealNumberA;

    //sealNumberB
    @Column(name = "sealNumberB")
    private String sealNumberB;

    //brand
    @Column(name = "brand")
    private String brand;

    //model
    @Column(name = "model")
    private String model;

    //chassis
    @Column(name = "chassis")
    private String chassis;

    //vehicleUsage
    @Column(name = "vehicleUsage")
    private char vehicleUsage;

    //vehicleCategory
    @Column(name = "vehicleCategory")
    private char vehicleCategory;
    //SOC
    @Column(name = "SOC", nullable = false)
    private char SOC;

    //VGMDocReferenceVerification
    @Column(name = "VGMDocReferenceVerification")
    private String VGMDocReferenceVerification;

    //VGMWeightDate
    @Column(name = "VGMWeightDate")
    private Date VGMWeightDate;

    //VGMWeightTime
    @Column(name = "VGMWeightTime")
    private Timestamp VGMWeightTime;

    //VGMWeight
    @Column(name = "VGMWeight")
    private double VGMWeight;

    //VGMMethod
    @Column(name = "VGMMethod")
    private String VGMMethod;

    //VGMAuthorizedOfficialName
    @Column(name = "VGMAuthorizedOfficialName")
    private String VGMAuthorizedOfficialName;

    //VGMDocumentNumber
    @Column(name = "VGMDocumentNumber")
    private String VGMDocumentNumber;

    //VGMPath
    @Column(name = "VGMPath")
    private String VGMPath;

    public BookingEquipment() {
    }

    public Long getBookingEquipmentId() {
        return bookingEquipmentId;
    }

    public void setBookingEquipmentId(Long bookingEquipmentId) {
        this.bookingEquipmentId = bookingEquipmentId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
    }

    public int getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(int equipmentTypeID) {
        this.equipmentTypeID = equipmentTypeID;
    }

    public int getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        EquipmentID = equipmentID;
    }

    public int getPhysicalConditionID() {
        return physicalConditionID;
    }

    public void setPhysicalConditionID(int physicalConditionID) {
        this.physicalConditionID = physicalConditionID;
    }

    public int getMovementID() {
        return movementID;
    }

    public void setMovementID(int movementID) {
        this.movementID = movementID;
    }

    public int getTerminalLoadLocationID() {
        return terminalLoadLocationID;
    }

    public void setTerminalLoadLocationID(int terminalLoadLocationID) {
        this.terminalLoadLocationID = terminalLoadLocationID;
    }

    public int getTerminalDischargeLocationID() {
        return terminalDischargeLocationID;
    }

    public void setTerminalDischargeLocationID(int terminalDischargeLocationID) {
        this.terminalDischargeLocationID = terminalDischargeLocationID;
    }

    public int getParkLocationID() {
        return parkLocationID;
    }

    public void setParkLocationID(int parkLocationID) {
        this.parkLocationID = parkLocationID;
    }

    public int getTransportMeansID() {
        return transportMeansID;
    }

    public void setTransportMeansID(int transportMeansID) {
        this.transportMeansID = transportMeansID;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getEquipmentOwnerAccountID() {
        return equipmentOwnerAccountID;
    }

    public void setEquipmentOwnerAccountID(int equipmentOwnerAccountID) {
        this.equipmentOwnerAccountID = equipmentOwnerAccountID;
    }

    public int getBusinessUnitKey() {
        return businessUnitKey;
    }

    public void setBusinessUnitKey(int businessUnitKey) {
        this.businessUnitKey = businessUnitKey;
    }

    public int getAccessUserID() {
        return accessUserID;
    }

    public void setAccessUserID(int accessUserID) {
        this.accessUserID = accessUserID;
    }

    public int getBusinessStatusKey() {
        return businessStatusKey;
    }

    public void setBusinessStatusKey(int businessStatusKey) {
        this.businessStatusKey = businessStatusKey;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getTranshipment() {
        return transhipment;
    }

    public void setTranshipment(String transhipment) {
        this.transhipment = transhipment;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getGateDate() {
        return gateDate;
    }

    public void setGateDate(Date gateDate) {
        this.gateDate = gateDate;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getReleaseReference() {
        return releaseReference;
    }

    public void setReleaseReference(String releaseReference) {
        this.releaseReference = releaseReference;
    }

    public String getTurnInReference() {
        return turnInReference;
    }

    public void setTurnInReference(String turnInReference) {
        this.turnInReference = turnInReference;
    }

    public String getTransportDetails() {
        return transportDetails;
    }

    public void setTransportDetails(String transportDetails) {
        this.transportDetails = transportDetails;
    }

    public String getSealNumberA() {
        return sealNumberA;
    }

    public void setSealNumberA(String sealNumberA) {
        this.sealNumberA = sealNumberA;
    }

    public String getSealNumberB() {
        return sealNumberB;
    }

    public void setSealNumberB(String sealNumberB) {
        this.sealNumberB = sealNumberB;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public char getVehicleUsage() {
        return vehicleUsage;
    }

    public void setVehicleUsage(char vehicleUsage) {
        this.vehicleUsage = vehicleUsage;
    }

    public char getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(char vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public char getSOC() {
        return SOC;
    }

    public void setSOC(char SOC) {
        this.SOC = SOC;
    }

    public String getVGMDocReferenceVerification() {
        return VGMDocReferenceVerification;
    }

    public void setVGMDocReferenceVerification(String VGMDocReferenceVerification) {
        this.VGMDocReferenceVerification = VGMDocReferenceVerification;
    }

    public Date getVGMWeightDate() {
        return VGMWeightDate;
    }

    public void setVGMWeightDate(Date VGMWeightDate) {
        this.VGMWeightDate = VGMWeightDate;
    }

    public Timestamp getVGMWeightTime() {
        return VGMWeightTime;
    }

    public void setVGMWeightTime(Timestamp VGMWeightTime) {
        this.VGMWeightTime = VGMWeightTime;
    }

    public double getVGMWeight() {
        return VGMWeight;
    }

    public void setVGMWeight(double VGMWeight) {
        this.VGMWeight = VGMWeight;
    }

    public String getVGMMethod() {
        return VGMMethod;
    }

    public void setVGMMethod(String VGMMethod) {
        this.VGMMethod = VGMMethod;
    }

    public String getVGMAuthorizedOfficialName() {
        return VGMAuthorizedOfficialName;
    }

    public void setVGMAuthorizedOfficialName(String VGMAuthorizedOfficialName) {
        this.VGMAuthorizedOfficialName = VGMAuthorizedOfficialName;
    }

    public String getVGMDocumentNumber() {
        return VGMDocumentNumber;
    }

    public void setVGMDocumentNumber(String VGMDocumentNumber) {
        this.VGMDocumentNumber = VGMDocumentNumber;
    }

    public String getVGMPath() {
        return VGMPath;
    }

    public void setVGMPath(String VGMPath) {
        this.VGMPath = VGMPath;
    }
}

*/
