package com.DEVLOP.Application.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovementDto {
    @NotNull
    private int id;//id movimento
    //equipment
    @NotNull (message = "Prefix must not be null")
    @Pattern(
            regexp = "^[A-Z]{4}$",
            message = "Prefix must be exactly 4 uppercase letters"
    )
    private String prefix;
    @NotNull (message ="number must not be null")
    private int number;
    @NotNull (message ="check digit must not be null")
    private int checkDigit;
    @NotNull
    private int equipmentTypeID;
    @NotNull (message =" equipment type code must not be null")
    private String equipmentTypeCode;
    @NotNull (message = "equipment type length must not be null")
    private double equipmentTypeLength;

    //movement
    private String movementKey;
    private int movementCode;
    private String movementStatus; //enum
    private LocalDateTime date;
    private String equipmentServiceCode;
    private String conditionCode;
    private String movementBound;//enum
    private String shippingCode;
    private boolean transhipment;
    private boolean isEmpty;
    private String stewageLocation;
    private int sealNumberA;
    private int sealNumberB;
    private String comments;
    private String transportDetails;
    private String transportReference;
    private String transportResponsibility;//enum
    private String haulier;
    private String vehicleReference;

    //location
    private int fromCode;
    private String fromName;
    private int toCode;
    private String toName;
    private int finalCode;
    private String finalName;
    private int restitutionCode;
    private String restitutionName;

    //depot
    private int depotCode;
    private String areaCode;
    private String locationCode;
    private String street;
    private String row;
    private String stack;

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
    /*
    user id
    date
    time
     */

}
