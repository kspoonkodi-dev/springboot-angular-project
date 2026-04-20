package com.example.crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TutorialCrsResponseDTO {
	private Integer tutorialId;

	private String title;
	private String content;
	private String youTubeLink;

}
