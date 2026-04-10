package com.example.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {



	@Override
	Optional<Course> findById(Integer courseId);

	List<Course> findByUserUserId(Integer instructorId);

	@Override
	void deleteById(Integer courseId);

	@Override
	List<Course> findAll();
}
