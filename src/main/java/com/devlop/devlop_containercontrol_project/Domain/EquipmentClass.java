package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "equipmentClass")
public class EquipmentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //createdOn
    @Column(name = "createdOn", nullable = false, updatable = false)
    private Timestamp createdOn;

    //lastUpdate
    @Column(name = "lastUpdate", nullable = false)
    private Timestamp lastUpdate;

    //tenantID
    @Column(name = "tenantID", nullable = false, unique = true)
    private int tenantID;

    //dbStatus
    @Column(name = "dbStatus", nullable = false)
    private String dbStatus;

    //equipmentCode
    @Column(name = "equipmentCode", nullable = false, unique = true)
    private int equipmentCode;

    //equipmentName
    @Column(name = "equipmentName")
    private String equipmentName;

    //equipmentType
    @Column(name = "equipmentType")
    private String equipmentType;

    public EquipmentClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public int getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(int equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
