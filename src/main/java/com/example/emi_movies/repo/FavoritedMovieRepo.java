package com.example.emi_movies.repo;

import com.example.emi_movies.model.Commentaire;
import com.example.emi_movies.model.FavoritedMovie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritedMovieRepo extends JpaRepository<FavoritedMovie,Long> {
    FavoritedMovie findByIdfilm(Long idfilm);

}
