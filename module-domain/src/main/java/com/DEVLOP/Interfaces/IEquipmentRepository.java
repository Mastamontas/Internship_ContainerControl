package com.DEVLOP.Interfaces;

import com.DEVLOP.DomainEntities.Equipment.Equipment;


import java.util.List;

public interface IEquipmentRepository {
    //estes metodos depois sao overriden porque faz se a query dentro
    List<Equipment> findAll();

}
