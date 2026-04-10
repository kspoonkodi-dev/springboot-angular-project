package com.example.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.request.TutorialRequestDTO;
import com.example.crud.dto.response.TutorialCrsResponseDTO;
import com.example.crud.entity.Tutorial;
import com.example.crud.service.TutorialService;

@RestController

@RequestMapping("/tutorial")
public class TutorialController {

	private final TutorialService tutorialService;

	public TutorialController(TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	/*
	 * Get All Tutorials
	 */

	@GetMapping("/all/{courseId}")
	public List<TutorialCrsResponseDTO> allTutorials(@PathVariable("courseId") Integer courseId) {


		List<Tutorial> newTutorial = tutorialService.getTutorials(courseId);
		return newTutorial.stream().map(tutorial -> new TutorialCrsResponseDTO(
				tutorial.getTutorialId(),
				tutorial.getTitle(),
				tutorial.getContent(),
				tutorial.getYouTubeLink())).toList();

	}

	/*
	 * Get Tutorials By ID
	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@GetMapping("/courseById/{courseId}")
	public List<TutorialCrsResponseDTO> allTutorialsByCourseId(@PathVariable("courseId") Integer courseId) {

		List<Tutorial> newTutorial = tutorialService.getTutorials(courseId);
		return newTutorial.stream().map(tutorial -> new TutorialCrsResponseDTO(
				tutorial.getTutorialId(),
				tutorial.getTitle(),
				tutorial.getContent(),
				tutorial.getYouTubeLink())).toList();

	}

	/*
	 * Tutorial Creation
	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")

	@PostMapping("/create")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody TutorialRequestDTO request) {
		System.out.println("tutorial create" + request);
		Tutorial newTutorial = tutorialService.createTutorial(request);
		return ResponseEntity.ok(newTutorial);

	}

	/*
	 * Tutorial Update
	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Integer tutorialId, @RequestBody Tutorial tutorial) {
		Tutorial updateTutorial = tutorialService.updateTutorial(tutorial, tutorialId);
		return ResponseEntity.ok(updateTutorial);
	}

	/*
	 * Tutorial Deletion
	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") Integer tutorialId) {
		tutorialService.deleteTutorial(tutorialId);
		return new ResponseEntity<>("Tutorial successfully deleted!", HttpStatus.OK);
		//return  ResponseEntity.ok().build();

	}
}
