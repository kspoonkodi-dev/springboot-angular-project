package com.example.crud.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        String adminUsername = "admin";

        if (userRepository.findByUsername(adminUsername).isPresent()) {
            System.out.println("Admin user already exists");
            return;
        }

        User admin = new User();
        admin.setUsername(adminUsername);
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@gmail.com");
        admin.setStatus(User.Status.ACCEPTED);
        admin.setRole(User.Role.ADMIN);

        userRepository.save(admin);

        System.out.println("Admin user created successfully");
    }
}

