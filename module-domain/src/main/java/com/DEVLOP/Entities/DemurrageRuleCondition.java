package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "DemurrageRuleCondition")
public class DemurrageRuleCondition extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //accountID
    //primary key? então mas nao vem de account? so demonstra que tem de ser unique
    //foreign key? Neste sentido será one to many? a rule condition so pode ter uma account associada mas
    //uma account pode ter varias rule conditions associadas?
    @Column(name = "AccountID", nullable = false)
    private int accountID;

    //demurrageRuleKey
    @Column(name = "DemurrageRuleKey", nullable = false)
    private String demurrageRuleKey;

    //demurrageConditionKey
    //primary key desta entidade, tem de ser unique
    @Column(name = "DemurrageConditionKey", nullable = false)
    private String demurrageConditionKey;

    //demurrageConditionID
    @Column(name = "DemurrageConditionID", nullable = false)
    private int demurrageConditionID;
    //demurrageConditionValue
    @Column(name = "DemurrageConditionValue", nullable = false)
    private int demurrageConditionValue;
    //demurrageConditionOperator
    @Column(name = "DemurrageConditionOperator", nullable = false)
    private String demurrageConditionOperator;
    //demurrageConditionImplementedName
    @Column(name = "DemurrageConditionImplementedName", nullable = false)
    private String demurrageConditionImplementedName;


    public DemurrageRuleCondition() {
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

    public String getDemurrageConditionKey() {
        return demurrageConditionKey;
    }

    public void setDemurrageConditionKey(String demurrageConditionKey) {
        this.demurrageConditionKey = demurrageConditionKey;
    }

    public int getDemurrageConditionID() {
        return demurrageConditionID;
    }

    public void setDemurrageConditionID(int demurrageConditionID) {
        this.demurrageConditionID = demurrageConditionID;
    }

    public int getDemurrageConditionValue() {
        return demurrageConditionValue;
    }

    public void setDemurrageConditionValue(int demurrageConditionValue) {
        this.demurrageConditionValue = demurrageConditionValue;
    }

    public String getDemurrageConditionOperator() {
        return demurrageConditionOperator;
    }

    public void setDemurrageConditionOperator(String demurrageConditionOperator) {
        this.demurrageConditionOperator = demurrageConditionOperator;
    }

    public String getDemurrageConditionImplementedName() {
        return demurrageConditionImplementedName;
    }

    public void setDemurrageConditionImplementedName(String demurrageConditionImplementedName) {
        this.demurrageConditionImplementedName = demurrageConditionImplementedName;
    }
}
