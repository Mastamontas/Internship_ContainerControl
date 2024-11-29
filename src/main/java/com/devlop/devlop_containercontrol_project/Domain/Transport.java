package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table (name="transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private Long id;
    @Column(name = "createdOn", nullable = false, updatable = false)
    private Timestamp createdOn;

    //lastUpdated
    @Column(name = "lastUpdatedOn", nullable = false)
    private Timestamp lastUpdatedOn;

    //tenantID
    @Column(name = "tenantID", nullable = false)
    private int tenantID;

    @Column(name = "dbStatus", nullable = false)
    private String dbStatus;

    //tradeline
    //one to one
    @Column(name = "tradeLine", nullable = false, unique = true)
    private int tradeLine; //foreign key

    //tradeID
    //one to many? O que quer dizer trade aqui? Pode ser um mercado? por exemplo TradeID textil
    @Column(name = "tradeID", nullable = false, unique = true)
    private int tradeID; //foreign key

    //voyageID
    //one to one
    @Column(name = "voyageID", nullable = false, unique = true)
    private int voyageID; //foreign key
    //businessUnitID
    @Column(name = "businessUnitID")
    private int businessUnitID;
    //businessStatusID
    @Column(name = "businessStatusID")
    private int businessStatusID;
    //accessUserID
    @Column(name = "accessUserID", unique = true)
    private int accessUserID;
    //transportDate
    @Column(name = "transportDate")
    private Date transportDate;
    //transportExchange
    @Column(name = "transportExchange")
    private Date transportExchange;
    //transportAccounting
    @Column(name = "transportAccounting")
    private String transportAccounting;
    //transportState
    @Column(name = "transportState")
    private String transportState;
    //transportComments
    @Column(name = "transportComments")
    private String transportComments;

    public Transport() {
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

    public String getDbStatus() {
        return dbStatus;
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
    }

    public int getTradeLine() {
        return tradeLine;
    }

    public void setTradeLine(int tradeLine) {
        this.tradeLine = tradeLine;
    }

    public int getTradeID() {
        return tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }

    public int getVoyageID() {
        return voyageID;
    }

    public void setVoyageID(int voyageID) {
        this.voyageID = voyageID;
    }

    public int getBusinessUnitID() {
        return businessUnitID;
    }

    public void setBusinessUnitID(int businessUnitID) {
        this.businessUnitID = businessUnitID;
    }

    public int getBusinessStatusID() {
        return businessStatusID;
    }

    public void setBusinessStatusID(int businessStatusID) {
        this.businessStatusID = businessStatusID;
    }

    public int getAccessUserID() {
        return accessUserID;
    }

    public void setAccessUserID(int accessUserID) {
        this.accessUserID = accessUserID;
    }

    public Date getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(Date transportDate) {
        this.transportDate = transportDate;
    }

    public Date getTransportExchange() {
        return transportExchange;
    }

    public void setTransportExchange(Date transportExchange) {
        this.transportExchange = transportExchange;
    }

    public String getTransportAccounting() {
        return transportAccounting;
    }

    public void setTransportAccounting(String transportAccounting) {
        this.transportAccounting = transportAccounting;
    }

    public String getTransportState() {
        return transportState;
    }

    public void setTransportState(String transportState) {
        this.transportState = transportState;
    }

    public String getTransportComments() {
        return transportComments;
    }

    public void setTransportComments(String transportComments) {
        this.transportComments = transportComments;
    }
}
