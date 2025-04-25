package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CarteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CarteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/carte")
@Validated
public class CarteController {
    @Autowired
    private CarteService carteService;

    @GetMapping()
    public ResponseEntity<?> getCarte(@Validated @RequestBody CarteModel carte) {
        try {
            Iterable<CarteModel> cateListe = carteService.getCartes();
            if (!cateListe.iterator().hasNext()) {
                log.warn("Liste est vide");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun carte trouver");
            }
            return ResponseEntity.ok().body(cateListe);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche de la liste des cartes : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche des cartes.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarteById(@PathVariable Integer id) {
        try {
            Optional<CarteModel> carteId = carteService.getCarteById(id);
            if (carteId.isPresent()) {
                return ResponseEntity.ok().body(carteId.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Carte avec l'id : " + id + " non trouvée.");
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la carte avec l'id {} : {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche de la carte avec l'id : " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarte(@PathVariable Integer id) {
        try {
            Optional<CarteModel> carteOpt = carteService.getCarteById(id);
            if (carteOpt.isPresent()) {
                carteService.deleteCarteById(id);
                return ResponseEntity.ok().body("Carte supprimée avec succès : " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Carte non tretrouvé avec l'id  : " + id);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la carte avec l'id {} : {}:", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche de la carte avec l'id : " + id);
        }
    }



}
