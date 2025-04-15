package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.RegionModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.RegionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public Iterable<RegionModel> getAllRegions() {
        try {
            return regionService.getAllRegions();
        } catch (Exception e) {
            log.error("Erreur lors de la recherche d'une région : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible d'afficher les regions");
        }
    }

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


}
