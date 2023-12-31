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
public class ControllerFavoritedMovie {
    private final ServiceFavoritedMovie serviceFavoritedMovie;
    @Autowired
    public ControllerFavoritedMovie(ServiceFavoritedMovie serviceFavoritedMovie) {
        this.serviceFavoritedMovie = serviceFavoritedMovie;
    }
    @GetMapping("/favorited")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<FavoritedMovie>> getAllCommentaire(){
        List<FavoritedMovie> favoritedMovies = serviceFavoritedMovie.findAllFavoris();
        return new ResponseEntity<>(favoritedMovies, HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<FavoritedMovie> addFavoris(@RequestBody FavoritedMovie favoritedMovie){
        FavoritedMovie favoritedMovieAdd= serviceFavoritedMovie.addFavoris(favoritedMovie);
        return new ResponseEntity<>(favoritedMovieAdd, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<FavoritedMovie> updateFavoris(@RequestBody FavoritedMovie favoritedMovie){
        FavoritedMovie favoritedMovieupdated = serviceFavoritedMovie.updateFavoris(favoritedMovie);
        return new ResponseEntity<>(favoritedMovieupdated, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> deleteFavoris(@PathVariable("id") Long id) {
        serviceFavoritedMovie.deleteFavoris(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteByIdFilm/{idfilm}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> deleteFavorisByIdFilm(@PathVariable("idfilm") Long idfilm) {
        serviceFavoritedMovie.deleteFavorisByfilmId(idfilm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/find/{idfilm}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<FavoritedMovie>> getFavorisById(@PathVariable("idfilm") Long idfilm){
        List<FavoritedMovie> favoritedMovieCommentaireByIdFilm= serviceFavoritedMovie.findFavorisByIdFilm(idfilm);
        return new ResponseEntity<>(favoritedMovieCommentaireByIdFilm, HttpStatus.OK);
    }

}
