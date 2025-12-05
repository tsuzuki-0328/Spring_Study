package com.jutjoy.domain.entity.profile;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileNews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "introduction")
	private String introduction;
	
    @Column(name = "image_name")
    private String imageName;
	
	@CreatedDate
	@Column(name = "registered_date")
	private Timestamp registered_date;
	
	@LastModifiedDate
	@Column(name = "update_date")
	private Timestamp update_date;

}
