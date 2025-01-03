package com.DEVLOP.PersistenceEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "demurrageRuleCondition")
public class DemurrageRuleConditionPersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //accountID
    //primary key? então mas nao vem de account? so demonstra que tem de ser unique
    //foreign key? Neste sentido será one to many? a rule condition so pode ter uma account associada mas
    //uma account pode ter varias rule conditions associadas?
    @Column(name = "accountID", nullable = false)
    private int accountID;

    //demurrageRuleKey
    @Column(name = "demurrageRuleKey", nullable = false)
    private String demurrageRuleKey;

    //demurrageConditionKey
    //primary key desta entidade, tem de ser unique
    @Column(name = "demurrageConditionKey", nullable = false)
    private String demurrageConditionKey;

    //demurrageConditionID
    @Column(name = "demurrageConditionID", nullable = false)
    private int demurrageConditionID;
    //demurrageConditionValue
    @Column(name = "demurrageConditionValue", nullable = false)
    private int demurrageConditionValue;
    //demurrageConditionOperator
    @Column(name = "demurrageConditionOperator", nullable = false)
    private String demurrageConditionOperator;
    //demurrageConditionImplementedName
    @Column(name = "demurrageConditionImplementedName", nullable = false)
    private String demurrageConditionImplementedName;


    public DemurrageRuleConditionPersistenceEntity() {
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
