package com.DEVLOP.Factories;

import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.github.javafaker.Faker;

//has to receive an equipment class
public class EquipmentTypeFactory {
    private static final Faker faker = new Faker();
    public  static EquipmentType CreateEquipmentType(EquipmentClass preExistingEquipClass){
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipmentClass(preExistingEquipClass);
        equipmentType.setEquipmentTypeHeight(2.00);
        equipmentType.setEquipmentTypeCode(faker.letterify(faker.lorem().characters(3,true)));
        equipmentType.setEquipmentTypeLength(2.00);
        equipmentType.setEquipmentTypeName(faker.letterify(faker.lorem().characters(3,true)));
        equipmentType.setEquipmentTypeTareWeight(2.00);
        equipmentType.setEquipmentTypeComments(faker.letterify(faker.lorem().characters(3,true)));
        return equipmentType;
    }
}
