package com.jutjoy.domain.entity.news;

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
@Table(name="news_histories")
@Data
@EntityListeners(AuditingEntityListener.class)
public class NewsHistories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    @LastModifiedDate
    @Column(name = "edited_date")
    private Timestamp editedDate;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private News news;
}