package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IJpaEquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Override
    List<Equipment> findAll();

    Equipment findEquipmentByPrefix(@NotNull String prefix);


    //this method creates equip if null and updates if exist
    @Override
    <S extends Equipment> S saveAndFlush(S entity);

    Equipment findEquipmentById(@NotNull int id);
}
