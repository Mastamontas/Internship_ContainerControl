package com.DEVLOP.Application.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class MovementDto {
    private Integer id;//id movimento

    //equipment

    private Integer equipmentId;
    @Pattern(
            regexp = "^[A-Z]{4}$",
            message = "Prefix must be exactly 4 uppercase letters"
    )
    private String prefix; //equipamento
    private Integer number; //equipamento
    private Integer checkDigit; //equipamento
    private String equipmentTypeCode; //equipment type
    private Double equipmentTypeLength; //equipment type

    //movement
    private String movementKey; //movement type?
    private String movementCode; //vem de movement type?
    private String movementStatus; //enum
    private LocalDateTime date; //igual a mov entity
    private String equipmentServiceCode;//vem de equipment service
    private String conditionCode; //vem de equipment condition
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
    private String transportResponsibility;//enum
    private String haulier;//outra tabela
    private String vehicleReference;//outra tabela

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

    //companies
    private String companyLine;
    private Integer equipmentOwnerCode;
    private Integer offHire;
    private Integer agentCode;
    private Integer shipperCode;
    private Integer consigneeCode;

    //booking process
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

    //customs
    private String goodsExportDeclaration;
    private String customsDispatchDocument;
    private Double goodsValue;
    private String situation;
    private String customsSeal;

    //audition
    private String username;
    private LocalDate auditionDate;
    private LocalDateTime time;

}
