package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS;


//DTO que retorna movimentos especificos para o contentor selecionado
//para retornar quando o utilizador seleciona um equipamento
public class EquipmentMovementDTO {
    private String movementKey;
    private String movementCode;
    private String movementStatus;
    private String movementDate;
    private String movementTime;
    private String equipmentServiceCode;
    private String conditionCode;
    private String movementBound;
    private String leasingCode;
    private boolean transhipmentCode;
    private boolean empty;
    private String shippingCode;
    private String sealNumberA;
    private String sealNumberB;
    private String comments;
    private String transportDetails;
    private String releaseReference;
    private String transportResponse;
    private String transportMeansCode;
    private String haulier;
    private String vehicleReference;
    private String from;
    private String fromName;
    private String to;
    private String toName;
    private String finalLocation;
    private String finalName;
    private String line;
    private String equipmentOwnerCode;
    private String offHire;
    private String agentCode;
    private String clientCode;
    private String shipperCode;
    private String consigneeCode;
    private String transportCompany;
    private String vessel;
    private String voyage;
    private String transportBound;
    private String transportService;
    private String bookingProcess;
    private String billOfLandingNumber;
    private String bookingReference;
    private String UCN;
    private int numberOfPacks;
    private double netWeight;
    private double grossWeight;
    private double temperature;
    private String temperatureUnit;
    private String commodityCode;
    private String commodityName;
    private String commodityExpireDate;
    private String equipmentDescription;
    private String goodsExportDeclaration;
    private String customsDispatchDocument;
    private double goodsValue;
    private String situation;
    private String customSeal;
    private String userID;
    private String auditDate;
    private String auditTime;
    private String depotCode;
    private String areaCode;
    private String locationCode;
    private String street;
    private String row;
    private String stack;

    public EquipmentMovementDTO(String movementKey, String movementCode, String movementStatus, String movementDate,
                                String movementTime, String equipmentServiceCode, String conditionCode, String movementBound,
                                String leasingCode, boolean transhipmentCode, boolean empty, String shippingCode,
                                String sealNumberA, String sealNumberB, String comments, String transportDetails,
                                String releaseReference, String transportResponse, String transportMeansCode, String haulier,
                                String vehicleReference, String from, String fromName, String to, String toName, String finalLocation,
                                String finalName, String line, String equipmentOwnerCode, String offHire, String agentCode, String clientCode,
                                String shipperCode, String consigneeCode, String transportCompany, String vessel, String voyage,
                                String transportBound, String transportService, String bookingProcess, String billOfLandingNumber,
                                String bookingReference, String UCN, int numberOfPacks, double netWeight,
                                double grossWeight, double temperature, String temperatureUnit, String commodityCode,
                                String commodityName, String commodityExpireDate, String equipmentDescription,
                                String goodsExportDeclaration, String customsDispatchDocument, double goodsValue,
                                String situation, String customSeal, String userID, String auditDate, String auditTime,
                                String depotCode, String areaCode, String locationCode, String street, String row, String stack) {

        this.movementKey = movementKey;
        this.movementCode = movementCode;
        this.movementStatus = movementStatus;
        this.movementDate = movementDate;
        this.movementTime = movementTime;
        this.equipmentServiceCode = equipmentServiceCode;
        this.conditionCode = conditionCode;
        this.movementBound = movementBound;
        this.leasingCode = leasingCode;
        this.transhipmentCode = transhipmentCode;
        this.empty = empty;
        this.shippingCode = shippingCode;
        this.sealNumberA = sealNumberA;
        this.sealNumberB = sealNumberB;
        this.comments = comments;
        this.transportDetails = transportDetails;
        this.releaseReference = releaseReference;
        this.transportResponse = transportResponse;
        this.transportMeansCode = transportMeansCode;
        this.haulier = haulier;
        this.vehicleReference = vehicleReference;
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.toName = toName;
        this.finalLocation = finalLocation;
        this.finalName = finalName;
        this.line = line;
        this.equipmentOwnerCode = equipmentOwnerCode;
        this.offHire = offHire;
        this.agentCode = agentCode;
        this.clientCode = clientCode;
        this.shipperCode = shipperCode;
        this.consigneeCode = consigneeCode;
        this.transportCompany = transportCompany;
        this.vessel = vessel;
        this.voyage = voyage;
        this.transportBound = transportBound;
        this.transportService = transportService;
        this.bookingProcess = bookingProcess;
        this.billOfLandingNumber = billOfLandingNumber;
        this.bookingReference = bookingReference;
        this.UCN = UCN;
        this.numberOfPacks = numberOfPacks;
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.commodityCode = commodityCode;
        this.commodityName = commodityName;
        this.commodityExpireDate = commodityExpireDate;
        this.equipmentDescription = equipmentDescription;
        this.goodsExportDeclaration = goodsExportDeclaration;
        this.customsDispatchDocument = customsDispatchDocument;
        this.goodsValue = goodsValue;
        this.situation = situation;
        this.customSeal = customSeal;
        this.userID = userID;
        this.auditDate = auditDate;
        this.auditTime = auditTime;
        this.depotCode = depotCode;
        this.areaCode = areaCode;
        this.locationCode = locationCode;
        this.street = street;
        this.row = row;
        this.stack = stack;
    }

    public EquipmentMovementDTO() {
    }

    public String getMovementKey() {
        return movementKey;
    }

    public void setMovementKey(String movementKey) {
        this.movementKey = movementKey;
    }

    public String getMovementCode() {
        return movementCode;
    }

    public void setMovementCode(String movementCode) {
        this.movementCode = movementCode;
    }

    public String getMovementStatus() {
        return movementStatus;
    }

    public void setMovementStatus(String movementStatus) {
        this.movementStatus = movementStatus;
    }

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }

    public String getMovementTime() {
        return movementTime;
    }

    public void setMovementTime(String movementTime) {
        this.movementTime = movementTime;
    }

    public String getEquipmentServiceCode() {
        return equipmentServiceCode;
    }

    public void setEquipmentServiceCode(String equipmentServiceCode) {
        this.equipmentServiceCode = equipmentServiceCode;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public String getMovementBound() {
        return movementBound;
    }

    public void setMovementBound(String movementBound) {
        this.movementBound = movementBound;
    }

    public String getLeasingCode() {
        return leasingCode;
    }

    public void setLeasingCode(String leasingCode) {
        this.leasingCode = leasingCode;
    }

    public boolean isTranshipmentCode() {
        return transhipmentCode;
    }

    public void setTranshipmentCode(boolean transhipmentCode) {
        this.transhipmentCode = transhipmentCode;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTransportDetails() {
        return transportDetails;
    }

    public void setTransportDetails(String transportDetails) {
        this.transportDetails = transportDetails;
    }

    public String getReleaseReference() {
        return releaseReference;
    }

    public void setReleaseReference(String releaseReference) {
        this.releaseReference = releaseReference;
    }

    public String getTransportResponse() {
        return transportResponse;
    }

    public void setTransportResponse(String transportResponse) {
        this.transportResponse = transportResponse;
    }

    public String getTransportMeansCode() {
        return transportMeansCode;
    }

    public void setTransportMeansCode(String transportMeansCode) {
        this.transportMeansCode = transportMeansCode;
    }

    public String getHaulier() {
        return haulier;
    }

    public void setHaulier(String haulier) {
        this.haulier = haulier;
    }

    public String getVehicleReference() {
        return vehicleReference;
    }

    public void setVehicleReference(String vehicleReference) {
        this.vehicleReference = vehicleReference;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(String finalLocation) {
        this.finalLocation = finalLocation;
    }

    public String getFinalName() {
        return finalName;
    }

    public void setFinalName(String finalName) {
        this.finalName = finalName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getEquipmentOwnerCode() {
        return equipmentOwnerCode;
    }

    public void setEquipmentOwnerCode(String equipmentOwnerCode) {
        this.equipmentOwnerCode = equipmentOwnerCode;
    }

    public String getOffHire() {
        return offHire;
    }

    public void setOffHire(String offHire) {
        this.offHire = offHire;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getTransportBound() {
        return transportBound;
    }

    public void setTransportBound(String transportBound) {
        this.transportBound = transportBound;
    }

    public String getTransportService() {
        return transportService;
    }

    public void setTransportService(String transportService) {
        this.transportService = transportService;
    }

    public String getBookingProcess() {
        return bookingProcess;
    }

    public void setBookingProcess(String bookingProcess) {
        this.bookingProcess = bookingProcess;
    }

    public String getBillOfLandingNumber() {
        return billOfLandingNumber;
    }

    public void setBillOfLandingNumber(String billOfLandingNumber) {
        this.billOfLandingNumber = billOfLandingNumber;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getUCN() {
        return UCN;
    }

    public void setUCN(String UCN) {
        this.UCN = UCN;
    }

    public int getNumberOfPacks() {
        return numberOfPacks;
    }

    public void setNumberOfPacks(int numberOfPacks) {
        this.numberOfPacks = numberOfPacks;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
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

    public String getCommodityExpireDate() {
        return commodityExpireDate;
    }

    public void setCommodityExpireDate(String commodityExpireDate) {
        this.commodityExpireDate = commodityExpireDate;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
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

    public double getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(double goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getCustomSeal() {
        return customSeal;
    }

    public void setCustomSeal(String customSeal) {
        this.customSeal = customSeal;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

}


