package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data

public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private boolean gender;

	@Column(name = "birth")
	private java.sql.Date birth;

	@Column(name = "created_at")
	private java.sql.Timestamp created_at;

	@Column(name = "updated_at")
	private java.sql.Timestamp updated_at;

}
