/*
package com.DEVLOP.Factories;
import com.DEVLOP.DomainEntities.Equipment.Equipment;
import java.time.Year;
//faz sentido ter aqui um builder? Sim porque sao muitos atributos, construcao e complexa e confusa

//logica simples nao precisa de metodos, so aquelas com logica mais complexa
//que campos sao obrigatorios para criar um equipamento?

//campos obrigatorios sao id, equipTypeID,prefix, numero e checkDigit
public class EquipmentFactory {
    public static Equipment createEquipment(int id, int equipmentTypeID,int movementID, String prefix, int number, int checkDigit, double grossWeight, double insideCubic, double insideHeight, double insideLength, double insideWidth, double equipmentTareWeight, Year yearOfManufacture, double payload, String comment, int lineID, int ownerID, boolean SOC) {
        // Verification and rules
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (equipmentTypeID <= 0) {
            throw new IllegalArgumentException("Equipment Type ID must be positive.");
        }
        if (prefix == null || prefix.isEmpty()) {
            throw new IllegalArgumentException("Prefix cannot be null or empty.");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive.");
        }
        if (checkDigit < 0 || checkDigit > 9) {
            throw new IllegalArgumentException("Check Digit must be between 0 and 9.");
        }
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross Weight must be positive.");
        }
        if (insideCubic <= 0) {
            throw new IllegalArgumentException("Inside Cubic must be positive.");
        }
        if (insideHeight <= 0) {
            throw new IllegalArgumentException("Inside Height must be positive.");
        }
        if (insideLength <= 0) {
            throw new IllegalArgumentException("Inside Length must be positive.");
        }
        if (insideWidth <= 0) {
            throw new IllegalArgumentException("Inside Width must be positive.");
        }
        if (equipmentTareWeight <= 0) {
            throw new IllegalArgumentException("Equipment Tare Weight must be positive.");
        }
        if (yearOfManufacture == null || yearOfManufacture.isAfter(Year.now())) {
            throw new IllegalArgumentException("Year of Manufacture must be a valid year and not in the future.");
        }
        if (payload <= 0) {
            throw new IllegalArgumentException("Payload must be positive.");
        }
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }
        if (lineID <= 0) {
            throw new IllegalArgumentException("Line ID must be positive.");
        }
        if (ownerID <= 0) {
            throw new IllegalArgumentException("Owner ID must be positive.");
        }

        return new Equipment(id, equipmentTypeID,movementID, prefix, number, checkDigit, grossWeight, insideCubic, insideHeight, insideLength, insideWidth, equipmentTareWeight, yearOfManufacture, payload, comment, lineID, ownerID, SOC);
    }
    //create business validation rules here
    //id tem de ser not null

    //equipmentTypeID tem de ser not null
    //prefix tem de ser 4 letras em CAPS
    //number tem de ser positivo

}
*/
