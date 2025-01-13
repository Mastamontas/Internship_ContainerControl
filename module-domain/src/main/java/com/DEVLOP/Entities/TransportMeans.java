package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "TransportMeans")
public class TransportMeans extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
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
