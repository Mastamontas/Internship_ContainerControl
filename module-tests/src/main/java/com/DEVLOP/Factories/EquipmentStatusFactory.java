package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentStatus;
import com.github.javafaker.Faker;

public class EquipmentStatusFactory {
    private static final Faker faker = new Faker();
    public static EquipmentStatus CreateEquipmentStatus(){
        EquipmentStatus equipmentStatus = new EquipmentStatus();
        equipmentStatus.setEquipmentStatusCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentStatus.setEquipmentStatusName(faker.letterify(faker.lorem().characters(3,true)));
        equipmentStatus.setEquipmentStatusLevel1(faker.letterify(faker.lorem().characters(3,true)));
        equipmentStatus.setEquipmentStatusLevel2(faker.letterify(faker.lorem().characters(3,true)));
        equipmentStatus.setEquipmentStatusComments(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentStatus;
    }
}
