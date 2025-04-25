package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.LigueModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.PayModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.LigueRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.PayRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LigueService {
    @Autowired
    private LigueRepo ligueRepo;
    @Autowired
    private PayRepo payRepo;

    public Iterable<LigueModel> getAllLigues() {
        try {
            return ligueRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des ligues : {}", e.getMessage());
            throw new RuntimeException("Impossible de récupérer les ligues");
        }
    }

    public Optional<LigueModel> getLigueById(Integer id) {
        try {
            return ligueRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de le ligue avec l'ID: {}{}", e.getMessage(), id);
            throw new RuntimeException("Impossible d'afficher le ligue avec l'ID: " + id);
        }
    }

    public LigueModel createLigue(LigueModel ligueModel) {
        try {
            if (ligueRepo.nomLegue(ligueModel.getNomLegue()).isPresent()) {
                log.warn("Tentative de création d'une ligue déjà existante : {}", ligueModel.getNomLegue());
                throw new RuntimeException("Une ligue portant ce nom existe déjà dans ce pays.");
            }

            if (ligueModel.getNiveauLegue() <= 0) {
                log.error("Niveau de ligue invalide : {}", ligueModel.getNiveauLegue());
                throw new RuntimeException("Le niveau de ligue invalide : " + ligueModel.getNiveauLegue());
            }

            LigueModel newLigue = ligueRepo.save(ligueModel);
            log.info("Ligue créée avec succès : {}", newLigue);
            return newLigue;

        } catch (Exception e) {
            log.error("Erreur lors de la création de la ligue : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible de créer la ligue.");
        }
    }

    public LigueModel updateLigue(LigueModel ligueModel, Integer id) {
        try {
            LigueModel existLigue = ligueRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ligue n'existe pas"));

            if (ligueModel.getNomLegue() != null && !ligueModel.getNomLegue().isBlank()) {
                existLigue.setNomLegue(ligueModel.getNomLegue());
            }

            if (ligueModel.getNiveauLegue() > 0) {
                existLigue.setNiveauLegue(ligueModel.getNiveauLegue());
            }

            if (ligueModel.getPayModel() != null && ligueModel.getPayModel().getId() != null) {
                PayModel newPay = payRepo.findById(ligueModel.getPayModel().getId())
                        .orElseThrow(() -> new RuntimeException("Pays introuvable avec l'id : "
                                + ligueModel.getPayModel().getId()));
                existLigue.setPayModel(newPay);
            }

            LigueModel updated = ligueRepo.save(existLigue);
            log.info("Ligue mise à jour avec succès : {}", updated.getNomLegue());
            return updated;

        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la ligue : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible de mettre à jour la ligue.");
        }

    }


    public void deleteLigueById(Integer id) {
        if (!ligueRepo.existsById(id)) {
            throw new RuntimeException("Ligue inexistant avec l'ID : " + id);
        }

        try {
            log.warn("Suppresion du ligue avec l'ID : {} " , id);
            ligueRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression de ligue avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer le ligue avec l'id :" + id);
        }
    }


}
