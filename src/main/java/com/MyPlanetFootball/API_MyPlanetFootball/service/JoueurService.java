package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.JoueurRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class JoueurService {
    @Autowired
    private JoueurRepo joueurRepo;

    public Iterable<JoueurModel> getJoueurs() {
        try {
            return joueurRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la liste des joueurs : {}", e.getMessage());
            throw new RuntimeException("Impossible de récupérer les joueurs : " + e.getMessage());
        }
    }

    public Optional<JoueurModel> getJoueurById(Integer id) {
        try {
            return joueurRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du joueur avec l'ID: {}{}", e.getMessage(), id);
            throw new RuntimeException("Impossible d'afficher le joueur avec l'ID: " + id);
        }
    }






    public void deleteJoueurById(Integer id) {
        if (!joueurRepo.existsById(id)) {
            throw new RuntimeException("Le joueur n'existe pas");
        }

        try {
            log.warn("Suppresion du joueur avec l'ID : {} " , id);
            joueurRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression du joueur avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer le joueur avec l'id :" + id);
        }
    }

}
