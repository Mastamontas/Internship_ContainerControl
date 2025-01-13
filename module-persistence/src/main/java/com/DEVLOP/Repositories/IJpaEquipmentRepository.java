package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository that inherits CRUD functionalities from the {@link JpaRepository}
 */
public interface IJpaEquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Override
    List<Equipment> findAll();
}
