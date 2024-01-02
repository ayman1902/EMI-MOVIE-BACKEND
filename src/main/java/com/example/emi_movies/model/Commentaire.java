package com.example.emi_movies.model;

import jakarta.persistence.*;

@Entity
public class Commentaire {
    public Commentaire(Long id, Long idFilm, String name, String commentaire) {
        this.id = id;
        idfilm = idFilm;
        this.name = name;
        this.commentaire = commentaire;
    }

    public Commentaire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(nullable = false,updatable = false)
    private Long id;
    private Long idfilm;
    private String name;
    private String commentaire;

    public Long getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(Long idfilm) {
        this.idfilm = idfilm;
    }
}
