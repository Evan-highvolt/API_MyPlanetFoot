package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.VilleModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.VilleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Ville service.
 */
@Service
@Slf4j
public class VilleService {
    @Autowired
    private VilleRepo villeRepo;

    /**
     * Get all villes iterable.
     *
     * @return the iterable
     */
    public Iterable<VilleModel> GetAllVilles() {
        try {
            return villeRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des villes: {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les villes");
        }
    }

    /**
     * Get ville by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<VilleModel> GetVilleById(Integer id) {
        try {
            return villeRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la villes avec l'ID: " + id, e);
            throw new RuntimeException("Impossible d'afficher les villes avec l'ID: " + id);
        }
    }

    /**
     * Create ville ville model.
     *
     * @param ville the ville
     * @return the ville model
     */
    public VilleModel createVille(VilleModel ville) {
        try {
            return villeRepo.save(ville);
        } catch (Exception e) {
            log.error("Erreur lors de la création de la ville", e);
            throw new RuntimeException("Impossible de créer la villes");
        }
    }

    /**
     * Update ville ville model.
     *
     * @param id           the id
     * @param updatedVille the updated ville
     * @return the ville model
     */
    public VilleModel updateVille(Integer id, VilleModel updatedVille) {
        try {
            VilleModel villeModel =  villeRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ville introuvable avec l'id : " + id));
            if (updatedVille != null && !updatedVille.getNomVil().isBlank()) {
                villeModel.setNomVil(updatedVille.getNomVil());
            }
            return villeRepo.save(villeModel);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la ville avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de mettre à jour la région avec l'id : " + id);
        }
    }

    /**
     * Delete ville.
     *
     * @param id the id
     */
    public void deleteVille(Integer id) {
        if(!villeRepo.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Ville introuvable avec l'id : " + id);
        }
        try {
            log.warn("Suppresion de la régionville avec l'id : {}", id);
            villeRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression de la ville avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer la ville avec l'id : " + id);
        }
    }


}
