package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "demurrageControl")
public class DemurrageControl extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    //foreign key
    @Column(name="bookingID", nullable = false)
    private int bookingID;

    //foreign key
    @Column(name="demurrageID", nullable = false)
    private int demurrageID;

    //demurrageControlType
    //enum?
    @Column(name = "demurrageControlType", nullable = false)
    private String demurrageControlType;

    //demurrageControlHolidays
    @Column(name = "demurrageControlHolidays")
    private int demurrageControlHolidays;

    //demurrageControlDate
    //aqui tem de ser timestamp
    @Column(name = "demurrageControlDate", nullable = false)
    private Timestamp demurrageControlDate;

    //demurrageCost
    @Column(name = "demurrageCost")
    private double demurrageCost;

    //demurrageCostDiscount
    @Column(name = "demurrageCostDiscount")
    private double demurrageCostDiscount;

    //invoiceCompanyID
    //foreign key
    //uma demurrage control so tem uma invoice ID e uma invoice ID so tem uma demurrage control?
    //one to one
    @Column(name = "invoiceCompanyID")
    private int invoiceCompanyID;

    //invoiceKey - as keys devem ser strings ou ints?
    @Column(name = "invoiceKey")
    private int invoiceKey;
    //invoiceDetailKey
    @Column(name = "invoiceDetailKey")
    private int invoiceDetailKey;
    //demurrageComments
    @Column(name = "demurrageComments")
    private String demurrageComments;

    public DemurrageControl() {
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

    public int getDemurrageID() {
        return demurrageID;
    }

    public void setDemurrageID(int demurrageID) {
        this.demurrageID = demurrageID;
    }

    public String getDemurrageControlType() {
        return demurrageControlType;
    }

    public void setDemurrageControlType(String demurrageControlType) {
        this.demurrageControlType = demurrageControlType;
    }

    public int getDemurrageControlHolidays() {
        return demurrageControlHolidays;
    }

    public void setDemurrageControlHolidays(int demurrageControlHolidays) {
        this.demurrageControlHolidays = demurrageControlHolidays;
    }

    public Timestamp getDemurrageControlDate() {
        return demurrageControlDate;
    }

    public void setDemurrageControlDate(Timestamp demurrageControlDate) {
        this.demurrageControlDate = demurrageControlDate;
    }

    public double getDemurrageCost() {
        return demurrageCost;
    }

    public void setDemurrageCost(double demurrageCost) {
        this.demurrageCost = demurrageCost;
    }

    public double getDemurrageCostDiscount() {
        return demurrageCostDiscount;
    }

    public void setDemurrageCostDiscount(double demurrageCostDiscount) {
        this.demurrageCostDiscount = demurrageCostDiscount;
    }

    public int getInvoiceCompanyID() {
        return invoiceCompanyID;
    }

    public void setInvoiceCompanyID(int invoiceCompanyID) {
        this.invoiceCompanyID = invoiceCompanyID;
    }

    public int getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(int invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public int getInvoiceDetailKey() {
        return invoiceDetailKey;
    }

    public void setInvoiceDetailKey(int invoiceDetailKey) {
        this.invoiceDetailKey = invoiceDetailKey;
    }

    public String getDemurrageComments() {
        return demurrageComments;
    }

    public void setDemurrageComments(String demurrageComments) {
        this.demurrageComments = demurrageComments;
    }
}
