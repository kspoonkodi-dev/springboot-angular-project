package com.example.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud.dto.request.TutorialRequestDTO;
import com.example.crud.entity.Course;
import com.example.crud.entity.Tutorial;
import com.example.crud.repository.CourseRepository;
import com.example.crud.repository.TutorialRepository;

@Service
public class TutorialService {

	private final TutorialRepository tutorialRepository;
	private final CourseRepository courseRepository;

	public TutorialService(TutorialRepository tutorialRepository, CourseRepository courseRepository) {

		this.tutorialRepository = tutorialRepository;
		this.courseRepository = courseRepository;

	}

	public List<Tutorial> getTutorials(Integer courseId)
    {
    	    	return tutorialRepository.findByCourseCourseId(courseId);
    }



	public Tutorial createTutorial(TutorialRequestDTO request) {
		System.out.println("---  Create Tutorial--");
		Course course = courseRepository.findById(request.getCourseId()).get();
		Tutorial tutorial = new Tutorial();

	    tutorial.setTitle(request.getTutorial().getTitle());
	    tutorial.setContent(request.getTutorial().getContent());
	    tutorial.setYouTubeLink(request.getTutorial().getYouTubeLink());

		tutorial.setCourse(course);

		return tutorialRepository.save(tutorial);

	}

	public Tutorial updateTutorial( Tutorial tutorial, Integer tutorialId) {
		//Course course = courseRepository.findById(courseId).get();

		//System.out.println("Tutorial" + tutorial.getContent());
		Tutorial exsitingTutorial = tutorialRepository.findById(tutorialId).get();

		exsitingTutorial.setContent(tutorial.getContent());
		exsitingTutorial.setTitle(tutorial.getTitle());
		exsitingTutorial.setYouTubeLink(tutorial.getYouTubeLink());
		//exsitingTutorial.setCourse(course);
		return tutorialRepository.save(exsitingTutorial);
	}

	public void deleteTutorial(Integer tutorialId) {
		tutorialRepository.deleteById(tutorialId);
	}

}
