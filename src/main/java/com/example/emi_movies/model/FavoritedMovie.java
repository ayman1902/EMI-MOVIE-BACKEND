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

    //@Column(nullable = false)
    private String username;

    public FavoritedMovie() {
    }

    public FavoritedMovie(Long id, Long idfilm, boolean favorited,String username) {
        this.id = id;
        this.idfilm = idfilm;
        this.favorited = favorited;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
