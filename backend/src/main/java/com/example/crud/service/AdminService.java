package com.example.crud.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.crud.entity.InstructorApproval;
import com.example.crud.entity.InstructorApproval.Status;
import com.example.crud.entity.User;
import com.example.crud.entity.User.Role;
import com.example.crud.repository.InstructorApprovalRepository;
import com.example.crud.repository.UserIdOnly;
import com.example.crud.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	public final UserRepository userRepository;
	public final InstructorApprovalRepository instructorApprovalRepository;

	public List<User> getAllUser() {


		return userRepository.findAll();

	}

	public List<User> getPendingInstructor(){
		return userRepository.findByStatusAndRole(com.example.crud.entity.User.Status.PENDING, Role.INSTRUCTOR);
	}


	public InstructorApproval processApproval(Authentication auth, Integer userId, String status) {
		User newUser = userRepository.findById(userId).get();

		UserIdOnly projectionUserId = userRepository.findProjectedByUsername(auth.getName());

		Integer adminId = (projectionUserId.getUserId().intValue());


		InstructorApproval instructorApproval = new InstructorApproval();
		
		instructorApproval.setApprovedByAdmin(adminId);

		instructorApproval.setApprovalTimeStamp(LocalDateTime.now());
		instructorApproval.setInstructor(newUser);

		if("Approved".equalsIgnoreCase(status)) {

		
		instructorApproval.setStatus(Status.ACCEPTED);
		newUser.setStatus(User.Status.ACCEPTED);
		instructorApproval.setText("Registartion Approved");
		

		}
		else if("Rejected".equalsIgnoreCase(status))
		{

			instructorApproval.setStatus(Status.REJECTED);
			newUser.setStatus(User.Status.REJECTED);
			instructorApproval.setText("Registartion Rejected");
			

		}
		return instructorApprovalRepository.save(instructorApproval);
	}

}
