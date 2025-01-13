package com.DEVLOP.Interfaces;
import com.DEVLOP.Entities.Equipment;
import java.util.List;


/**
 * Methods for the CRUD interactions of the equipment entity with the database
 */
public interface IEquipmentRepository {
    //estes metodos depois sao overriden porque faz se a query dentro
    List<Equipment> findAll();

}
