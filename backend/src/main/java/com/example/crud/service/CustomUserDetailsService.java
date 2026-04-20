package com.example.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("Loading user from DB...");

	//Optional<User> user=userRepository.findByUsername(username);


	User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("User Name not found"));

		


		/*
		 * return org.springframework.security.core.userdetails.User.withUsername(user.
		 * getUsername()).password(user.getPassword()).authorities(
		 * user.getRoles().stream() .map(Enum::name) // ROLE_ADMIN
		 * .toArray(String[]::new) ).build();
		 */

		//System.out.println("values ---"+org.springframework.security.core.userdetails.User.withUsername(
				//user.getUsername()).password(user.getPassword()).authorities("ROLE_"+user.getRole().name()).build());

		return org.springframework.security.core.userdetails.User.withUsername(
				user.getUsername()).password(user.getPassword()).authorities("ROLE_"+user.getRole().name()).build();
	}

}
