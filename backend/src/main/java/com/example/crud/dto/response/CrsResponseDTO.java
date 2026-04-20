package com.example.crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrsResponseDTO {
	private Integer courseId;
    private String title;
}
