package com.DEVLOP.Interfaces;
import com.DEVLOP.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 * jpa specification executor to do dynamic queries
 */
public interface IEquipment extends IGenericRepository<Equipment, Integer>, JpaSpecificationExecutor<Equipment> {
    //Optional<Equipment> FindByPrefixAndCheckDigitAndNumber(@NotNull String prefix, @NotNull int checkDigit, @NotNull int number);
}
