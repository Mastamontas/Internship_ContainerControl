package com.DEVLOP.Entities;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "DemurrageRule")
public class DemurrageRule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //businessUnitID
    //foreign key
    @Column(name = "BusinessUnitID", nullable = false)
    private int businessUnitID;

    //accessUserID
    //foreign key? Vem de entidade Access User? User?
    //ID para evento externo
    @Column(name = "AccessUserID", nullable = false)
    private int accessUserID;

    //accountID - como é que isto pode ser primary key se vem de entidade account?
    //foreign key
    @Column(name = "AccountID", nullable = false)
    private int accountID;

    //demurrageRuleKey
    //primary key de entidade, tem de ser unique
    @Column(name = "DemurrageRuleKey", nullable = false)
    private String demurrageRuleKey;

    //demurrageRuleDescription
    @Column(name = "DemurrageRuleDescription")
    private String demurrageRuleDescription;

    //demurrageRuleOrder
    @Column(name = "DemurrageRuleOrder")
    private int demurrageRuleOrder;

    //demurrageRuleStartDate
    @Column(name = "DemurrageRuleStartDate", nullable = false)
    private Timestamp demurrageRuleStartDate;

    //demurrageRuleExpirationDate
    @Column(name = "DemurrageRuleExpirationDate")
    private Timestamp demurrageRuleExpirationDate;

    //demurrageRulePayer
    //enumerator
    @Column(name = "DemurrageRulePayer", nullable = false)
    private String demurrageRulePayer;

    //clientID
    //foreign key para account?
    //uma demurrage rule tem apenas um client id, mas um client id pode ter varias demurrage rules
    @Column(name = "ClientID")
    private int clientID;

    //demurrageRuleWeekend
    //verificar este atributo
    @Column(name = "DemurrageRuleWeekend", nullable = false)
    private boolean demurrageRuleWeekend;

    //demurrageRuleTerminalFreeNumber
    @Column(name = "DemurrageRuleTerminalFreeNumber")
    private int demurrageRuleTerminalFreeNumber;
    //demurrageRuleTerminalFirst
    @Column(name = "DemurrageRuleTerminalFirst")
    private int demurrageRuleTerminalFirst;
    //demurrageRuleTerminalFirstCost
    @Column(name = "DemurrageRuleTerminalFirstCost")
    private double demurrageRuleTerminalFirstCost;
    //demurrageRuleTerminalSecond
    @Column(name = "DemurrageRuleTerminalSecond")
    private int demurrageRuleTerminalSecond;
    //demurrageRuleTerminalSecondCost
    @Column(name = "DemurrageRuleTerminalSecondCost")
    private double demurrageRuleTerminalSecondCost;
    //demurrageRuleTerminalThirdCost
    @Column(name = "DemurrageRuleTerminalThirdCost")
    private double demurrageRuleTerminalThirdCost;

    //costCurrencyCode - isto é um currency code? pode ser so aplicado um currency code por rule?
    //foreign key para currency? Terá de ser evento externo para currency
    //one to many, uma dem rule so pode ter um currency code mas o mesmo currency code pode ter varios dem
    @Column(name = "CostCurrencyCode")
    private int costCurrencyCode;

    //demurrageRuleAgentCommission
    @Column(name = "DemurrageRuleAgentCommission")
    private double demurrageRuleAgentCommission;

    //demurrageRuleAgentCommissionMultiple
    @Column(name = "DemurrageRuleAgentCommissionMultiple")
    private double demurrageRuleAgentCommissionMultiple;

    //demurrageRuleFreeNumber
    @Column(name = "DemurrageRuleFreeNumber")
    private int demurrageRuleFreeNumber;
    //demurrageRuleFirstPeriodNumber
    @Column(name = "DemurrageRuleFirstPeriodNumber")
    private int demurrageRuleFirstPeriodNumber;
    //demurrageRuleSecondPeriodNumber
    @Column(name = "DemurrageRuleSecondPeriodNumber")
    private int demurrageRuleSecondPeriodNumber;
    //demurrageRuleThirdPeriodNumber
    @Column(name = "DemurrageRuleThirdPeriodNumber")
    private int demurrageRuleThirdPeriodNumber;
    //demurrageRuleFirstPeriodPrice
    @Column(name = "DemurrageRuleFirstPeriodPrice")
    private double demurrageRuleFirstPeriodPrice;
    //demurrageRuleSecondPeriodPrice
    @Column(name = "DemurrageRuleSecondPeriodPrice")
    private double demurrageRuleSecondPeriodPrice;
    //demurrageRuleThirdPeriodPrice
    @Column(name = "DemurrageRuleThirdPeriodPrice")
    private double demurrageRuleThirdPeriodPrice;
    //demurrageRuleLastPeriodPrice
    @Column(name = "DemurrageRuleLastPeriodPrice")
    private double demurrageRuleLastPeriodPrice;



    //demurrageRuleCurrencyID
    //foreign key
    @Column(name="DemurrageRuleCurrencyID")
    private int demurrageRuleCurrencyID;

    //payCurrencyCode
    //foreign key - igual ao de cima
    @Column(name="PayCurrencyID", nullable = false)
    private int payCurrencyID;

    @Column(name = "PayCurrencyCode")
    private String payCurrencyCode;

    //demurrageRuleExchange
    @Column(name = "DemurrageRuleExchange", nullable = false)
    private String demurrageRuleExchange;

    //serviceTypeCode
    //foreign key
    @Column(name = "ServiceTypeCode", nullable = false)
    private int serviceTypeCode;

    //demurrageRuleComments
    @Column(name = "DemurrageRuleComments")
    private String demurrageRuleComments;

    public DemurrageRule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBusinessUnitID() {
        return businessUnitID;
    }

    public void setBusinessUnitID(int businessUnitID) {
        this.businessUnitID = businessUnitID;
    }

    public int getAccessUserID() {
        return accessUserID;
    }

    public void setAccessUserID(int accessUserID) {
        this.accessUserID = accessUserID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getDemurrageRuleKey() {
        return demurrageRuleKey;
    }

    public void setDemurrageRuleKey(String demurrageRuleKey) {
        this.demurrageRuleKey = demurrageRuleKey;
    }

    public String getDemurrageRuleDescription() {
        return demurrageRuleDescription;
    }

    public void setDemurrageRuleDescription(String demurrageRuleDescription) {
        this.demurrageRuleDescription = demurrageRuleDescription;
    }

    public int getDemurrageRuleOrder() {
        return demurrageRuleOrder;
    }

    public void setDemurrageRuleOrder(int demurrageRuleOrder) {
        this.demurrageRuleOrder = demurrageRuleOrder;
    }

    public Timestamp getDemurrageRuleStartDate() {
        return demurrageRuleStartDate;
    }

    public void setDemurrageRuleStartDate(Timestamp demurrageRuleStartDate) {
        this.demurrageRuleStartDate = demurrageRuleStartDate;
    }

    public Timestamp getDemurrageRuleExpirationDate() {
        return demurrageRuleExpirationDate;
    }

    public void setDemurrageRuleExpirationDate(Timestamp demurrageRuleExpirationDate) {
        this.demurrageRuleExpirationDate = demurrageRuleExpirationDate;
    }

    public String getDemurrageRulePayer() {
        return demurrageRulePayer;
    }

    public void setDemurrageRulePayer(String demurrageRulePayer) {
        this.demurrageRulePayer = demurrageRulePayer;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public boolean isDemurrageRuleWeekend() {
        return demurrageRuleWeekend;
    }

    public void setDemurrageRuleWeekend(boolean demurrageRuleWeekend) {
        this.demurrageRuleWeekend = demurrageRuleWeekend;
    }

    public int getDemurrageRuleTerminalFreeNumber() {
        return demurrageRuleTerminalFreeNumber;
    }

    public void setDemurrageRuleTerminalFreeNumber(int demurrageRuleTerminalFreeNumber) {
        this.demurrageRuleTerminalFreeNumber = demurrageRuleTerminalFreeNumber;
    }

    public int getDemurrageRuleTerminalFirst() {
        return demurrageRuleTerminalFirst;
    }

    public void setDemurrageRuleTerminalFirst(int demurrageRuleTerminalFirst) {
        this.demurrageRuleTerminalFirst = demurrageRuleTerminalFirst;
    }

    public double getDemurrageRuleTerminalFirstCost() {
        return demurrageRuleTerminalFirstCost;
    }

    public void setDemurrageRuleTerminalFirstCost(double demurrageRuleTerminalFirstCost) {
        this.demurrageRuleTerminalFirstCost = demurrageRuleTerminalFirstCost;
    }

    public int getDemurrageRuleTerminalSecond() {
        return demurrageRuleTerminalSecond;
    }

    public void setDemurrageRuleTerminalSecond(int demurrageRuleTerminalSecond) {
        this.demurrageRuleTerminalSecond = demurrageRuleTerminalSecond;
    }

    public double getDemurrageRuleTerminalSecondCost() {
        return demurrageRuleTerminalSecondCost;
    }

    public void setDemurrageRuleTerminalSecondCost(double demurrageRuleTerminalSecondCost) {
        this.demurrageRuleTerminalSecondCost = demurrageRuleTerminalSecondCost;
    }

    public double getDemurrageRuleTerminalThirdCost() {
        return demurrageRuleTerminalThirdCost;
    }

    public void setDemurrageRuleTerminalThirdCost(double demurrageRuleTerminalThirdCost) {
        this.demurrageRuleTerminalThirdCost = demurrageRuleTerminalThirdCost;
    }

    public int getCostCurrencyCode() {
        return costCurrencyCode;
    }

    public void setCostCurrencyCode(int costCurrencyCode) {
        this.costCurrencyCode = costCurrencyCode;
    }

    public double getDemurrageRuleAgentCommission() {
        return demurrageRuleAgentCommission;
    }

    public void setDemurrageRuleAgentCommission(double demurrageRuleAgentCommission) {
        this.demurrageRuleAgentCommission = demurrageRuleAgentCommission;
    }

    public double getDemurrageRuleAgentCommissionMultiple() {
        return demurrageRuleAgentCommissionMultiple;
    }

    public void setDemurrageRuleAgentCommissionMultiple(double demurrageRuleAgentCommissionMultiple) {
        this.demurrageRuleAgentCommissionMultiple = demurrageRuleAgentCommissionMultiple;
    }

    public int getDemurrageRuleFreeNumber() {
        return demurrageRuleFreeNumber;
    }

    public void setDemurrageRuleFreeNumber(int demurrageRuleFreeNumber) {
        this.demurrageRuleFreeNumber = demurrageRuleFreeNumber;
    }

    public int getDemurrageRuleFirstPeriodNumber() {
        return demurrageRuleFirstPeriodNumber;
    }

    public void setDemurrageRuleFirstPeriodNumber(int demurrageRuleFirstPeriodNumber) {
        this.demurrageRuleFirstPeriodNumber = demurrageRuleFirstPeriodNumber;
    }

    public int getDemurrageRuleSecondPeriodNumber() {
        return demurrageRuleSecondPeriodNumber;
    }

    public void setDemurrageRuleSecondPeriodNumber(int demurrageRuleSecondPeriodNumber) {
        this.demurrageRuleSecondPeriodNumber = demurrageRuleSecondPeriodNumber;
    }

    public int getDemurrageRuleThirdPeriodNumber() {
        return demurrageRuleThirdPeriodNumber;
    }

    public void setDemurrageRuleThirdPeriodNumber(int demurrageRuleThirdPeriodNumber) {
        this.demurrageRuleThirdPeriodNumber = demurrageRuleThirdPeriodNumber;
    }

    public double getDemurrageRuleFirstPeriodPrice() {
        return demurrageRuleFirstPeriodPrice;
    }

    public void setDemurrageRuleFirstPeriodPrice(double demurrageRuleFirstPeriodPrice) {
        this.demurrageRuleFirstPeriodPrice = demurrageRuleFirstPeriodPrice;
    }

    public double getDemurrageRuleSecondPeriodPrice() {
        return demurrageRuleSecondPeriodPrice;
    }

    public void setDemurrageRuleSecondPeriodPrice(double demurrageRuleSecondPeriodPrice) {
        this.demurrageRuleSecondPeriodPrice = demurrageRuleSecondPeriodPrice;
    }

    public double getDemurrageRuleThirdPeriodPrice() {
        return demurrageRuleThirdPeriodPrice;
    }

    public void setDemurrageRuleThirdPeriodPrice(double demurrageRuleThirdPeriodPrice) {
        this.demurrageRuleThirdPeriodPrice = demurrageRuleThirdPeriodPrice;
    }

    public double getDemurrageRuleLastPeriodPrice() {
        return demurrageRuleLastPeriodPrice;
    }

    public void setDemurrageRuleLastPeriodPrice(double demurrageRuleLastPeriodPrice) {
        this.demurrageRuleLastPeriodPrice = demurrageRuleLastPeriodPrice;
    }

    public int getDemurrageRuleCurrencyID() {
        return demurrageRuleCurrencyID;
    }

    public void setDemurrageRuleCurrencyID(int demurrageRuleCurrencyID) {
        this.demurrageRuleCurrencyID = demurrageRuleCurrencyID;
    }

    public int getPayCurrencyID() {
        return payCurrencyID;
    }

    public void setPayCurrencyID(int payCurrencyID) {
        this.payCurrencyID = payCurrencyID;
    }

    public String getPayCurrencyCode() {
        return payCurrencyCode;
    }

    public void setPayCurrencyCode(String payCurrencyCode) {
        this.payCurrencyCode = payCurrencyCode;
    }

    public String getDemurrageRuleExchange() {
        return demurrageRuleExchange;
    }

    public void setDemurrageRuleExchange(String demurrageRuleExchange) {
        this.demurrageRuleExchange = demurrageRuleExchange;
    }

    public int getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(int serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public String getDemurrageRuleComments() {
        return demurrageRuleComments;
    }

    public void setDemurrageRuleComments(String demurrageRuleComments) {
        this.demurrageRuleComments = demurrageRuleComments;
    }
}
