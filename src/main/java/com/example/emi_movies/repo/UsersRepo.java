package com.example.emi_movies.repo;

import com.example.emi_movies.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByNom(String nom);
}
