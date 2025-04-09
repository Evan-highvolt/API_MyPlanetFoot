package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdresseModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.AdresseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Adresse service.
 */
@Service

public class AdresseService {
    @Autowired
    private AdresseRepo adresse;
    @Autowired
    private AdresseRepo adresseRepo;

    /**
     * Get all adresse iterable.
     *
     * @return the iterable
     */
    public Iterable<AdresseModel> getAllAdresse(){
        return adresseRepo.findAll();
    }

    /**
     * Get adresse by id adresse model.
     *
     * @param id the id
     * @return the adresse model
     */
    public Optional<AdresseModel> getAdresseById(Integer id){
        return adresseRepo.findById(id);
    }

    /**
     * Create adresse adresse model.
     *
     * @param adresse the adresse
     * @return the adresse model
     */
    public AdresseModel createAdresse(AdresseModel adresse){
        return adresseRepo.save(adresse);
    }


    /**
     * Delete adresse.
     *
     * @param id the id
     */
    public void deleteAdresse(Integer id){
        adresseRepo.deleteById(id);
    }
}
