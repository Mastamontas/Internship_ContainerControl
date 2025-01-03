package com.DEVLOP.PersistenceEntities;


import jakarta.persistence.*;

@Entity
@Table(name = "equipmentCostRule")
public class EquipmentCostRulePersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //movementTypeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementTypeCode", nullable = false)
    private String movementTypeCode;

    //equipmentTypeID
    //primary key desta entidade, tem de ser unique
    @Column(name = "equipmentTypeID", nullable = false)
    private int equipmentTypeID;

    //movementFromCode
    //como é que movement de from e to são primary keys de uma entidade equipment? Nao deveriam ser codigos de locations?
    @Column(name = "movementFromCode", nullable = false)
    private String movementFromCode;

    //movementToCode
    //como é que movement de from e to são primary keys de uma entidade equipment? Nao deveriam ser codigos de locations?
    @Column(name = "movementToCode", nullable = false)
    private String movementToCode;

    //transportMeansCode
    //igual, anotação de primary key, não deveria ser uma foreign key para a entidade de transport means?
    @Column(name = "transportMeansCode", nullable = false)
    private String transportMeansCode;

    //serviceTypeCode
    //igual, anotação de primary key, não deveria ser uma foreign key para a entidade de service type? O tipo de serviço
    //ser uma particularidade do custo de equipamento faz sentido?
    @Column(name = "serviceTypeCode", nullable = false)
    private String serviceTypeCode;

    //accountID
    //foreign key para account
    //equipment cost rule tem um accountID, mas um accountID pode ter mais que uma equip costrule
    //one to many
    @Column(name = "accountID", nullable = false)
    private int accountID;

    //equipmentCostEstimatedCost
    @Column(name = "equipmentCostEstimatedCost", nullable = false)
    private double equipmentCostEstimatedCost;
    //equipmentCostDescription
    @Column(name = "equipmentCostDescription", nullable = false)
    private String equipmentCostDescription;

    public EquipmentCostRulePersistenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementTypeCode() {
        return movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    public int getEquipmentTypeID() {
        return equipmentTypeID;
    }

    public void setEquipmentTypeID(int equipmentTypeID) {
        this.equipmentTypeID = equipmentTypeID;
    }

    public String getMovementFromCode() {
        return movementFromCode;
    }

    public void setMovementFromCode(String movementFromCode) {
        this.movementFromCode = movementFromCode;
    }

    public String getMovementToCode() {
        return movementToCode;
    }

    public void setMovementToCode(String movementToCode) {
        this.movementToCode = movementToCode;
    }

    public String getTransportMeansCode() {
        return transportMeansCode;
    }

    public void setTransportMeansCode(String transportMeansCode) {
        this.transportMeansCode = transportMeansCode;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getEquipmentCostEstimatedCost() {
        return equipmentCostEstimatedCost;
    }

    public void setEquipmentCostEstimatedCost(double equipmentCostEstimatedCost) {
        this.equipmentCostEstimatedCost = equipmentCostEstimatedCost;
    }

    public String getEquipmentCostDescription() {
        return equipmentCostDescription;
    }

    public void setEquipmentCostDescription(String equipmentCostDescription) {
        this.equipmentCostDescription = equipmentCostDescription;
    }
}
