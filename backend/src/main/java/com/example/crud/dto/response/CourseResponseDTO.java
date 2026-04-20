package com.example.crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourseResponseDTO {
	private Integer courseId;
	private String title;
	private String description;
	private String status;

}
