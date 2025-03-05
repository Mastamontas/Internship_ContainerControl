package com.DEVLOP.Interfaces;
import com.DEVLOP.Entities.Equipment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/*
todo
test integration of persistence, see if something really is being persisted
makes sense to have any data validation here?
 */

/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IEquipmentRepository extends IGenericRepository<Equipment, Integer> {
    //faz sentido aqui ser optional? ou só na equipment query? aqui fica transacional?
    //poe se transacional em interfaces?
    //Optional<Equipment> FindByPrefixAndCheckDigitAndNumber(@NotNull String prefix, @NotNull int checkDigit, @NotNull int number);
}
