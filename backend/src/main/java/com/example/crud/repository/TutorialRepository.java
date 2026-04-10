package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {
	List<Tutorial> findByCourseCourseId(Integer courseId);

}
