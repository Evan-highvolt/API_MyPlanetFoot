package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.JoueurService;
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
