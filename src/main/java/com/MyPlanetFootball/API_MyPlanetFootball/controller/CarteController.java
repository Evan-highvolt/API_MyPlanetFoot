package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CarteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CarteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.JoueurRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CarteService;
import com.MyPlanetFootball.API_MyPlanetFootball.service.JoueurService;
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
@RequestMapping("/carte")
@Validated
public class CarteController {
    @Autowired
    private CarteService carteService;

    @Autowired
    private JoueurService joueurService;


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

    @PostMapping()
    public ResponseEntity<?>createCarte(@RequestBody @Valid CarteModel carteModel) {
        try {

            if (joueurService.getJoueurById(carteModel.getJoueurModel().getIdJou()).isEmpty()) {
                throw new RuntimeException("L'ID du joueur est requis pour créer une carte.");
            }

            JoueurModel joueur = joueurService.getJoueurById(carteModel.getJoueurModel().getIdJou())
                    .orElseThrow(() -> new RuntimeException("Joueur introuvable avec ID: " + carteModel.getJoueurModel().getIdJou()));

            carteModel.setJoueurModel(joueur);
            CarteModel newCarte = carteService.createCarte(carteModel);

            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body("Carte créée avec succès : " + newCarte);

        }  catch (Exception e) {
            log.error("Erreur lors de la création de la carte : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de la carte : " + e.getMessage());
        }
    }

    @PutMapping("/{idJoueur}")
    public ResponseEntity<?> updateJoueur(@PathVariable Integer idJoueur, @RequestBody @Valid CarteModel carteModel) {
        try {
            CarteModel carteToUpdate = carteService.updateCarte(carteModel, idJoueur);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Carte mise à jour avec succès : " + carteToUpdate);
        } catch (RuntimeException rte) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : " + rte.getMessage());
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
