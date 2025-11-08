package com.example.demo.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Users implements Persistable<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "mail_address")
    private String email;

    private Integer age;

    private String note;

    @CreatedDate
    @Column(name = "registered_date", nullable = false, updatable = false)
    private OffsetDateTime registeredDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private OffsetDateTime updatedDate;
    
    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
