package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.LigueModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.LigueRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LigueService {
    @Autowired
    private LigueRepo ligueRepo;

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
