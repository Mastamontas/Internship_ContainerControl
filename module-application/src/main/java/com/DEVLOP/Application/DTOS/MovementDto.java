package com.DEVLOP.Application.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class MovementDto {
    @NotNull
    private int id;//id movimento

    //equipment

    private int equipmentId;
    @Pattern(
            regexp = "^[A-Z]{4}$",
            message = "Prefix must be exactly 4 uppercase letters"
    )
    private String prefix; //equipamento
    private int number; //equipamento
    private int checkDigit; //equipamento
    private String equipmentTypeCode; //equipment type
    private double equipmentTypeLength; //equipment type

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
    private int sealNumberA;//outra tabela
    private int sealNumberB;//outra tabela
    private String comments; //vem de movement entity
    private String transportDetails;//outra tabela
    private String transportReference;//outra tabela
    private String transportResponsibility;//enum
    private String haulier;//outra tabela
    private String vehicleReference;//outra tabela

    //location
    private int fromCode; //evento externo
    private String fromName; //evento externo
    private int toCode; //evento externo
    private String toName; //evento externo
    private int finalCode; //evento externo
    private String finalName; //evento externo
    private int restitutionCode; //evento externo
    private String restitutionName; //evento externo

    //depot
    private int depotCode;//outra tabela
    private String areaCode;//outra tabela
    private String locationCode;//outra tabela
    private String street;//outra tabela
    private String row;//outra tabela
    private String stack;//outra tabela

    //companies
    private String companyLine;
    private int equipmentOwnerCode;
    private int offHire;
    private int agentCode;
    private int shipperCode;
    private int consigneeCode;

    //booking process
    private String transportingCompany;
    private String vessel;
    private String voyage;
    private String transportBounds;//enum
    private int bookingProcess;
    private int bookingLineNumber;
    private int bookingReference;
    private String goodsDetail;
    private String UCN;
    private int numberPacks;
    private double netWeight;
    private double grossWeight;
    private double temperature;
    private String temperatureUnit;//enum
    private int commodityCode;
    private String commodityName;
    private LocalDate commodityExpireDate;
    private LocalDate equipmentExpireDate;

    //customs
    private String goodsExportDeclaration;
    private String customsDispatchDocument;
    private double goodsValue;
    private String situation;
    private String customsSeal;

    //audition
    private String username;
    private LocalDate auditionDate;
    private LocalDateTime time;

}
