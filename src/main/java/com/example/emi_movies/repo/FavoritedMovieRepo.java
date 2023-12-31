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
    @Query("SELECT f FROM FavoritedMovie f WHERE f.idfilm = :idfilm")
    List<FavoritedMovie> findFavorisById_film(Long idfilm);
    @Modifying
    @Query("DELETE FROM FavoritedMovie f WHERE f.idfilm = :idfilm")
    @Transactional
    void deleteFavorisByIdFilm(@Param("idfilm") Long idfilm);
    /*
    @Query("UPDATE FavoritedMovie f SET f.favorited = :favorited WHERE f.idfilm = :idfilm")
    void updateFavorisByIdFilm(@Param("favorited") boolean favorited, @Param("idFilm") Long idfilm);*/
}
