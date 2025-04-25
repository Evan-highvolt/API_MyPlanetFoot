package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.RegionModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.RegionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Region controller.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;


    /**
     * Gets all regions.
     *
     * @return the all regions
     */
    @GetMapping("/regions")
    public ResponseEntity<?> getAllRegions() {
        try {
            Iterable<RegionModel> regions = regionService.getAllRegions();
            return ResponseEntity.ok(regions);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche d'une région : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les regions");
        }
    }

    /**
     * Gets region by id.
     *
     * @param id the id
     * @return the region by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable @Valid Integer id) {
        Optional<RegionModel> regionId = regionService.getRegionById(id);
        if (regionId.isPresent()) {
            return ResponseEntity.ok(regionId.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Région non trouvée avec l'id : " + id);
        }
    }

    /**
     * Create region response entity.
     *
     * @param region the region
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<String> createRegion(@RequestBody @Valid RegionModel region){
        try {
            regionService.createRegion(region);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Région créée avec succès : " + region.getNomReg());
        } catch (RuntimeException rte) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de la région : " + rte.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateRegion(@PathVariable @Valid Integer id, @RequestBody RegionModel region){
        try {
            RegionModel regionUpdated = regionService.updateRegion(id, region);
            return ResponseEntity.ok(regionUpdated);
        } catch (RuntimeException rte) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la modification de l'id : " + id + rte.getMessage());
        }
    }

    /**
     * Delete region response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable @Valid Integer id) {
        try {
            regionService.deleteRegionById(id);
            return ResponseEntity.ok("Région supprimée avec succès !");
        } catch (RuntimeException rte) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Échec de la suppression : " + rte.getMessage());
        }
    }


}
