package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CarteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CarteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CarteService {
    @Autowired
    private CarteRepo carteRepo;

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
