package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.JoueurService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/joueur")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    @GetMapping()
    public ResponseEntity<?> getAllJoueurs() {
        try {
            Iterable<JoueurModel> JoueurList = joueurService.getJoueurs();
            return ResponseEntity.ok(JoueurList);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des joueurs : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche des joueurs : {}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJoueurById(@PathVariable Integer id) {
        try {
            Optional<JoueurModel> JoueurOpt = joueurService.getJoueurById(id);
            if (JoueurOpt.isPresent()) {
                return ResponseEntity.ok(JoueurOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(("Joueur non trouvé avec l'id  : " + id));
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du joueur avec l'id {} : {}:", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche du joueur avec l'id : " + id);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createJoueur(@RequestBody @Valid JoueurModel joueurModel) {
        try {
            JoueurModel newJoueur = joueurService.createJoueur(joueurModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Joueur créée avec succès : " + newJoueur.getEmailJou());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la creation du joueur : " + e.getMessage());
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateJoueur(@PathVariable String email, @RequestBody @Valid JoueurModel joueurModel) {
        try {
            JoueurModel joueurToUpdate = joueurService.updateJoueur(joueurModel, email);
            return ResponseEntity.ok(joueurToUpdate);
        } catch (Exception e) {
            log.error("Erreur lors de la modification du joueur : {} {}",email, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la modification du joueur avec l’email " + email + " : " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJoueurById(@PathVariable Integer id) {
        try {
            joueurService.deleteJoueurById(id);
            return ResponseEntity.ok("Joueur supprimé avec succès !");
        } catch (RuntimeException e) {
                log.error("Erreur lors de la suppression du joueur avec id {} : {}", id, e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Le joueur avec l'ID " + id + " n'existe pas ou ne peut pas être supprimé.");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du joueur avec id {} : {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du joueur avec l'id :" + id);
        }
    }


}
