package com.DEVLOP.Entities;
//todo: listar sempre entidades cujo is deleted = false;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
//mapped superclass permite que estas colunas passem para as entidades que a herdem
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Setter
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
    @PrePersist
    protected void OnCreate(){
        this.createdOn = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.isDeleted = false;
    }

}
