package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CompteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Compte controller.
 */
@RestController
@Slf4j
@RequestMapping("/compte")
public class CompteController {
    @Autowired
    private CompteService compteService;

    /**
     * Get all comptes iterable.
     *
     * @return the iterable
     */
    @GetMapping()
    public Iterable<CompteModel> GetAllComptes() {
        try {
            return compteService.GetAllComptes();
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des comptes : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les villes", e);
        }
    }

    /**
     * Gets copmte by id.
     *
     * @param id the id
     * @return the copmte by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCopmteById(@PathVariable @Valid Integer id) {
        Optional<CompteModel> compteId = compteService.GetCompteById(id);
        if (compteId.isPresent()) {
            return ResponseEntity.ok().body(compteId.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Compte inexistant avec l'id : " + id);
        }
    }

    /**
     * Create compte response entity.
     *
     * @param compte the compte
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<String> createCompte(@RequestBody @Valid CompteModel compte) {
        try {
            compteService.createCopmte(compte);
            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body("Compte créée avec succès : " + compte.getLoginCpt());
        } catch (Exception e) {
            log.error("Erreur lors de la creation de compte : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la creation de compte : " + e.getMessage());
        }

    }

    /**
     * Update compte response entity.
     *
     * @param compte the compte
     * @param login  the login
     * @return the response entity
     */
    @PutMapping("{login}")
    public ResponseEntity<?> updateCompte(@RequestBody @Valid CompteModel compte, @PathVariable String login) {
        try {
            CompteModel compteUpdated = compteService.updateCompte(login, compte);
            return ResponseEntity.ok(compteUpdated);
        } catch (Exception e) {
            log.error("Erreur lors de la modification de compte : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la modification de compte : " + e.getMessage());
        }
    }

    /**
     * Delete compte response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable Integer id) {
        try {
            compteService.DeleteCompte(id);
            return ResponseEntity.ok("Compte supprimée avec succès !");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du compte : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la suppression de compte : " + e.getMessage());
        }
    }



}
