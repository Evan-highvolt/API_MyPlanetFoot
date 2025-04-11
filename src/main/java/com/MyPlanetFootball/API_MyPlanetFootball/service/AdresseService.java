package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdresseModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.AdresseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Adresse service.
 */
@Service
@Slf4j

public class AdresseService {

    @Autowired
    private AdresseRepo adresseRepo;

    /**
     * Get all adresse iterable.
     *
     * @return the iterable
     */
    public Iterable<AdresseModel> getAllAdresse(){
        try{
            return adresseRepo.findAll();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erreur lors de la recherche des adresses");
        }

    }

    /**
     * Get adresse by id adresse model.
     *
     * @param id the id
     * @return the adresse model
     */
    public Optional<AdresseModel> getAdresseById(Integer id){
        try {
            log.debug("Recherche de l'adresse avec l'id : {}", id);
            return adresseRepo.findById(id);
        } catch(Exception e) {
            log.error("Erreur lors de la récupération de l'adresse avec l'id {} : {}", id, e.getMessage());
            throw new RuntimeException("Impossible de récupérer l'adresse.");

        }

    }

    /**
     * Create adresse adresse model.
     *
     * @param adresse the adresse
     * @return the adresse model
     */
    public AdresseModel createAdresse(AdresseModel adresse){
        log.info("Création d'une nouvelle adresse : {}", adresse);
        return adresseRepo.save(adresse);
    }


    /**
     * Delete adresse.
     *
     * @param id the id
     */
    public void deleteAdresse(Integer id){
        log.warn("Suppression de l'adresse avec l'id : {}", id);
        adresseRepo.deleteById(id);
    }
}
