package com.example.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.response.UserResponseDTO;
import com.example.crud.entity.User;
import com.example.crud.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<UserResponseDTO> getAllUser(){

		
		List<User> newUser = adminService.getPendingInstructor();
 		return newUser.stream().map(user->new UserResponseDTO(
 				user.getUserId(),
 				user.getUsername(),
 				user.getEmail(),
 				user.getStatus(),
 				user.getRole())).toList();
	}

	/*
	 * Approval  Instructor Registration
	 *
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/approve-instructor/{UserId}")
	public ResponseEntity<?> instructorApproval(Authentication auth,@PathVariable("UserId")Integer userId){
		String status = "Approved";

		adminService.processApproval(auth,userId,status);

		return ResponseEntity.ok("Approved");
	}


	/*
	 * Reject Instructor Registration
	 *
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/reject-instructor/{UserId}")
	public ResponseEntity<?> instructorRejection(Authentication auth,@PathVariable("UserId")Integer userId){
		String status = "Rejected";
		adminService.processApproval(auth,userId,status);
		return ResponseEntity.ok("Rejected");
	}

}
