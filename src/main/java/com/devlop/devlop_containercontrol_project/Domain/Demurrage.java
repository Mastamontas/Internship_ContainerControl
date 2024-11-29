package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Entity
@Table(name = "demurrage")
public class Demurrage extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    //bookingID
    //one to many
    @Column(name="bookingID", nullable = false)
    private int bookingID;


    //demurrageRuleKey
    //foreign key
    //many to many
    @Column(name = "demurrageRuleID")
    private String demurrageRuleID;

    //demurrageStartDate
    //aqui tem de ser time stamp
    @Column(name = "demurrageStartDate")
    private Timestamp demurrageStartDate;

    //demurrageMidDate
    @Column(name = "demurrageMidDate")
    private Timestamp demurrageMidDate;

    //demurrageEndDate
    @Column(name = "demurrageEndDate")
    private Timestamp demurrageEndDate;

    //terminalStatus
    //enumerator
    @Column(name = "terminalStatus", nullable = false)
    private String terminalStatus;

    //demurrageStatus
    //enumerator
    @Column(name = "demurrageStatus", nullable = false)
    private String demurrageStatus;

    //demurrageFreeDays
    @Column(name = "demurrageFreeDays")
    private int demurrageFreeDays;
    //demurrageCost
    @Column(name = "demurrageCost")
    private double demurrageCost;

    //demurrageCostApply string ou double?
    //será uma boolean?
    @Column(name = "demurrageCostApply")
    private String demurrageCostApply;

    //demurrageCostPrice
    @Column(name = "demurrageCostPrice")
    private double demurrageCostPrice;

    //demurrageDiscountType
    //será um enum?
    @Column(name = "demurrageDiscountType")
    private String demurrageDiscountType;

    //clientID
    //foreign key
    //um client pode ter várias demurrages, mas uma demurrage só pode ter um client
    @Column(name = "clientID")
    private int clientID;

    //invoiceID
    //foreign key
    //uma invoice pode ter várias demurrages, mas uma demurrage só pode ter uma invoice
    @Column(name = "invoiceID")
    private int invoiceID;

    //accountID
    //foreign key
    //um account pode ter várias demurrages, mas uma demurrage só pode ter um account
    @Column(name = "accountID")
    private int accountID;

    //equipmentID
    //many to one
    @Column(name="equipmentID", nullable = false)
    private int equipmentID;

    //startMoveCode
    //foreign key
    //um movement pode ter várias demurrages, mas uma demurrage so pode ter um movement
    //one to many
    @Column(name="startMovementID")
    private int startMovementID;

    //foreign key
    //one to many with movement
    @Column(name="midMovementID")
    private int midMovementID;

    //foreign key
    //one to many with movements
    @Column(name="endMovementID")
    private int endMovementID;

    //demurrageComments
    @Column(name = "demurrageComments")
    private String demurrageComments;

    public Timestamp getDemurrageEndDate() {
        return demurrageEndDate;
    }

    public Demurrage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getDemurrageRuleID() {
        return demurrageRuleID;
    }

    public void setDemurrageRuleID(String demurrageRuleID) {
        this.demurrageRuleID = demurrageRuleID;
    }

    public Timestamp getDemurrageStartDate() {
        return demurrageStartDate;
    }

    public void setDemurrageStartDate(Timestamp demurrageStartDate) {
        this.demurrageStartDate = demurrageStartDate;
    }

    public Timestamp getDemurrageMidDate() {
        return demurrageMidDate;
    }

    public void setDemurrageMidDate(Timestamp demurrageMidDate) {
        this.demurrageMidDate = demurrageMidDate;
    }

    public void setDemurrageEndDate(Timestamp demurrageEndDate) {
        this.demurrageEndDate = demurrageEndDate;
    }

    public String getTerminalStatus() {
        return terminalStatus;
    }

    public void setTerminalStatus(String terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

    public String getDemurrageStatus() {
        return demurrageStatus;
    }

    public void setDemurrageStatus(String demurrageStatus) {
        this.demurrageStatus = demurrageStatus;
    }

    public int getDemurrageFreeDays() {
        return demurrageFreeDays;
    }

    public void setDemurrageFreeDays(int demurrageFreeDays) {
        this.demurrageFreeDays = demurrageFreeDays;
    }

    public double getDemurrageCost() {
        return demurrageCost;
    }

    public void setDemurrageCost(double demurrageCost) {
        this.demurrageCost = demurrageCost;
    }

    public String getDemurrageCostApply() {
        return demurrageCostApply;
    }

    public void setDemurrageCostApply(String demurrageCostApply) {
        this.demurrageCostApply = demurrageCostApply;
    }

    public double getDemurrageCostPrice() {
        return demurrageCostPrice;
    }

    public void setDemurrageCostPrice(double demurrageCostPrice) {
        this.demurrageCostPrice = demurrageCostPrice;
    }

    public String getDemurrageDiscountType() {
        return demurrageDiscountType;
    }

    public void setDemurrageDiscountType(String demurrageDiscountType) {
        this.demurrageDiscountType = demurrageDiscountType;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getStartMovementID() {
        return startMovementID;
    }

    public void setStartMovementID(int startMovementID) {
        this.startMovementID = startMovementID;
    }

    public int getMidMovementID() {
        return midMovementID;
    }

    public void setMidMovementID(int midMovementID) {
        this.midMovementID = midMovementID;
    }

    public int getEndMovementID() {
        return endMovementID;
    }

    public void setEndMovementID(int endMovementID) {
        this.endMovementID = endMovementID;
    }

    public String getDemurrageComments() {
        return demurrageComments;
    }

    public void setDemurrageComments(String demurrageComments) {
        this.demurrageComments = demurrageComments;
    }
}
