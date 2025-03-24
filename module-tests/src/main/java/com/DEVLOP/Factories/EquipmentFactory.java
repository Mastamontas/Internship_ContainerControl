package com.DEVLOP.Factories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.DEVLOP.Repositories.EquipmentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

public class EquipmentFactory {
    /*
    public static methods for this class
    nao interessa gravar em db, so serve para criar os objetos
     */
    private static final Faker faker = new Faker();


    public static Equipment CreateEquipment() {
        //set up equipment class
        EquipmentClass eqClass = new EquipmentClass();
        //eqClass.setCreatedOn(LocalDateTime.now());
        eqClass.setEquipmentClassCode(faker.lorem().characters(1, 3));
        eqClass.setEquipmentClassName(faker.lorem().characters(1, 5));
        eqClass.setEquipmentClassType(faker.lorem().characters(1, 5));
        //setup equipment type
        EquipmentType eqType = new EquipmentType();
        //setup equipment class in equipment type
        eqType.setEquipmentClass(eqClass);
        //eqType.setCreatedOn(LocalDateTime.now());
        eqType.setEquipmentTypeCode(faker.code().isbn10());
        eqType.setEquipmentTypeName(faker.lorem().fixedString(7));
        eqType.setEquipmentTypeLength(faker.number().randomDouble(3, 1, 100));
        eqType.setEquipmentTypeTareWeight(faker.number().randomDouble(4, 1, 100));
        eqType.setEquipmentTypeHeight(faker.number().randomDouble(3, 1, 100));
        //set equipment
        Equipment eq = new Equipment();
        eq.setPrefix(faker.letterify("????").toUpperCase());
        eq.setNumber(faker.number().numberBetween(1, 99));
        eq.setCheckDigit(faker.number().numberBetween(1, 10));
        //eq.setCreatedOn(LocalDateTime.now());
        //set up equipment type in equipment
        eq.setEquipmentType(eqType);
        return eq;
    }

    //codigo duplicado, fazer refactor
    public static List<Equipment> CreateEquipmentList(int count) {
        List<Equipment> equipmentList = new ArrayList<>();
        IntStream.rangeClosed(1, count).forEach(i -> {
            //set up equipment class
            EquipmentClass eqClass = new EquipmentClass();
            eqClass.setCreatedOn(LocalDateTime.now());
            eqClass.setEquipmentClassCode(faker.lorem().characters(1, 3));
            eqClass.setEquipmentClassName(faker.lorem().characters(1, 5));
            eqClass.setEquipmentClassType(faker.lorem().characters(1, 5));
            //setup equipment type
            EquipmentType eqType = new EquipmentType();
            //setup equipment class in equipment type
            eqType.setEquipmentClass(eqClass);
            eqType.setCreatedOn(LocalDateTime.now());
            eqType.setEquipmentTypeCode(faker.code().isbn10());
            eqType.setEquipmentTypeName(faker.lorem().fixedString(7));
            eqType.setEquipmentTypeLength(faker.number().randomDouble(3, 1, 100));
            eqType.setEquipmentTypeTareWeight(faker.number().randomDouble(4, 1, 100));
            eqType.setEquipmentTypeHeight(faker.number().randomDouble(3, 1, 100));


            //set equipment
            Equipment eq = new Equipment();
            eq.setPrefix(faker.letterify("????").toUpperCase());
            eq.setNumber(faker.number().numberBetween(1, 99));
            eq.setCheckDigit(faker.number().numberBetween(1, 10));
            eq.setCreatedOn(LocalDateTime.now());
            //set up equipment type in equipment
            eq.setEquipmentType(eqType);
            equipmentList.add(eq);
        });
        return equipmentList;
    }
}
