package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.VilleModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.VilleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Ville controller.
 */
@RestController
@Slf4j
@RequestMapping("/ville")
public class VilleController {
    @Autowired
    private VilleService villeService;

    /**
     * Gets ville.
     *
     * @return the ville
     */
    @GetMapping()
    public Iterable<VilleModel> getVille() {
        try {
            return villeService.GetAllVilles();
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des ville : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les villes");
        }
    }

    /**
     * Gets ville by id.
     *
     * @param id the id
     * @return the ville by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getVilleById(@PathVariable @Valid Integer id) {
        Optional<VilleModel> villeId = villeService.GetVilleById(id);
        if (villeId.isPresent()) {
            return ResponseEntity.ok().body(villeId.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ville non trouvée avec l'id : " + id);
        }
    }

    /**
     * Create ville response entity.
     *
     * @param ville the ville
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<String> createVille(@RequestBody @Valid VilleModel ville) {
        try {
            villeService.createVille(ville);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Ville créée avec succès : " + ville.getNomVil());
        }catch (RuntimeException rte) {
            log.error("Erreur lors de la création de la ville: {}", rte.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de la ville : " + rte.getMessage());
        }
    }

    /**
     * Update ville response entity.
     *
     * @param id    the id
     * @param ville the ville
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVille(@PathVariable @Valid Integer id ,@RequestBody VilleModel ville) {
        try {
            VilleModel updatedVille = villeService.updateVille(id, ville);
            return ResponseEntity.ok(updatedVille);
        } catch (RuntimeException rte) {
            log.error("Erreur lors de la mise a jour de la ville avec id {} : {}", id, rte.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la modification de l'id : " + id + rte.getMessage());
        }
    }

    /**
     * Delete ville response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVille(@PathVariable @Valid Integer id) {
        try {
            villeService.deleteVille(id);
            return ResponseEntity.ok("Ville supprimée avec succès !");
        } catch (RuntimeException rte) {
            log.error("Erreur lors de la suppression de la ville avec id {} : {}", id, rte.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la suppression de l'id : " + id + rte.getMessage());
        }
    }




}
