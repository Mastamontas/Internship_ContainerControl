package com.DEVLOP.PersistenceEntities;


import jakarta.persistence.*;

@Entity
@Table(name = "movementType")
public class MovementTypePersistenceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //movementTypeCode
    //primary key desta entidade, tem de ser unique
    @Column(name = "movementTypeCode", nullable = false)
    private String movementTypeCode;
    //movementTypeName
    @Column(name = "movementTypeName", nullable = false)
    private String movementTypeName;
    //movementTypeComments
    @Column(name = "movementTypeComments", nullable = false)
    private String movementTypeComments;
    //movementTypeEmpty
    @Column(name = "movementTypeEmpty", nullable = false)
    private boolean movementTypeEmpty;

    public MovementTypePersistenceEntity() {
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
