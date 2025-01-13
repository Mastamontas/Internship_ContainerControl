package com.DEVLOP.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "MovementType")
public class MovementType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //movementTypeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "MovementTypeCode", nullable = false)
    private String movementTypeCode;
    //movementTypeName
    @Column(name = "MovementTypeName", nullable = false)
    private String movementTypeName;
    //movementTypeComments
    @Column(name = "MovementTypeComments", nullable = false)
    private String movementTypeComments;
    //movementTypeEmpty
    @Column(name = "MovementTypeEmpty", nullable = false)
    private boolean movementTypeEmpty;

    public MovementType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovementTypeCode() {
        return movementTypeCode;
    }

    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
    }

    public String getMovementTypeName() {
        return movementTypeName;
    }

    public void setMovementTypeName(String movementTypeName) {
        this.movementTypeName = movementTypeName;
    }

    public String getMovementTypeComments() {
        return movementTypeComments;
    }

    public void setMovementTypeComments(String movementTypeComments) {
        this.movementTypeComments = movementTypeComments;
    }

    public boolean isMovementTypeEmpty() {
        return movementTypeEmpty;
    }

    public void setMovementTypeEmpty(boolean movementTypeEmpty) {
        this.movementTypeEmpty = movementTypeEmpty;
    }
}
