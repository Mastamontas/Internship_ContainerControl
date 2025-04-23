package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentCondition;
import com.github.javafaker.Faker;

public class EquipmentConditionFactory {
    private static final Faker faker = new Faker();
    public static EquipmentCondition CreateEquipmentCondition(){
        EquipmentCondition equipmentCondition = new EquipmentCondition();
        equipmentCondition.setPhysicalConditionCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentCondition.setPhysicalConditionName(faker.letterify(faker.lorem().characters(3,true)));
        equipmentCondition.setPhysicalConditionType(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentCondition;
    }
}
