package com.jutjoy.domain.entity.profile;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@DynamicUpdate
@Table(name="profile_histories")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileHistories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name="hobby")
    private String hobby;
    
    @Column(name="introduction")
    private String introduction;

    @LastModifiedDate
    @Column(name = "edited_date")
    private Timestamp editedDate;

    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false, updatable = false)
    private ProfileNews profileNews;
}