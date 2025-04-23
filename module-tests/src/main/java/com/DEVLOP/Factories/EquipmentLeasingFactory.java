package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentLeasing;
import com.github.javafaker.Faker;

public class EquipmentLeasingFactory {
    private static final Faker faker = new Faker();
    public static EquipmentLeasing CreateEquipmentLeasing(){
        EquipmentLeasing equipmentLeasing =  new EquipmentLeasing();
        equipmentLeasing.setLeasingContractCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentLeasing.setLeasingContractName(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentLeasing;
    }
}
