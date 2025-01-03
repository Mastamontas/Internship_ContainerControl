package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "demurrageRuleLineDetail")
public class DemurrageRuleLineDetailPersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //accountID
    //novamente como primary key? Este account ID é so o ID desta entidade que usam como primary key?
    @Column(name = "accountID", nullable = false)
    private int accountID;

    //demurrageRuleKey
    @Column(name = "demurrageRuleKey", nullable = false)
    private String demurrageRuleKey;

    //demurrageRuleLineDetailKey
    //primary key desta entidade, tem de ser unique
    @Column(name = "demurrageRuleLineDetailKey", nullable = false)
    private String demurrageRuleLineDetailKey;

    //terminalTariffCompanyValue1
    @Column(name = "terminalTariffCompanyValue1", nullable = false)
    private int terminalTariffCompanyValue1;
    //terminalTariffCompanyValue2
    @Column(name = "terminalTariffCompanyValue2", nullable = false)
    private int terminalTariffCompanyValue2;
    //terminalTariffCompanyValue3
    @Column(name = "terminalTariffCompanyValue3", nullable = false)
    private int terminalTariffCompanyValue3;
    //demurrageTariffCompanyValue1
    @Column(name = "demurrageTariffCompanyValue1", nullable = false)
    private int demurrageTariffCompanyValue1;
    //demurrageTariffCompanyValue2
    @Column(name = "demurrageTariffCompanyValue2", nullable = false)
    private int demurrageTariffCompanyValue2;
    //demurrageTariffCompanyValue3
    @Column(name = "demurrageTariffCompanyValue3", nullable = false)
    private int demurrageTariffCompanyValue3;
    //demurrageTariffCompanyValue4
    @Column(name = "demurrageTariffCompanyValue4", nullable = false)
    private int demurrageTariffCompanyValue4;

    //currencyCode
    //foreign key de currency? Tem de ser evento.
    //one to many
    @Column(name = "currencyCode", nullable = false)
    private String currencyCode;
    //currencyName
    @Column(name = "currencyName", nullable = false)
    private String currencyName;

    public DemurrageRuleLineDetailPersistenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDemurrageRuleLineDetailKey() {
        return demurrageRuleLineDetailKey;
    }

    public void setDemurrageRuleLineDetailKey(String demurrageRuleLineDetailKey) {
        this.demurrageRuleLineDetailKey = demurrageRuleLineDetailKey;
    }

    public int getTerminalTariffCompanyValue1() {
        return terminalTariffCompanyValue1;
    }

    public void setTerminalTariffCompanyValue1(int terminalTariffCompanyValue1) {
        this.terminalTariffCompanyValue1 = terminalTariffCompanyValue1;
    }

    public int getTerminalTariffCompanyValue2() {
        return terminalTariffCompanyValue2;
    }

    public void setTerminalTariffCompanyValue2(int terminalTariffCompanyValue2) {
        this.terminalTariffCompanyValue2 = terminalTariffCompanyValue2;
    }

    public int getTerminalTariffCompanyValue3() {
        return terminalTariffCompanyValue3;
    }

    public void setTerminalTariffCompanyValue3(int terminalTariffCompanyValue3) {
        this.terminalTariffCompanyValue3 = terminalTariffCompanyValue3;
    }

    public int getDemurrageTariffCompanyValue1() {
        return demurrageTariffCompanyValue1;
    }

    public void setDemurrageTariffCompanyValue1(int demurrageTariffCompanyValue1) {
        this.demurrageTariffCompanyValue1 = demurrageTariffCompanyValue1;
    }

    public int getDemurrageTariffCompanyValue2() {
        return demurrageTariffCompanyValue2;
    }

    public void setDemurrageTariffCompanyValue2(int demurrageTariffCompanyValue2) {
        this.demurrageTariffCompanyValue2 = demurrageTariffCompanyValue2;
    }

    public int getDemurrageTariffCompanyValue3() {
        return demurrageTariffCompanyValue3;
    }

    public void setDemurrageTariffCompanyValue3(int demurrageTariffCompanyValue3) {
        this.demurrageTariffCompanyValue3 = demurrageTariffCompanyValue3;
    }

    public int getDemurrageTariffCompanyValue4() {
        return demurrageTariffCompanyValue4;
    }

    public void setDemurrageTariffCompanyValue4(int demurrageTariffCompanyValue4) {
        this.demurrageTariffCompanyValue4 = demurrageTariffCompanyValue4;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
