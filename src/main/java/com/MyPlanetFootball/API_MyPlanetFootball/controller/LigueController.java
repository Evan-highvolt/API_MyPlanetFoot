package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.LigueModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.LigueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class LigueController {
    @Autowired
    private LigueService ligueService;

    @GetMapping()
    public ResponseEntity<?>getAllLigues() {
        try {
            Iterable<LigueModel> liguesList = ligueService.getAllLigues();
            return ResponseEntity.ok(liguesList);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des ligues : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne : impossible d'afficher les ligues");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLigueById(@PathVariable Integer id) {
        try {
            Optional<LigueModel> ligueOpt = ligueService.getLigueById(id);
            if (ligueOpt.isPresent()) {
                return ResponseEntity.ok(ligueOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ligue non trouvée avec l'id : " + id);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la ligue avec l'id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne lors de la récupération de la ligue.");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteLigueById(@PathVariable Integer id) {
        try {
            ligueService.deleteLigueById(id);
            return ResponseEntity.ok("Ligue supprimée avec succès !");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de la ligue avec id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne lors de la suppression de la ligue.");
        }
    }

}
