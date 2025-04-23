package com.DEVLOP.Factories;

import com.DEVLOP.Entities.MovementType;
import com.github.javafaker.Faker;

public class MovementTypeFactory {
    private static final Faker faker = new Faker();
    public static MovementType CreateMovementType(){
        MovementType movementType = new MovementType();
        movementType.setMovementTypeCode(faker.letterify(faker.lorem().characters(3,true)));
        movementType.setMovementTypeName(faker.letterify(faker.lorem().characters(3,true)));
        movementType.setMovementTypeComments(faker.letterify(faker.lorem().characters(3,true)));
        movementType.setEmpty(false);
        return movementType;
    }
}
