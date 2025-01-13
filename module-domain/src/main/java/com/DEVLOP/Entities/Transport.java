package com.DEVLOP.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.*;
@Entity
@Table (name="Transport")
public class Transport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    @Column(name ="Id", unique = true, nullable = false)
    private int id;
    //tradeline
    //one to one
    @Column(name = "TradeLine", nullable = false, unique = true)
    private int tradeLine; //foreign key

    //tradeID
    //one to many? O que quer dizer trade aqui? Pode ser um mercado? por exemplo TradeID textil
    @Column(name = "TradeID", nullable = false, unique = true)
    private int tradeID; //foreign key

    //voyageID
    //one to one
    @Column(name = "VoyageID", nullable = false, unique = true)
    private int voyageID; //foreign key
    //businessUnitID
    @Column(name = "BusinessUnitID")
    private int businessUnitID;
    //businessStatusID
    @Column(name = "BusinessStatusID")
    private int businessStatusID;
    //accessUserID
    @Column(name = "AccessUserID", unique = true)
    private int accessUserID;
    //transportDate
    @Column(name = "TransportDate")
    private Date transportDate;
    //transportExchange
    @Column(name = "TransportExchange")
    private Date transportExchange;
    //transportAccounting
    @Column(name = "TransportAccounting")
    private String transportAccounting;
    //transportState
    @Column(name = "TransportState")
    private String transportState;
    //transportComments
    @Column(name = "TransportComments")
    private String transportComments;

    public Transport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
