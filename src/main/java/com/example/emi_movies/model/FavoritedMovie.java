package com.example.emi_movies.model;

import jakarta.persistence.*;

@Entity
public class FavoritedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idfilm;

    @Column(nullable = false)
    private boolean favorited;

    public FavoritedMovie() {
    }

    public FavoritedMovie(Long id, Long idfilm, boolean favorited) {
        this.id = id;
        this.idfilm = idfilm;
        this.favorited = favorited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(Long idfilm) {
        this.idfilm = idfilm;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
