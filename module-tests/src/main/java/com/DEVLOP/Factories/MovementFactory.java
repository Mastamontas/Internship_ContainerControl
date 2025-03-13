package com.DEVLOP.Factories;


import com.DEVLOP.Entities.*;
import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*

 */
public class MovementFactory {
    private static final Faker faker = new Faker();
    //classe tem de receber um equipamento pre existente para poder associar movimentos
    public static Movement CreateMovementEntity(Equipment preExistingEquipment){

        //SETUP
        //base entity sets
        Movement movement = new Movement();

        //create movement type
        MovementType randomMovementType = new MovementType();
        randomMovementType.setMovementTypeCode(faker.letterify(faker.lorem().characters(3,true)));
        randomMovementType.setMovementTypeName(faker.letterify(faker.lorem().characters(6,true)));
        randomMovementType.setMovementTypeEmpty(false);

        //create Equipment service
        EquipmentService randomEquipmentService = new EquipmentService();
        randomEquipmentService.setEquipmentServiceCode(faker.letterify(faker.lorem().characters(6,true)));
        randomEquipmentService.setEquipmentServiceName(faker.letterify(faker.lorem().characters(8,true)));
        //create equipment condition
        EquipmentCondition randomEquimentCondition = new EquipmentCondition();
        randomEquimentCondition.setPhysicalConditionCode(faker.letterify(faker.lorem().characters(8,true)));
        randomEquimentCondition.setPhysicalConditionName(faker.letterify(faker.lorem().characters(8,true)));
        randomEquimentCondition.setPhysicalConditionType(faker.letterify(faker.lorem().characters(8,true)));
        //create random equipment leasing
        EquipmentLeasing randomEquipmentLeasing = new EquipmentLeasing();
        randomEquipmentLeasing.setLeasingContractCode(faker.letterify(faker.lorem().characters(8,true)));
        randomEquipmentLeasing.setLeasingContractName(faker.letterify(faker.lorem().characters(8,true)));
        //create equipment status
        EquipmentStatus randomEquipmentStatus = new EquipmentStatus();
        randomEquipmentStatus.setEquipmentStatusCode(faker.letterify(faker.lorem().characters(8,true)));
        randomEquipmentStatus.setEquipmentStatusName(faker.letterify(faker.lorem().characters(8,true)));
        randomEquipmentStatus.setEquipmentStatusLevel1(faker.letterify(faker.lorem().characters(8,true)));
        randomEquipmentStatus.setEquipmentStatusLevel2(faker.letterify(faker.lorem().characters(8,true)));

        //create transport means
        TransportMeans randomTransportMeans = new TransportMeans();

        //set database relationships
        movement.setEquipment(preExistingEquipment);
        movement.setEquipmentType(preExistingEquipment.getEquipmentType());
        movement.setEquipmentService(randomEquipmentService);
        movement.setEquipmentCondition(randomEquimentCondition);
        movement.setEquipmentLeasing(randomEquipmentLeasing);
        movement.setTransportMeans(randomTransportMeans);
        movement.setEquipmentStatus(randomEquipmentStatus);

        //movement attributes
        movement.setDate(convertToLocalDateTime(faker.date().past(365, TimeUnit.DAYS)));
        movement.setBusinessUnitID(faker.number().numberBetween(1,200));
        movement.setAccessUserID(faker.number().numberBetween(1,200));
        movement.setBookingEquipmentID(faker.number().numberBetween(1,200));
        movement.setTransportResponsibility(faker.letterify(faker.lorem().characters(8,true)));
        movement.setMovementOfHire(faker.letterify(faker.lorem().characters(8,true)));
        movement.setMovementFromID(faker.number().numberBetween(1,200));
        movement.setMovementToID(faker.number().numberBetween(1,200));
        movement.setMovementFinalID(faker.number().numberBetween(1,200));
        movement.setMovementRestitutionCode(faker.number().numberBetween(1,200));
        movement.setMovementVoyageID(faker.number().numberBetween(1,200));
        movement.setMovementTransport(faker.letterify(faker.lorem().characters(8,true)));
        movement.setEquipmentOwnerID(faker.number().numberBetween(1,200));
        movement.setMovementDays(faker.number().numberBetween(1,200));
        movement.setMovementLast(false);
        movement.setShipmentUCN(faker.letterify(faker.lorem().characters(8,true)));
        return movement;


    }
    // Helper method to convert Date to LocalDateTime
    private static LocalDateTime convertToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault()) // Convert to system default timezone
                .toLocalDateTime();
    }
}
