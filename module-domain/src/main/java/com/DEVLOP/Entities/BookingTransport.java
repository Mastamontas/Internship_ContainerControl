package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "BookingTransport")
public class BookingTransport extends BaseEntity{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    //bookingtransportDetail
    @Getter @Setter
    @Column(name = "BookingtransportDetail", nullable = false, unique = true)
    private String bookingtransportDetail;

    //estes detalhes de shipment não deveriam vir de outra entidade?
    //transhipment
    @Getter @Setter
    @Column(name = "Transhipment", nullable = false, unique = true)
    private boolean transhipment;

    //fullEmpty
    //se for o container estar cheio, tem de ser uma boolean com um nome diferente do genero "is full"
    @Getter @Setter
    @Column(name = "FullEmpty", nullable = false, unique = true)
    private String fullEmpty;

    //bookingGoodsDetail
    @Getter @Setter
    @Column(name = "BookingGoodsDetail", nullable = false, unique = true)
    private String bookingGoodsDetail;

    //shipmentTemperature
    @Getter @Setter
    @Column(name = "ShipmentTemperature")
    private String shipmentTemperature;

    //shipmentTemperatureUnit
    @Getter @Setter
    @Column(name = "ShipmentTemperatureUnit")
    private String shipmentTemperatureUnit;

    //haulierID
    //foreign key
    //one haulier can have several bookings, but one booking can have one haulier ID
    @Getter @Setter
    @Column(name = "HaulierID", nullable = false)
    private int haulierID;

    //vehicleReference
    @Getter @Setter
    @Column(name = "VehicleReference", nullable = false, unique = true)
    private String vehicleReference;

    //stowageLocation não deveria estar ligada à tabela das localizações?
    @Getter @Setter
    @Column(name = "StowageLocation", nullable = false, unique = true)
    private String stowageLocation;

    //shipmentPackageNumber
    @Getter @Setter
    @Column(name = "ShipmentPackageNumber", nullable = false, unique = true)
    private int shipmentPackageNumber;

    //netWeight
    @Getter @Setter
    @Column(name = "NetWeight", nullable = false)
    private double netWeight;

    //sealNumberA
    @Getter @Setter
    @Column(name = "SealNumberA", nullable = false)
    private String sealNumberA;

    //sealNumberB
    @Getter @Setter
    @Column(name = "SealNumberB", nullable = false)
    private String sealNumberB;

    //moveTypeBound
    @Getter @Setter
    @Column(name = "MoveTypeBound", nullable = false)
    private String moveTypeBound;

    //depotCode
    //foreign key
    //one depot can have several bookings, but one booking can have one depot code
    //one to many
    @Getter @Setter
    @Column(name = "DepotCode", nullable = false)
    private String depotCode;

    //depotAreaCode
    @Getter @Setter
    @Column(name = "DepotAreaCode", nullable = false)
    private String depotAreaCode;

    //depotLocationCode
    //foreign key
    //one depot location can have several bookings, but one booking can have one depot location code
    //one to many
    @Getter @Setter
    @Column(name = "DepotLocationCode", nullable = false)
    private String depotLocationCode;

    //depotLocationStreet
    @Getter @Setter
    @Column(name = "DepotLocationStreet", nullable = false)
    private String depotLocationStreet;

    //depotLocationRow
    @Getter @Setter
    @Column(name = "DepotLocationRow", nullable = false)
    private String depotLocationRow;

    //depotLocationStacking
    @Getter @Setter
    @Column(name = "DepotLocationStacking", nullable = false)
    private String depotLocationStacking;

    //clientID
    //foreign key
    //one client can have several bookings, but one booking can have one client ID
    //one to many
    @Getter @Setter
    @Column(name = "ClientID", nullable = false)
    private int clientID;

    //shipperID
    //foreign key
    //one shipper can have several bookings, but one booking can have one shipper ID
    //one to many
    @Getter @Setter
    @Column(name = "ShipperID", nullable = false)
    private int shipperID;

    //consigneeID
    //foreign key
    //one consignee can have several bookings, but one booking can have one consignee ID
    //one to many
    @Getter @Setter
    @Column(name = "ConsigneeID", nullable = false)
    private int consigneeID;

    //notifyID
    //foreign key
    //one notify can have several bookings, but one booking can have one notify ID
    //one to many
    @Getter @Setter
    @Column(name = "NotifyID", nullable = false)
    private int notifyID;

    //agentID
    //foreign key
    //one agent can have several bookings, but one booking can have one agent ID
    //one to many
    @Getter @Setter
    @Column(name = "AgentID", nullable = false)
    private int agentID;

    //grossWeight
    @Getter @Setter
    @Column(name = "GrossWeight", nullable = false)
    private double grossWeight;
    //goodsExportDeclaration(DME)
    @Getter @Setter
    @Column(name = "GoodsExportDeclaration", nullable = false)
    private String goodsExportDeclaration;
    //customsDispatchDocument(DAU)
    @Getter @Setter
    @Column(name = "CustomsDispatchDocument", nullable = false)
    private String customsDispatchDocument;
    //customsSeal
    @Getter @Setter
    @Column(name = "CustomsSeal", nullable = false)
    private String customsSeal;

    //goodsValue
    //que data type é?
    @Getter @Setter
    @Column(name = "GoodsValue", nullable = false, unique = true)
    private String goodsValue;

    //situation
    @Getter @Setter
    @Column(name = "Situation")
    private String situation;

    //equipmentDateDischarge
    @Getter @Setter
    @Column(name = "EquipmentDateDischarge", nullable = false)
    private Timestamp equipmentDateDischarge;

    //commodityExpireDate
    @Getter @Setter
    @Column(name = "CommodityExpireDate", nullable = false)
    private Timestamp commodityExpireDate;

    //commodityCode
    //foreign key
    //one commodity can have several bookings, but one booking can have one commodity code
    //one to many
    @Getter @Setter
    @Column(name = "CommodityCode", nullable = false)
    private String commodityCode;

    //commodityName
    @Getter @Setter
    @Column(name = "commodityName", nullable = false)
    private String commodityName;

    public BookingTransport() {
    }
}
