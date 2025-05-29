package com.DEVLOP.Application.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class MovementDto {
    //mandatory fields
    private Integer id;//id movimento
    private LocalDate date; //igual a mov entity
    private String movementStatus; //todo: definir enum - perguntar paulo
    private Integer accessUserId;//por no excel?
    private Integer businessUnitId;//por no excel?
    private String transportResponsibility;
    private String movementOfHire;
    private Integer movementRestitutionCode;
    private Integer movementVoyageId;
    private Integer equipmentOwnerId;
    private Integer movementDays;
    private boolean isMovementLast;
    private String shipmentUCN;
    private String movementTransport;
    private Integer transportMeansId;
    private String transportMeansComment;
    //equipment
    private Integer equipmentId;
    @Pattern(
            regexp = "^[A-Z]{4}$",
            message = "Prefix must be exactly 4 uppercase letters"
    )
    private String prefix; //equipamento
    private Integer number; //equipamento
    private Integer checkDigit; //equipamento

    //equipment type
    private Integer equipmentTypeId;//é int porque nunca pode ser nula?
    private String equipmentTypeCode; //equipment type
    private Double equipmentTypeLength; //equipment type

    //movement type
    private Integer movementTypeId;
    private String movementTypeCode; //movement type
    private String movementTypeName; //vem de movement type

    //equipment status
    private Integer equipmentStatusId;
    private String equipmentStatusCode;
    private String equipmentStatusName;
    private String equipmentStatusLevel1;//enum?
    private String equipmentStatusLevel2;//enum?
    //equipment service
    private Integer equipmentServiceId;
    private String equipmentServiceCode;
    private String equipmentServiceName;

    //equipment condition
    private Integer equipmentConditionId;
    private String physicalConditionCode;
    private String physicalConditionName;
    private String physicalConditionType;
    //equipment leasing
    private Integer equipmentLeasingId;
    private String equipmentLeasingCode;
    private String equipmentLeasingName;


    private String movementBound;//vem de outra tabela
    private String shippingCode;//vem de outra tabela
    private boolean transhipment;//outra tabela
    private boolean isEmpty;//movement type
    private String stowageLocation;//outra tabela
    private Integer sealNumberA;//outra tabela
    private Integer sealNumberB;//outra tabela
    private String comments; //vem de movement entity
    private String transportDetails;//outra tabela
    private String transportReference;//outra tabela
    private String haulier;//outra tabela
    private String vehicleReference; //outra tabela

    //location
    private Integer fromCode; //evento externo
    private String fromName; //evento externo
    private Integer toCode; //evento externo
    private String toName; //evento externo
    private Integer finalCode; //evento externo
    private String finalName; //evento externo
    private Integer restitutionCode; //evento externo
    private String restitutionName; //evento externo

    //depot
    private Integer depotCode;//outra tabela
    private String areaCode;//outra tabela
    private String locationCode;//outra tabela
    private String street;//outra tabela
    private String row;//outra tabela
    private String stack;//outra tabela

    //companies - externo
    private String companyLine;
    private Integer equipmentOwnerCode;
    private Integer offHire;
    private Integer agentCode;
    private Integer shipperCode;
    private Integer consigneeCode;

    //booking process - externo
    private String transportingCompany;
    private String vessel;
    private String voyage;
    private String transportBounds;//enum
    private Integer bookingProcess;
    private Integer bookingLineNumber;
    private Integer bookingReference;
    private String goodsDetail;
    private String UCN;
    private Integer numberPacks;
    private Double netWeight;
    private Double grossWeight;
    private Double temperature;
    private String temperatureUnit;//enum
    private Integer commodityCode;
    private String commodityName;
    private LocalDate commodityExpireDate;
    private LocalDate equipmentExpireDate;

    //customs - externo
    private String goodsExportDeclaration;
    private String customsDispatchDocument;
    private Double goodsValue;
    private String situation;
    private String customsSeal;

    //audition - externo
    private String username;
    private LocalDate auditionDate;
    private LocalDateTime time;

}
