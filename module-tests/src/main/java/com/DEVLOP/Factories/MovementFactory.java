package com.DEVLOP.Factories;


import com.DEVLOP.Entities.*;
import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class MovementFactory {
    private static final Faker faker = new Faker();
    //classe tem de receber um equipamento pre existente para poder associar movimentos
    public static Movement CreateMovement(Equipment preExistingEquipment){

        //SETUP
        //base entity sets
        Movement movement = new Movement();
        movement.setCreatedOn(LocalDateTime.now());
        movement.setMovementStatus("MovementStatus");

        //set database relationships
        //todo: this nested functionality here works for now, but remove it later
        movement.setEquipment(preExistingEquipment);//mudar nome
        movement.setEquipmentType(preExistingEquipment.getEquipmentType());//mudar nome para ser igual

        //movement attributes
        movement.setDate(convertToLocalDate(faker.date().past(365, TimeUnit.DAYS)));
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
    //pode receber um equipamento ou pode receber uma lista

    public static List<Movement> CreateMovementList(Equipment equipment, int numberOfMovements){
        List<Movement> movementList = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfMovements).forEach(i->{
           Movement move = CreateMovement(equipment);
           movementList.add(move);
        });
        return movementList;
    }

    // Helper method to convert Date to LocalDateTime
    //todo maybe instead of date have local date time
    private static LocalDate convertToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault()) // Convert to system default timezone
                .toLocalDate();
    }
}
