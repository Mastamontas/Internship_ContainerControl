package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentClass;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
implementar i generic repo com Equipment class
 */
public interface IEquipmentClassRepo extends IGenericRepository<EquipmentClass, Integer>, JpaSpecificationExecutor<EquipmentClass> {

}
