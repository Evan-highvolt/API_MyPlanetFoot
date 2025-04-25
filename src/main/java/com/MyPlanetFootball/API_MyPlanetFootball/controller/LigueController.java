package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.LigueModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.LigueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@Validated
@RequestMapping("/ligue")
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

    @PostMapping()
    public ResponseEntity<?> createLigue(@RequestBody @Valid LigueModel ligueModel) {

        try {
            LigueModel newLigue = ligueService.createLigue(ligueModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(("Ligue créée avec succès : " + newLigue));
        } catch (Exception e) {
            log.error("Erreur lors de la creation de ligue : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la creation de la ligue : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLigue(@PathVariable Integer id, @RequestBody @Valid LigueModel ligueModel) {
        try {
            LigueModel updatedLigue = ligueService.updateLigue( ligueModel, id);
            return ResponseEntity.ok("Ligue mise à jour avec succès : " + updatedLigue.getNomLegue());
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la ligue avec id {} : {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la mise à jour de la ligue : " + e.getMessage());
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
