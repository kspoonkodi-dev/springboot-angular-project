package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.InstructorApproval;

public interface InstructorApprovalRepository extends JpaRepository<InstructorApproval, Integer> {

}
