package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CarteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CarteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.JoueurRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CarteService {
    @Autowired
    private CarteRepo carteRepo;
    @Autowired
    private JoueurRepo joueurRepo;


    public Iterable<CarteModel> getCartes() {
        try {
            if (carteRepo.count() == 0) {
                log.warn("Liste de cartes st vide");
                throw new Exception("Liste de cartes st vide");
            }
            log.info("Récupération de la liste des cartes effectuée.");
            return carteRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la liste des cartes : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les cartes.");
        }
    }

    public Optional<CarteModel> getCarteById(Integer id) {
        try {
            Optional<CarteModel> carteModel = carteRepo.findById(id);
            if (carteModel.isPresent()) {
                log.info("Carte récupérée avec succès pour l'id : {}", id);
            } else {
                log.warn("Aucune carte trouvée pour l'id : {}", id);
            }
            return carteModel;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la carte avec l'id : {}{}" ,id, e.getMessage());
            throw new RuntimeException("Impossible d'afficher de la carte avec l'id: " +id);
        }
    }

    public CarteModel createCarte(CarteModel carteModel) {
        if (carteModel.getJoueurModel() == null || carteModel.getJoueurModel().getIdJou() == null) {
            throw new RuntimeException("L'ID du joueur est requis pour créer une carte.");
        }
        JoueurModel joueur = joueurRepo.findById(carteModel.getJoueurModel().getIdJou())
                .orElseThrow(() -> new RuntimeException("Joueur introuvable avec ID: " + carteModel.getJoueurModel().getIdJou()));

        Optional<CarteModel> existingCarte = carteRepo.findByJoueurModel(joueur);
        if (existingCarte.isPresent()) {
            throw new RuntimeException("Le joueur a déjà une carte.");
        }
        carteModel.setJoueurModel(joueur);
        return carteRepo.save(carteModel);
    }

    public CarteModel updateCarte(CarteModel carteModel, Integer idJoueur) {
        try {

            JoueurModel joueur = joueurRepo.findById(idJoueur)
                    .orElseThrow(() -> new RuntimeException("Joueur introuvable avec ID: " + idJoueur));

            CarteModel existeCarte = carteRepo.findByJoueurModel(joueur)
                    .orElseThrow(() -> new RuntimeException("Aucune carte trouvée pour le joueur avec ID: " + idJoueur));

            existeCarte.setPostCrt(carteModel.getPostCrt());
            existeCarte.setNbrMatchCrt(carteModel.getNbrMatchCrt());
            existeCarte.setVidJou(carteModel.getVidJou());

            return carteRepo.save(existeCarte);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la carte pour le joueur avec ID: {}", idJoueur, e);
            throw new RuntimeException("Erreur lors de la mise à jour de la carte : " + e.getMessage(), e);
        }

    }


    public void deleteCarteById(Integer id) {
        try {
            if (!carteRepo.existsById(id)) {
                log.warn("Aucune carte trouvée pour suppression avec l'id : {}", id);
                throw new RuntimeException("Carte inexistant avec l'ID : " + id);
            }
            log.info("Suppression de la carte avec l'ID : {}", id);
            carteRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression de la carte avec l'id : {} - {}", id, e.getMessage(), e);
            throw new RuntimeException("Impossible de supprimer la carte avec l'id : " + id);
        }
    }

}
