package com.DEVLOP.Entities;
//tem como objectivo ser a classe base de todas as entidades
//createdOn (datetime)
//lastUpdated (datetime)
//db status (boolean)

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
//mapped superclass permite que estas colunas passem para as entidades que a herdem
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Getter
    @Column(name = "CreatedOn", nullable = false, updatable = false)
    private LocalDateTime createdOn;
    @Getter
    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated;
    @Getter
    @Column(name = "IsDeleted", nullable = false)
    private boolean isDeleted;

    public BaseEntity() {
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
