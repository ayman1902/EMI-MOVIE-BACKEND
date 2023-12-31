package com.example.emi_movies.service;

import com.example.emi_movies.model.Commentaire;
import com.example.emi_movies.model.FavoritedMovie;
import com.example.emi_movies.repo.FavoritedMovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFavoritedMovie {
    private final FavoritedMovieRepo favoritedMovieRepo;
    @Autowired
    public ServiceFavoritedMovie(FavoritedMovieRepo favoritedMovieRepo) {
        this.favoritedMovieRepo = favoritedMovieRepo;
    }
    public FavoritedMovie addFavoris(FavoritedMovie favoritedMovie){
        return favoritedMovieRepo.save(favoritedMovie);
    }
    public List<FavoritedMovie> findAllFavoris(){
        return favoritedMovieRepo.findAll();
    }

    public void deleteFavoris(Long id){
        favoritedMovieRepo.deleteById(id);
    }
    public void deleteFavorisByfilmId(Long idFilm){
        favoritedMovieRepo.deleteFavorisByIdFilm(idFilm);
    }
    public List<FavoritedMovie> findFavorisByIdFilm(Long idfilm){
        return (List<FavoritedMovie>) favoritedMovieRepo.findFavorisById_film(idfilm);

    }

    public FavoritedMovie updateFavoris(FavoritedMovie favoritedMovie) {
        return favoritedMovieRepo.save(favoritedMovie);
    }
}
