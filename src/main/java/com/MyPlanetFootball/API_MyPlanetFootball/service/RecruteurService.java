package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.RecruteurModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.RecruteurRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RecruteurService {
    @Autowired
    private RecruteurRepo recruteurRepo;
    @Autowired
    private CompteRepo compteRepo;

    public Iterable<RecruteurModel> getAllRecruteurs() {
        try {
           return recruteurRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des recreuteurs : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les recreuteurs.");
        }
    }


    public Optional<RecruteurModel>GetRecruteurById(Integer id) {
        try {
            return recruteurRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de recruteur avec l'id : {}{}" ,id, e.getMessage());
            throw new RuntimeException("Impossible d'afficher le recreuteur avec l'id: " +id);
        }
    }

    public RecruteurModel createRecruteur(RecruteurModel recruteurModel) {
        try {
            Optional<RecruteurModel> existRecruteur = recruteurRepo.emailRec(recruteurModel.getEmailRec());

            if (existRecruteur.isPresent()) {
                throw new RuntimeException("Un recruteur existe déjà avec cet email : " + recruteurModel.getEmailRec());
            }

            Integer compteId = recruteurModel.getCompteModel().getIdCpt();
            CompteModel compte = compteRepo.findById(compteId)
                    .orElseThrow(() -> new RuntimeException("Compte inexistant avec l'id: " + compteId));

            recruteurModel.setCompteModel(compte);
            return recruteurRepo.save(recruteurModel);
        } catch (Exception e) {
            log.error("Erreur lors de la création du recruteur : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible de créer le recruteur.");
        }
    }

    public RecruteurModel updateRecruteur(RecruteurModel recruteurModel, String email) {
        try {
            RecruteurModel updatedRecruteur = recruteurRepo.emailRec(email)
                    .orElseThrow(() -> new RuntimeException("Recruteur inexistant avec l'email : " + email));

                if (recruteurModel.getPrenomRec() != null && !recruteurModel.getPrenomRec().isBlank()) {
                    updatedRecruteur.setPrenomRec(recruteurModel.getPrenomRec());
                }

                if (recruteurModel.getNomRec() != null && !recruteurModel.getNomRec().isBlank()) {
                    updatedRecruteur.setNomRec(recruteurModel.getNomRec());
                }

                if (recruteurModel.getTelephoneRec() != null && !recruteurModel.getTelephoneRec().isBlank()) {
                    updatedRecruteur.setTelephoneRec(recruteurModel.getTelephoneRec());
                }

                if (recruteurModel.getDateRec() != null ){
                    updatedRecruteur.setDateRec(recruteurModel.getDateRec());
                }

                updatedRecruteur.setPhotoRec(recruteurModel.getPhotoRec());
                return recruteurRepo.save(updatedRecruteur);

        } catch (Exception e) {
            log.error("Impossible de mettre a jour le recruteur : {}", e.getMessage());
            throw new RuntimeException("Impossible de mettre a jour le recruteur.");
        }
    }

    public void deleteRecruteur(Integer id) {
        if (!recruteurRepo.existsById(id)) {
            throw new RuntimeException("Recruteur inexistant avec l'ID : " + id);
        }
        try {
            log.warn("Suppresion du recruteur avec l'ID : {} " , id);
            recruteurRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression de recruteur avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer le recruteur avec l'id :" + id);
        }
    }

}
