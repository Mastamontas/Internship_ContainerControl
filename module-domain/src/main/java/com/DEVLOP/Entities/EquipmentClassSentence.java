package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "EquipmentClassSentence")
public class EquipmentClassSentence extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //equipmentClassCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "EquipmentClassCode", nullable = false, unique = true)
    private String equipmentClassCode;

    //tradeLineID
    //primary key desta entidade, tem de ser unique
    @Column(name = "TradeLineID", nullable = false, unique = true)
    private int tradeLineID;

    //loadingCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "LoadingCode", nullable = false, unique = true)
    private String loadingCode;

    //dischargeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "DischargeCode", nullable = false, unique = true)
    private String dischargeCode;

    //shippingConditionCode
    //como é que este atributo é primary key desta entidade? Não tem de vir do shipping?
    @Column(name = "ShippingConditionCode", nullable = false, unique = true)
    private String shippingConditionCode;
    //equipmentClassSentencePattern1
    @Column(name = "EquipmentClassSentencePattern1", nullable = false, unique = true)
    private String equipmentClassSentencePattern1;
    //equipmentClassSentencePattern2
    @Column(name = "EquipmentClassSentencePattern2", nullable = false, unique = true)
    private String equipmentClassSentencePattern2;

    public EquipmentClassSentence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentClassCode() {
        return equipmentClassCode;
    }

    public void setEquipmentClassCode(String equipmentClassCode) {
        this.equipmentClassCode = equipmentClassCode;
    }

    public int getTradeLineID() {
        return tradeLineID;
    }
    public void setTradeLineID(int tradeLineID) {
        this.tradeLineID = tradeLineID;
    }

    public String getLoadingCode() {
        return loadingCode;
    }

    public void setLoadingCode(String loadingCode) {
        this.loadingCode = loadingCode;
    }

    public String getDischargeCode() {
        return dischargeCode;
    }

    public void setDischargeCode(String dischargeCode) {
        this.dischargeCode = dischargeCode;
    }

    public String getShippingConditionCode() {
        return shippingConditionCode;
    }

    public void setShippingConditionCode(String shippingConditionCode) {
        this.shippingConditionCode = shippingConditionCode;
    }

    public String getEquipmentClassSentencePattern1() {
        return equipmentClassSentencePattern1;
    }

    public void setEquipmentClassSentencePattern1(String equipmentClassSentencePattern1) {
        this.equipmentClassSentencePattern1 = equipmentClassSentencePattern1;
    }

    public String getEquipmentClassSentencePattern2() {
        return equipmentClassSentencePattern2;
    }

    public void setEquipmentClassSentencePattern2(String equipmentClassSentencePattern2) {
        this.equipmentClassSentencePattern2 = equipmentClassSentencePattern2;
    }
}
