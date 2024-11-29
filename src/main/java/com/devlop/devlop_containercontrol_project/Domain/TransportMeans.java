package com.devlop.devlop_containercontrol_project.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "transportMeans")
public class TransportMeans extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    public TransportMeans() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
