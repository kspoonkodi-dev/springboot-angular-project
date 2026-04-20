package com.example.crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TutorialRequestDTO {
	private Integer courseId;
	private TutorialDTO tutorial;

}
