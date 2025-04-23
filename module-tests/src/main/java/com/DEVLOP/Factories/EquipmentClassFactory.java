package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentClass;
import com.github.javafaker.Faker;

public class EquipmentClassFactory {
    private static final Faker faker = new Faker();
    public static EquipmentClass CreateEquipmentClass(){
        EquipmentClass equipmentClass = new EquipmentClass();
        equipmentClass.setEquipmentClassCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentClass.setEquipmentClassName(faker.letterify(faker.lorem().characters(3,true)));
        equipmentClass.setEquipmentClassType(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentClass;
    }
}
