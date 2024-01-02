package com.example.emi_movies.controller;

import com.example.emi_movies.model.Commentaire;
import com.example.emi_movies.model.FavoritedMovie;
import com.example.emi_movies.service.ServiceFavoritedMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Favoris")
@CrossOrigin(origins = "*")
public class ControllerFavoritedMovie {
    private final ServiceFavoritedMovie serviceFavoritedMovie;
    @Autowired
    public ControllerFavoritedMovie(ServiceFavoritedMovie serviceFavoritedMovie) {
        this.serviceFavoritedMovie = serviceFavoritedMovie;
    }
    @GetMapping("/favorite")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public ResponseEntity<List<FavoritedMovie>> getFavoriteMovieIds() {
        List<FavoritedMovie> favoriteMovieIds = serviceFavoritedMovie.getFavoriteMovie();
        return ResponseEntity.ok(favoriteMovieIds);
    }

    @PostMapping("/add/{movieId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> addFavoriteMovie(@RequestBody FavoritedMovie favoriteMovies,
                                              @PathVariable("movieId") Long movieId) {
        favoriteMovies.setIdfilm(movieId);
        serviceFavoritedMovie.addFavoriteMovie(favoriteMovies);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> addFavoriteMovieraw(@RequestBody FavoritedMovie favoriteMovies) {
        serviceFavoritedMovie.addFavoriteMovie(favoriteMovies);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> deleteFavoriteMovie(@PathVariable("id") Long id) {
        serviceFavoritedMovie.deleteFavoriteMovie(id);
        return ResponseEntity.ok().build();
    }


}
