package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.RegionModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.RegionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * The type Region service.
 */
@Service
@Slf4j
public class RegionService {
    @Autowired
    private RegionRepo regionRepo;

    /**
     * Get all regions iterable.
     *
     * @return the iterable
     */
    public Iterable<RegionModel>getAllRegions() {
        try {
            return regionRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des régions", e);
            throw new RuntimeException("Impossible d'afficher les régions");
        }

    }

    /**
     * Get region by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<RegionModel> getRegionById(Integer id){
        try {
            return regionRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la recherche d'une région avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de trouver la région");
        }
    }

    /**
     * Create region region model.
     *
     * @param regionModel the region model
     * @return the region model
     */
    public RegionModel createRegion(RegionModel regionModel) {
        try {
            return regionRepo.save(regionModel);
        } catch (Exception e) {
            log.error("Erreur lors de la création de la région", e);
            throw new RuntimeException("Impossible de creer la région");
        }
    }

    /**
     * Update region region model.
     *
     * @param id            the id
     * @param updatedRegion the updated region
     * @return the region model
     */
    public RegionModel updateRegion(Integer id, RegionModel updatedRegion) {
        try {
            RegionModel regionModel = regionRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Région introuvable avec l'id : " + id));

            if (updatedRegion.getNomReg() != null && !updatedRegion.getNomReg().isBlank()) {
                regionModel.setNomReg(updatedRegion.getNomReg());
            }

            return regionRepo.save(regionModel);
        }  catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la région avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de mettre à jour la région avec l'id : " + id);
        }
    }


    /**
     * Delete region by id.
     *
     * @param id the id
     */
    public void deleteRegionById(Integer id) {
        if (!regionRepo.existsById(id)) {
            throw  new RuntimeException("Impossible de supprimer : région introuvable avec l'id : " + id);
        }
        try {
            log.warn("Suppresion de la région avec l'id : {}", id);
            regionRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression de la région avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer la région avec l'id : + id)");
        }
    }



}
