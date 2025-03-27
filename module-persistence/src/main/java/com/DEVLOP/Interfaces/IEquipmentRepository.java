package com.DEVLOP.Interfaces;
import com.DEVLOP.Entities.Equipment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 * jpa specification executor to do dynamic queries
 */
public interface IEquipmentRepository extends IGenericRepository<Equipment, Integer>, JpaSpecificationExecutor<Equipment> {
    //Optional<Equipment> FindByPrefixAndCheckDigitAndNumber(@NotNull String prefix, @NotNull int checkDigit, @NotNull int number);
}
