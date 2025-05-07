package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.PublicationModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CompteService;
import com.MyPlanetFootball.API_MyPlanetFootball.service.PublicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/publication")
public class PublicationController {
    @Autowired
    private PublicationService pubService;

    @Autowired
    private CompteService compteService;


    @GetMapping()
    public ResponseEntity<?> getPublications() {
        try {
            Iterable<PublicationModel> publication = pubService.getpublications();
            return ResponseEntity.ok(publication);
        } catch (Exception e){
            log.error("Erreur lors de la recherche des publication : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Problem lors de la recuperation de la liste" );
        }
    }


    @GetMapping("/{mail}")
    public ResponseEntity<?> getPublicationsParCompte(@PathVariable String mail) {
        try {
            Optional<CompteModel> compteOpt = compteService.GetCompteByLogin(mail);
            if (compteOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Le compte n'est pas trouvé");
            }
            return ResponseEntity.ok(compteOpt.get());
        } catch (Exception e) {
            log.error("Erreur lors de la recherche du compte : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Problème lors de la récupération des publications");
        }
    }

    @PostMapping()
    public ResponseEntity<?> createPublication(@Valid @RequestBody PublicationModel publication) {
        try {
            PublicationModel newPub = pubService.createPublication(publication);
            return ResponseEntity.status(HttpStatus.CREATED).body( "Publication créée avec succès :" + newPub.getId() );
        } catch (Exception e) {
            log.error("Erreur lors de la création de la publication : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la creation de la publication : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable Integer id) {
        try {
            pubService.deletePublication(id);
            return ResponseEntity.ok().body("Publication deleted");
        } catch (Exception e){
            log.error("Erreur lors de la recherche du publication : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Problem lors de la recherche du publication");
        }

    }

}
