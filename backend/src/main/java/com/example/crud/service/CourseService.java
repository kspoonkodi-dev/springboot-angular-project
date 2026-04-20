package com.example.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud.entity.Course;
import com.example.crud.entity.User;
import com.example.crud.entity.User.Status;
import com.example.crud.exception.InstructorNotEligibleException;
import com.example.crud.repository.CourseRepository;
import com.example.crud.repository.UserIdOnly;
import com.example.crud.repository.UserRepository;


@Service
public class CourseService {

private final CourseRepository courseRepository;
private final UserRepository userRepository;

	public CourseService(CourseRepository courseRepository,UserRepository userRepository)
	{
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	public UserIdOnly getId(String username) {

		return userRepository.findProjectedByUsername(username);
	}

	public List<Course> getCourse()
	{
		return courseRepository.findAll();
	}



    public List<Course> getCourse(Integer instructorId)
    {

    	return courseRepository.findByUserUserId(instructorId);
    }

	public Course createCourse(Course course, Integer instructorId){


		User user = userRepository.findById(instructorId).get();

		if(user.getStatus() == Status.PENDING) {
		    throw new InstructorNotEligibleException("You are not eligible to create a course.");
		}

		course .setUser(user);


		 return courseRepository.save(course);

	}

	public Course updateCourse(Integer instructorId,Course course, Integer courseId) {


		User user = userRepository.findById(instructorId).get();
		Course	exsitingCourse =	courseRepository.findById(courseId).get();

		exsitingCourse.setDescription(course.getDescription());
		exsitingCourse.setTitle(course.getTitle());
		exsitingCourse.setStatus(course.getStatus());
		exsitingCourse.setUser(user);
		return courseRepository.save(exsitingCourse);
		}


		public void deleteCourse (Integer courseId) {
			courseRepository.deleteById(courseId);
		}


}
