package com.MyPlanetFootball.API_MyPlanetFootball.service;


import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.PublicationModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.PublicationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PublicationService {
    @Autowired
    private PublicationRepo pubRepo;

    @Autowired
    private CompteRepo compteRepo;


    public Iterable<PublicationModel>getpublications() {
        try {

            return pubRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de la recherche des publication pour le compte", e);
            throw new RuntimeException("Erreur lors de la récupération des publications : " + e.getMessage(), e);
        }
    }

    public List<PublicationModel> getPubByCompte(String emailCpt) {
        try {
           Optional<CompteModel> pubCpt = compteRepo.findByLoginCpt(emailCpt);
           if (pubCpt.isEmpty()) {
               throw new RuntimeException("Aucun compte trouvé avec l'email : " + emailCpt);
           }
            return pubRepo.getPublicationModelByCompteModel_LoginCpt(emailCpt);
        } catch (Exception e) {
            log.error("Problème lors de la récupération des publications du compte : {}", emailCpt, e);
            throw new RuntimeException("Impossible de récupérer les publications du compte : " + emailCpt);
        }

    }

    public PublicationModel createPublication(PublicationModel publication) {
        try {
            Optional<CompteModel> comptOpt = compteRepo.findByLoginCpt(publication.getCompteModel().getLoginCpt());
            if (comptOpt.isEmpty()) {
                log.warn("Tentative de création d'une publication avec un compte inexistant : {}", publication.getCompteModel().getLoginCpt());
                throw new RuntimeException("Le compte n'existe pas.");
            }
            CompteModel compte = comptOpt.get();
            publication.setCompteModel(compte);
            log.info("Publication créée avec succès");
            return pubRepo.save(publication);
        } catch (Exception e) {
            log.error("Erreur lors de la création de la publication : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible de créer la publication.", e);
        }
    }

    public void deletePublication(Integer id) {
        if (!pubRepo.existsById(id)) {
            throw new RuntimeException("Publication inexistant avec l'ID : " + id);
        }
        try {
            log.info("Le publication pour le compte : {}", id);
            pubRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Impossible de supprimer la publication avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer la publication : " + id + " : " + e.getMessage(), e);
        }

    }


}
