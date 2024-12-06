package com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence;
import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipmentCommands extends JpaRepository<Equipment, Long> {

}
