package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentClass;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
implementar i generic repo com Equipment class
 */
public interface IEquipmentClassRepo extends IGenericRepository<EquipmentClass, Integer>, JpaSpecificationExecutor<EquipmentClass> {

    EquipmentClass findByEquipmentClassCode(@NotNull String equipmentClassCode);
}
