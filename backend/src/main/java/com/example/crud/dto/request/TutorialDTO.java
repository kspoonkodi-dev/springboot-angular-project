package com.example.crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TutorialDTO {
	private String title;
	private String content;
	private String youTubeLink;

}
