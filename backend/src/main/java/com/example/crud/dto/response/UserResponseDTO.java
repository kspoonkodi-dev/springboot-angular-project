package com.example.crud.dto.response;

import com.example.crud.entity.User.Role;
import com.example.crud.entity.User.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	private Integer userId;
	private String username;
	private String email;
	private Status status;
    private Role role;


}
