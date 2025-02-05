package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IGenericRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IJpaEquipmentRepository extends IGenericRepository<Equipment, Integer> {
    Optional<Equipment> findByPrefixAndCheckDigitAndNumber(@NotNull String prefix, @NotNull int checkDigit, @NotNull int number);
}
