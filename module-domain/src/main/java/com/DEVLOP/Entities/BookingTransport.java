package com.DEVLOP.Entities;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BookingTransport")
public class BookingTransport extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    //bookingtransportDetail
    @Column(name = "BookingtransportDetail", nullable = false, unique = true)
    private String bookingtransportDetail;

    //estes detalhes de shipment não deveriam vir de outra entidade?
    //transhipment
    @Column(name = "Transhipment", nullable = false, unique = true)
    private String transhipment;

    //fullEmpty
    //se for o container estar cheio, tem de ser uma boolean com um nome diferente do genero "is full"
    @Column(name = "FullEmpty", nullable = false, unique = true)
    private String fullEmpty;

    //bookingGoodsDetail
    @Column(name = "BookingGoodsDetail", nullable = false, unique = true)
    private String bookingGoodsDetail;

    //shipmentTemperature
    @Column(name = "ShipmentTemperature")
    private String shipmentTemperature;

    //shipmentTemperatureUnit
    @Column(name = "ShipmentTemperatureUnit")
    private String shipmentTemperatureUnit;

    //haulierID
    //foreign key
    //one haulier can have several bookings, but one booking can have one haulier ID
    @Column(name = "HaulierID", nullable = false)
    private int haulierID;

    //vehicleReference
    @Column(name = "VehicleReference", nullable = false, unique = true)
    private String vehicleReference;

    //stowageLocation não deveria estar ligada à tabela das localizações?
    @Column(name = "StowageLocation", nullable = false, unique = true)
    private String stowageLocation;

    //shipmentPackageNumber
    @Column(name = "ShipmentPackageNumber", nullable = false, unique = true)
    private int shipmentPackageNumber;
    //netWeight
    @Column(name = "NetWeight", nullable = false)
    private double netWeight;
    //sealNumberA
    @Column(name = "SealNumberA", nullable = false)
    private String sealNumberA;
    //sealNumberB
    @Column(name = "SealNumberB", nullable = false)
    private String sealNumberB;
    //moveTypeBound
    @Column(name = "MoveTypeBound", nullable = false)
    private String moveTypeBound;

    //depotCode
    //foreign key
    //one depot can have several bookings, but one booking can have one depot code
    //one to many
    @Column(name = "DepotCode", nullable = false)
    private String depotCode;

    //depotAreaCode
    @Column(name = "DepotAreaCode", nullable = false)
    private String depotAreaCode;

    //depotLocationCode
    //foreign key
    //one depot location can have several bookings, but one booking can have one depot location code
    //one to many
    @Column(name = "DepotLocationCode", nullable = false)
    private String depotLocationCode;

    //depotLocationStreet
    @Column(name = "DepotLocationStreet", nullable = false)
    private String depotLocationStreet;
    //depotLocationRow
    @Column(name = "DepotLocationRow", nullable = false)
    private String depotLocationRow;
    //depotLocationStacking
    @Column(name = "DepotLocationStacking", nullable = false)
    private String depotLocationStacking;

    //clientID
    //foreign key
    //one client can have several bookings, but one booking can have one client ID
    //one to many
    @Column(name = "ClientID", nullable = false)
    private int clientID;

    //shipperID
    //foreign key
    //one shipper can have several bookings, but one booking can have one shipper ID
    //one to many
    @Column(name = "ShipperID", nullable = false)
    private int shipperID;

    //consigneeID
    //foreign key
    //one consignee can have several bookings, but one booking can have one consignee ID
    //one to many
    @Column(name = "ConsigneeID", nullable = false)
    private int consigneeID;

    //notifyID
    //foreign key
    //one notify can have several bookings, but one booking can have one notify ID
    //one to many
    @Column(name = "NotifyID", nullable = false)
    private int notifyID;

    //agentID
    //foreign key
    //one agent can have several bookings, but one booking can have one agent ID
    //one to many
    @Column(name = "AgentID", nullable = false)
    private int agentID;

    //grossWeight
    @Column(name = "GrossWeight", nullable = false)
    private double grossWeight;
    //goodsExportDeclaration(DME)
    @Column(name = "GoodsExportDeclaration", nullable = false)
    private String goodsExportDeclaration;
    //customsDispatchDocument(DAU)
    @Column(name = "CustomsDispatchDocument", nullable = false)
    private String customsDispatchDocument;
    //customsSeal
    @Column(name = "CustomsSeal", nullable = false)
    private String customsSeal;

    //goodsValue
    //que data type é?
    @Column(name = "GoodsValue", nullable = false, unique = true)
    private String goodsValue;

    //situation
    @Column(name = "Situation")
    private String situation;

    //equipmentDateDischarge
    @Column(name = "EquipmentDateDischarge", nullable = false)
    private Timestamp equipmentDateDischarge;

    //commodityExpireDate
    @Column(name = "CommodityExpireDate", nullable = false)
    private Timestamp commodityExpireDate;

    //commodityCode
    //foreign key
    //one commodity can have several bookings, but one booking can have one commodity code
    //one to many
    @Column(name = "CommodityCode", nullable = false)
    private String commodityCode;

    //commodityName
    @Column(name = "commodityName", nullable = false)
    private String commodityName;

    public BookingTransport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingtransportDetail() {
        return bookingtransportDetail;
    }

    public void setBookingtransportDetail(String bookingtransportDetail) {
        this.bookingtransportDetail = bookingtransportDetail;
    }

    public String getTranshipment() {
        return transhipment;
    }

    public void setTranshipment(String transhipment) {
        this.transhipment = transhipment;
    }

    public String getFullEmpty() {
        return fullEmpty;
    }

    public void setFullEmpty(String fullEmpty) {
        this.fullEmpty = fullEmpty;
    }

    public String getBookingGoodsDetail() {
        return bookingGoodsDetail;
    }

    public void setBookingGoodsDetail(String bookingGoodsDetail) {
        this.bookingGoodsDetail = bookingGoodsDetail;
    }

    public String getShipmentTemperature() {
        return shipmentTemperature;
    }

    public void setShipmentTemperature(String shipmentTemperature) {
        this.shipmentTemperature = shipmentTemperature;
    }

    public String getShipmentTemperatureUnit() {
        return shipmentTemperatureUnit;
    }

    public void setShipmentTemperatureUnit(String shipmentTemperatureUnit) {
        this.shipmentTemperatureUnit = shipmentTemperatureUnit;
    }

    public int getHaulierID() {
        return haulierID;
    }

    public void setHaulierID(int haulierID) {
        this.haulierID = haulierID;
    }

    public String getVehicleReference() {
        return vehicleReference;
    }

    public void setVehicleReference(String vehicleReference) {
        this.vehicleReference = vehicleReference;
    }

    public String getStowageLocation() {
        return stowageLocation;
    }

    public void setStowageLocation(String stowageLocation) {
        this.stowageLocation = stowageLocation;
    }

    public int getShipmentPackageNumber() {
        return shipmentPackageNumber;
    }

    public void setShipmentPackageNumber(int shipmentPackageNumber) {
        this.shipmentPackageNumber = shipmentPackageNumber;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public String getSealNumberA() {
        return sealNumberA;
    }

    public void setSealNumberA(String sealNumberA) {
        this.sealNumberA = sealNumberA;
    }

    public String getSealNumberB() {
        return sealNumberB;
    }

    public void setSealNumberB(String sealNumberB) {
        this.sealNumberB = sealNumberB;
    }

    public String getMoveTypeBound() {
        return moveTypeBound;
    }

    public void setMoveTypeBound(String moveTypeBound) {
        this.moveTypeBound = moveTypeBound;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getDepotAreaCode() {
        return depotAreaCode;
    }

    public void setDepotAreaCode(String depotAreaCode) {
        this.depotAreaCode = depotAreaCode;
    }

    public String getDepotLocationCode() {
        return depotLocationCode;
    }

    public void setDepotLocationCode(String depotLocationCode) {
        this.depotLocationCode = depotLocationCode;
    }

    public String getDepotLocationStreet() {
        return depotLocationStreet;
    }

    public void setDepotLocationStreet(String depotLocationStreet) {
        this.depotLocationStreet = depotLocationStreet;
    }

    public String getDepotLocationRow() {
        return depotLocationRow;
    }

    public void setDepotLocationRow(String depotLocationRow) {
        this.depotLocationRow = depotLocationRow;
    }

    public String getDepotLocationStacking() {
        return depotLocationStacking;
    }

    public void setDepotLocationStacking(String depotLocationStacking) {
        this.depotLocationStacking = depotLocationStacking;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public int getConsigneeID() {
        return consigneeID;
    }

    public void setConsigneeID(int consigneeID) {
        this.consigneeID = consigneeID;
    }

    public int getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(int notifyID) {
        this.notifyID = notifyID;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getGoodsExportDeclaration() {
        return goodsExportDeclaration;
    }

    public void setGoodsExportDeclaration(String goodsExportDeclaration) {
        this.goodsExportDeclaration = goodsExportDeclaration;
    }

    public String getCustomsDispatchDocument() {
        return customsDispatchDocument;
    }

    public void setCustomsDispatchDocument(String customsDispatchDocument) {
        this.customsDispatchDocument = customsDispatchDocument;
    }

    public String getCustomsSeal() {
        return customsSeal;
    }

    public void setCustomsSeal(String customsSeal) {
        this.customsSeal = customsSeal;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Timestamp getEquipmentDateDischarge() {
        return equipmentDateDischarge;
    }

    public void setEquipmentDateDischarge(Timestamp equipmentDateDischarge) {
        this.equipmentDateDischarge = equipmentDateDischarge;
    }

    public Timestamp getCommodityExpireDate() {
        return commodityExpireDate;
    }

    public void setCommodityExpireDate(Timestamp commodityExpireDate) {
        this.commodityExpireDate = commodityExpireDate;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
