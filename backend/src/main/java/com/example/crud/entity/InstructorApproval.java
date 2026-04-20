package com.example.crud.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table


@AllArgsConstructor
@NoArgsConstructor

public class InstructorApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private Integer logId;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "users_id", nullable = false)
	@JsonIgnore
	private User instructor;

	/*@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "admins_id", nullable = false)
	@JsonIgnore
	private User approvedByAdmin;*/
	private Integer approvedByAdmin;

	@Enumerated (EnumType.STRING)
	private Status status;

	public enum Status {
		ACCEPTED,
		REJECTED
		}
	private String text;

	private LocalDateTime approvalTimeStamp;

}
