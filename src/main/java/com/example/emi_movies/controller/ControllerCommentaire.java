package com.example.emi_movies.controller;

import com.example.emi_movies.model.Commentaire;
import com.example.emi_movies.service.ServiceCommentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Commentaire")
@CrossOrigin(origins = "*")
public class ControllerCommentaire {
    private final ServiceCommentaire serviceCommentaire;
    @Autowired
    public ControllerCommentaire(ServiceCommentaire serviceCommentaire) {
        this.serviceCommentaire = serviceCommentaire;
    }
    @GetMapping("/commentaires")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Commentaire>> getAllCommentaire(){
        List<Commentaire> employees = serviceCommentaire.findAllCommentaire();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/find/{idfilm}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Commentaire>> getEmployeeById(@PathVariable("idfilm") Long idfilm){
        List<Commentaire> employee= serviceCommentaire.findCommentaireByIdFilm(idfilm);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public ResponseEntity<Commentaire> addEmployee(@RequestBody Commentaire commentaire){
        Commentaire newemployee= serviceCommentaire.addCommentaire(commentaire);
        return new ResponseEntity<>(newemployee, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        serviceCommentaire.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

