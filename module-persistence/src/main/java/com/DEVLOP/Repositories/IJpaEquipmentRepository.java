package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IGenericRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IJpaEquipmentRepository extends IGenericRepository<Equipment, Integer> {
    Equipment findEquipmentByPrefix(@NotNull String prefix);
}
