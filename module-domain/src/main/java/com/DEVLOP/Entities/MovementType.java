package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MovementType")
public class MovementType extends BaseEntity{

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Getter @Setter
    @Column(name = "MovementTypeCode", nullable = false)
    private String movementTypeCode;

    @Getter @Setter
    @Column(name = "MovementTypeName", nullable = false)
    private String movementTypeName;

    @Getter @Setter
    @Column(name = "MovementTypeComments")
    private String movementTypeComments;

    @Getter @Setter
    @Column(name = "IsMovementTypeEmpty", nullable = false)
    private boolean isMovementTypeEmpty;

    public MovementType() {
    }
}
