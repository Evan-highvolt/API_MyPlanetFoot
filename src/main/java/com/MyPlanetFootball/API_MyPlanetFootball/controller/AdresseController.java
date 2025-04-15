package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdresseModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.AdresseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Adresse controller.
 */
@RestController
@RequestMapping("/adresse")
public class AdresseController {
    @Autowired
    private AdresseService adresseService;


    /**
     * Get all adresses iterable.
     *
     * @return the iterable
     */
    @GetMapping("/adresses")
    public Iterable<AdresseModel> getAllAdresses(){
        return adresseService.getAllAdresse();
    }

    /**
     * Get adresse adresse model.
     *
     * @param id the id
     * @return the adresse model
     */
    @GetMapping("/{id}")
    public AdresseModel getAdresse(@PathVariable Integer id){
        Optional<AdresseModel> adresseModel = adresseService.getAdresseById(id);
        return adresseModel.orElse(null);
    }

    public ResponseEntity<AdresseModel> createAdresse(@RequestBody AdresseModel adresseModel){
        return ResponseEntity.ok(adresseService.createAdresse(adresseModel));
    }

    /**
     * Update adresse response entity .
     *
     * @param id           the id
     * @param adresseModel the adresse model
     * @return the response entity
     */
    @PutMapping("/adresse/{id}")
    public ResponseEntity<AdresseModel>updateAdresse(@PathVariable Integer id, @RequestBody AdresseModel adresseModel){
        Optional<AdresseModel> adresseModelOptional = adresseService.getAdresseById(id);
        if(adresseModelOptional.isPresent()){
            AdresseModel adresseUpdate = adresseModelOptional.get();

            Integer numAdresse = adresseModel.getNumeroAdr();
            if(numAdresse != null){
                adresseUpdate.setNumeroAdr(numAdresse);
            }

            String rueAdresse = adresseModel.getRueAdr();
            if(rueAdresse != null){
                adresseUpdate.setRueAdr(rueAdresse);
            }

            String cpAdresse = adresseModel.getCodePostaleAdr();
            if(cpAdresse != null){
                adresseUpdate.setCodePostaleAdr(cpAdresse);
            }

            adresseService.createAdresse(adresseUpdate);
            return ResponseEntity.ok(adresseUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }




    }

    /**
     * Delete adresse.
     *
     * @param id the id
     */
    @DeleteMapping("/adresse/{id}")
    public void deleteAdresse(@PathVariable Integer id){
        adresseService.deleteAdresse(id);
    }

}
