package com.DEVLOP.Entities;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "DemurrageControl")
public class DemurrageControl extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    //foreign key
    @Column(name="BookingID", nullable = false)
    private int bookingID;

    //foreign key
    @Column(name="DemurrageID", nullable = false)
    private int demurrageID;

    //demurrageControlType
    //enum?
    @Column(name = "DemurrageControlType", nullable = false)
    private String demurrageControlType;

    //demurrageControlHolidays
    @Column(name = "DemurrageControlHolidays")
    private int demurrageControlHolidays;

    //demurrageControlDate
    //aqui tem de ser timestamp
    @Column(name = "DemurrageControlDate", nullable = false)
    private Timestamp demurrageControlDate;

    //demurrageCost
    @Column(name = "DemurrageCost")
    private double demurrageCost;

    //demurrageCostDiscount
    @Column(name = "DemurrageCostDiscount")
    private double demurrageCostDiscount;

    //invoiceCompanyID
    //foreign key
    //uma demurrage control so tem uma invoice ID e uma invoice ID so tem uma demurrage control?
    //one to one
    @Column(name = "InvoiceCompanyID")
    private int invoiceCompanyID;

    //invoiceKey - as keys devem ser strings ou ints?
    @Column(name = "InvoiceKey")
    private int invoiceKey;
    //invoiceDetailKey
    @Column(name = "InvoiceDetailKey")
    private int invoiceDetailKey;
    //demurrageComments
    @Column(name = "DemurrageComments")
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
