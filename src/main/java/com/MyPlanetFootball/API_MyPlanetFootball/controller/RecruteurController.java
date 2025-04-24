package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.RecruteurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.RecruteurService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/recruteur")
public class RecruteurController {
    @Autowired
    private RecruteurService recruteurService;

    @GetMapping()
    public ResponseEntity<?> getRecruteurs() {
        try {
            Iterable<RecruteurModel> recList = recruteurService.getAllRecruteurs();
            return ResponseEntity.ok().body(recList);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des recruteurs : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche des recruteurs : {}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecruteurById(@PathVariable Integer id) {
        try {
            Optional<RecruteurModel> recruteurId = recruteurService.GetRecruteurById(id);
            if (recruteurId.isPresent()) {
                return ResponseEntity.ok(recruteurId.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Recruteur non trouvée avec l'id : " + id);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du recruteur avec l'id {}{}:", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la recherche de recruteur avec l'id : " + id);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createRecruteur(@RequestBody @Valid RecruteurModel recruteurModel) {
        try {
            RecruteurModel newRecruteur = recruteurService.createRecruteur(recruteurModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(("Recruteur créée avec succès : " + newRecruteur.getEmailRec()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la creation du recruteur : " + e.getMessage());
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateRecruteur(@PathVariable String email, @RequestBody @Valid RecruteurModel recruteurModel) {
        try {
            RecruteurModel updatedRec = recruteurService.updateRecruteur(recruteurModel, email);
            return ResponseEntity.ok(updatedRec);
        } catch (Exception e) {
            log.error("Erreur lors de la modification du recruteur : {} {}",email, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la modification d'admin : "+ email + e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruteur(@PathVariable Integer id) {
        try {
            recruteurService.deleteRecruteur(id);
            return ResponseEntity.ok("Recruteur supprimée avec succès !");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du recruteur avec id {} : {}", id, e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression du recruteur.");
        }
    }



}
