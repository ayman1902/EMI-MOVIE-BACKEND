package com.example.emi_movies.repo;

import com.example.emi_movies.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentaireRepo extends JpaRepository<Commentaire,Long> {
    @Query("SELECT c FROM Commentaire c WHERE c.idfilm = :idfilm")
    List<Commentaire> findCommentaireById_film(Long idfilm);
}
