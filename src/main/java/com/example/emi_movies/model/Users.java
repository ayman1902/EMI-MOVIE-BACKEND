package com.example.emi_movies.model;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String password;

    @Column(nullable = false,unique = true)
    private String usernamelog;
    private String gmail;

    public Users() {
    }

    public Users(Long id, String nom, String prenom, String password, String gmail,String usernamelog) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.gmail = gmail;
        this.usernamelog=usernamelog;
    }
    public String getUsernamelog() {
        return usernamelog;
    }

    public void setUsernamelog(String usernamelog) {
        this.usernamelog = usernamelog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
