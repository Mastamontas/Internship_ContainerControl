package com.DEVLOP.PersistenceEntities;


import jakarta.persistence.*;

@Entity
@Table(name = "movementLink")
public class MovementLinkPersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //movementLinkType
    //primary key desta entidade, tem de ser unique. É aqui que se inserem os nexts? demurrage?
    @Column(name = "movementLinkType", nullable = false)
    private String movementLinkType;

    //accountID
    //como é que pode ser primary key? Nao tem de ser foreign key para a tabela account?
    @Column(name = "accountID", nullable = false)
    private int accountID;
    //movementName
    @Column(name = "movementName", nullable = false)
    private String movementName;
    //accountVATNumber
    @Column(name = "accountVATNumber", nullable = false)
    private int accountVATNumber;

    //movementLinkOutCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementLinkOutCode", nullable = false)
    private String movementLinkOutCode;
    //movementLinkOutName
    @Column(name = "movementLinkOutName", nullable = false)
    private String movementLinkOutName;

    //movementEquipmentStatusOutCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementEquipmentStatusOutCode", nullable = false)
    private String movementEquipmentStatusOutCode;

    //movementFromOutCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementFromOutCode", nullable = false)
    private String movementFromOutCode;

    //movementToOutCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementToOutCode", nullable = false)
    private String movementToOutCode;

    //movementConditionOutCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementConditionOutCode", nullable = false)
    private String movementConditionOutCode;

    //movementTypeOutBound
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementTypeOutBound", nullable = false)
    private String movementTypeOutBound;

    //movementLinkInCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementLinkInCode", nullable = false)
    private String movementLinkInCode;

    //movementLinkInName
    @Column(name = "movementLinkInName", nullable = false)
    private String movementLinkInName;

    //movementEquipmentStatusInCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementEquipmentStatusInCode", nullable = false)
    private String movementEquipmentStatusInCode;

    //movementFromInCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementFromInCode", nullable = false)
    private String movementFromInCode;

    //movementToInCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementToInCode", nullable = false)
    private String movementToInCode;

    //movementConditionInCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementConditionInCode", nullable = false)
    private String movementConditionInCode;

    //movementTypeInBound
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementTypeInBound", nullable = false)
    private String movementTypeInBound;

    //outLocationField
    @Column(name = "outLocationField", nullable = false)
    private String outLocationField;
    //inLocationField
    @Column(name = "inLocationField", nullable = false)
    private String inLocationField;

    public MovementLinkPersistenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementLinkType() {
        return movementLinkType;
    }

    public void setMovementLinkType(String movementLinkType) {
        this.movementLinkType = movementLinkType;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getMovementName() {
        return movementName;
    }

    public void setMovementName(String movementName) {
        this.movementName = movementName;
    }

    public int getAccountVATNumber() {
        return accountVATNumber;
    }

    public void setAccountVATNumber(int accountVATNumber) {
        this.accountVATNumber = accountVATNumber;
    }

    public String getMovementLinkOutCode() {
        return movementLinkOutCode;
    }

    public void setMovementLinkOutCode(String movementLinkOutCode) {
        this.movementLinkOutCode = movementLinkOutCode;
    }

    public String getMovementLinkOutName() {
        return movementLinkOutName;
    }

    public void setMovementLinkOutName(String movementLinkOutName) {
        this.movementLinkOutName = movementLinkOutName;
    }

    public String getMovementEquipmentStatusOutCode() {
        return movementEquipmentStatusOutCode;
    }

    public void setMovementEquipmentStatusOutCode(String movementEquipmentStatusOutCode) {
        this.movementEquipmentStatusOutCode = movementEquipmentStatusOutCode;
    }

    public String getMovementFromOutCode() {
        return movementFromOutCode;
    }

    public void setMovementFromOutCode(String movementFromOutCode) {
        this.movementFromOutCode = movementFromOutCode;
    }

    public String getMovementToOutCode() {
        return movementToOutCode;
    }

    public void setMovementToOutCode(String movementToOutCode) {
        this.movementToOutCode = movementToOutCode;
    }

    public String getMovementConditionOutCode() {
        return movementConditionOutCode;
    }

    public void setMovementConditionOutCode(String movementConditionOutCode) {
        this.movementConditionOutCode = movementConditionOutCode;
    }

    public String getMovementTypeOutBound() {
        return movementTypeOutBound;
    }

    public void setMovementTypeOutBound(String movementTypeOutBound) {
        this.movementTypeOutBound = movementTypeOutBound;
    }

    public String getMovementLinkInCode() {
        return movementLinkInCode;
    }

    public void setMovementLinkInCode(String movementLinkInCode) {
        this.movementLinkInCode = movementLinkInCode;
    }

    public String getMovementLinkInName() {
        return movementLinkInName;
    }

    public void setMovementLinkInName(String movementLinkInName) {
        this.movementLinkInName = movementLinkInName;
    }

    public String getMovementEquipmentStatusInCode() {
        return movementEquipmentStatusInCode;
    }

    public void setMovementEquipmentStatusInCode(String movementEquipmentStatusInCode) {
        this.movementEquipmentStatusInCode = movementEquipmentStatusInCode;
    }

    public String getMovementFromInCode() {
        return movementFromInCode;
    }

    public void setMovementFromInCode(String movementFromInCode) {
        this.movementFromInCode = movementFromInCode;
    }

    public String getMovementToInCode() {
        return movementToInCode;
    }

    public void setMovementToInCode(String movementToInCode) {
        this.movementToInCode = movementToInCode;
    }

    public String getMovementConditionInCode() {
        return movementConditionInCode;
    }

    public void setMovementConditionInCode(String movementConditionInCode) {
        this.movementConditionInCode = movementConditionInCode;
    }

    public String getMovementTypeInBound() {
        return movementTypeInBound;
    }

    public void setMovementTypeInBound(String movementTypeInBound) {
        this.movementTypeInBound = movementTypeInBound;
    }

    public String getOutLocationField() {
        return outLocationField;
    }

    public void setOutLocationField(String outLocationField) {
        this.outLocationField = outLocationField;
    }

    public String getInLocationField() {
        return inLocationField;
    }

    public void setInLocationField(String inLocationField) {
        this.inLocationField = inLocationField;
    }
}
