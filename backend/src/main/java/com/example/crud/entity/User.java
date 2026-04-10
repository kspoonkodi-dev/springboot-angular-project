package com.example.crud.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonIgnore
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 50)
	private Role role;

	public enum Role {

		ADMIN, INSTRUCTOR
	}

	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	public enum Status {
		ACCEPTED, REJECTED, PENDING

	}

	private LocalDateTime accountCreatedDate = LocalDateTime.now();



}
