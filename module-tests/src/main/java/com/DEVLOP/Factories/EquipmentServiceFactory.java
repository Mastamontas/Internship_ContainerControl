package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentService;
import com.github.javafaker.Faker;

public class EquipmentServiceFactory {
    private static final Faker faker = new Faker();
    public static EquipmentService CreateEquipmentService(){
        EquipmentService equipmentService =  new EquipmentService();
        equipmentService.setEquipmentServiceCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentService.setEquipmentServiceName(faker.letterify(faker.lorem().characters(3,true)));
        equipmentService.setEquipmentServiceComments(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentService;
    }
}
