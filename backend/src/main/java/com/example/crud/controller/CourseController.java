package com.example.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.response.CourseResponseDTO;
import com.example.crud.dto.response.CrsResponseDTO;
import com.example.crud.entity.Course;
import com.example.crud.repository.UserIdOnly;
import com.example.crud.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	/*
	 * Get All Courses
	 */
	@GetMapping("/user/all")
	public List<CourseResponseDTO> allCourses(){
		List<Course> newCourse = courseService.getCourse();
		return newCourse.stream()
				.map(course -> new  CourseResponseDTO(
						course.getCourseId(),
						course.getTitle(),
						course.getDescription(),
						course.getStatus().name()
						)).toList();
	}

	/*
	 *Get All Courses
	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/all")
	public List<CourseResponseDTO> allCourse(Authentication auth){


		UserIdOnly projectionUserId = courseService.getId(auth.getName());
		Integer instructorId = (projectionUserId.getUserId().intValue());


		List<Course> newCourse = courseService.getCourse(instructorId);
		return newCourse.stream()
				.map(course -> new  CourseResponseDTO(
						course.getCourseId(),
						course.getTitle(),
						course.getDescription(),
						course.getStatus().name()
						)).toList();

	}

	/*
	 *Get All Courses For Tutorial Dropdown box
	 *	 */
	@PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/allCourse")
	public List<CrsResponseDTO> allCourseforTutorial(Authentication auth){


		UserIdOnly projectionUserId = courseService.getId(auth.getName());
		Integer instructorId = (projectionUserId.getUserId().intValue());


		List<Course> newCourse = courseService.getCourse(instructorId);
		return newCourse.stream()
				.map(course->new CrsResponseDTO(
				course.getCourseId(),
				course.getTitle()
				)).toList();

	}



	/*
	 * Courser Creation
	 */

	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping("/create")
	public ResponseEntity<Course> createCourse(Authentication auth, @RequestBody Course course) {
		System.out.println("Authentication object: " + auth);
		System.out.println("Authorities inside controller: " + auth.getAuthorities());

		UserIdOnly projectionUserId = courseService.getId(auth.getName());



		Integer instructorId = (projectionUserId.getUserId().intValue());

		System.out.println("instructorId" + instructorId);

		Course newCourse = courseService.createCourse(course, instructorId);
		return ResponseEntity.ok(newCourse);

	}

	/*
	 * Courser Update
	 */

	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Course> updateCourse(Authentication auth, @PathVariable("id") Integer courseId,
			@RequestBody Course course) {

		System.out.println("id value" + courseId);

		System.out.println("course" + course.getDescription());
		System.out.println("course" + course);
		UserIdOnly projectionUserId = courseService.getId(auth.getName());

		Integer instructorId = (projectionUserId.getUserId().intValue());

		Course updateCourse = courseService.updateCourse(instructorId, course, courseId);
		return ResponseEntity.ok(updateCourse);
	}

	/*
	 * Courser Deletion
	 */

	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCourse(Authentication auth, @PathVariable("id") Integer courseId) {


		courseService.deleteCourse(courseId);
		return new ResponseEntity<>("Course successfully deleted!", HttpStatus.OK);

	}

}
