package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
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

    @Autowired
    private CompteRepo compteRepo;

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

    public JoueurModel createJoueur(JoueurModel joueurModel) {
        try {
            Optional<JoueurModel> existJoueur = joueurRepo.emailJou(joueurModel.getEmailJou());
            if (existJoueur.isPresent()) {
                throw new RuntimeException("Un joueur existe déjà avec cet email : " + joueurModel.getEmailJou());
            }
            if (joueurModel.getCompteModel() == null || joueurModel.getCompteModel().getIdCpt() == null) {
                throw new RuntimeException("L'ID du compte est requis pour créer un joueur.");
            }
            Integer compteId = joueurModel.getCompteModel().getIdCpt();
            CompteModel compte = compteRepo.findById(compteId)
                    .orElseThrow(() -> new RuntimeException("Compte inexistant avec l'id: " + compteId));

            joueurModel.setCompteModel(compte);
            return joueurRepo.save(joueurModel);
        } catch (Exception e) {
            log.error("Erreur lors de la création de le joueur : {}", e.getMessage(), e);
            throw new RuntimeException("Erreur lors de la création du joueur : " + e.getMessage());
        }
    }


    public JoueurModel updateJoueur(JoueurModel joueurModel, String emailJoueur) {
        try {
            JoueurModel updateJoueur = joueurRepo.emailJou(emailJoueur)
                    .orElseThrow(() -> new RuntimeException("Joueur introuvable avec le mail : " + emailJoueur));

            if (joueurModel.getNomJou() != null && !joueurModel.getNomJou().isBlank()) {
                updateJoueur.setNomJou(joueurModel.getNomJou());
            }

            if (joueurModel.getPrenomJou() != null && !joueurModel.getPrenomJou().isBlank()) {
                updateJoueur.setPrenomJou(joueurModel.getPrenomJou());
            }

            if (joueurModel.getTelephoneJou() != null && !joueurModel.getTelephoneJou().isBlank()) {
                updateJoueur.setTelephoneJou(joueurModel.getTelephoneJou());
            }

            if (joueurModel.getDateJou() != null) {
                updateJoueur.setDateJou(joueurModel.getDateJou());
            }

            log.info("Joueur mis à jour avec succès");

            return joueurRepo.save(updateJoueur);

        } catch (Exception e) {
            log.error("Impossible de mettre à jour le joueur : {}", e.getMessage());
            throw new RuntimeException("Impossible de mettre à jour le joueur.");
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
