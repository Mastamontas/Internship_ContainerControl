package com.DEVLOP.Entities;
//tem como objectivo ser a classe base de todas as entidades
//createdOn (datetime)
//lastUpdated (datetime)
//db status (boolean)

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDateTime;
//mapped superclass permite que estas colunas passem para as entidades que a herdem
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Column(name = "CreatedOn", nullable = false, updatable = false)
    private LocalDateTime createdOn;
    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated;
    @Column(name = "DbStatus", nullable = false)
    private boolean dbStatus;

    public BaseEntity() {
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isDbStatus() {
        return dbStatus;
    }

    public void setDbStatus(boolean dbStatus) {
        this.dbStatus = dbStatus;
    }
}
