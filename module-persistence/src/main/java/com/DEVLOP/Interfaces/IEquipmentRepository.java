package com.DEVLOP.Interfaces;
import com.DEVLOP.Entities.Equipment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IEquipmentRepository extends IGenericRepository<Equipment, Integer> {
    //Optional<Equipment> FindByPrefixAndCheckDigitAndNumber(@NotNull String prefix, @NotNull int checkDigit, @NotNull int number);
}
