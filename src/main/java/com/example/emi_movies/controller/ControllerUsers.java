package com.example.emi_movies.controller;

import com.example.emi_movies.model.Users;
import com.example.emi_movies.repo.UsersRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")

public class ControllerUsers {

    private final UsersRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public ControllerUsers(UsersRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users user) {
        // Check if the username is already taken
        if (userRepository.findByNom(user.getUsernamelog()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Encode password before saving to the database
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
