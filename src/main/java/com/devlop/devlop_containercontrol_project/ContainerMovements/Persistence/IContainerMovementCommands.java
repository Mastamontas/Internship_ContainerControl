package com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence;
import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContainerMovementCommands extends JpaRepository<Equipment, Long> {
    /**
     * só se inserem aqui custom queries como update ou create por algo que não seja um ID
     */
}
