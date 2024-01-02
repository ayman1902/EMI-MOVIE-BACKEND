package com.example.emi_movies.service;

import com.example.emi_movies.model.Commentaire;
import com.example.emi_movies.model.FavoritedMovie;
import com.example.emi_movies.repo.FavoritedMovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceFavoritedMovie {
    private final FavoritedMovieRepo favoritedMovieRepo;
    @Autowired
    public ServiceFavoritedMovie(FavoritedMovieRepo favoritedMovieRepo) {
        this.favoritedMovieRepo = favoritedMovieRepo;
    }
    public List<FavoritedMovie> getFavoriteMovie() {
        return favoritedMovieRepo.findAll();
    }


    public FavoritedMovie addFavoriteMovie(FavoritedMovie favoriteMovies) {
        return favoritedMovieRepo.save(favoriteMovies);
    }

    public void deleteFavoriteMovie(Long idfilm) {
        // Find the FavoriteMovies entity by movieId and delete it if it exists
        FavoritedMovie favoriteMovie = favoritedMovieRepo.findByIdfilm(idfilm);
        if (favoriteMovie != null) {
            favoritedMovieRepo.delete(favoriteMovie);
        } else {
        }
    }
}
