package com.example.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entity.User;
import com.example.crud.entity.User.Role;
import com.example.crud.entity.User.Status;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{

	Optional<User> findByUsername(String username);
	UserIdOnly findProjectedByUsername(String username);
	@Override
	List<User> findAll();
	List<User> findByStatusAndRole(Status status, Role role);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);


}
