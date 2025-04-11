package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.PayModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.PayRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Pay service.
 */
@Service
@Slf4j
public class PayService {
    @Autowired
    private PayRepo pay;

    /**
     * Get all pay iterable.
     *
     * @return the iterable
     */
    public Iterable<PayModel>getAllPay(){
        try {
            return pay.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erreur lors de la recherche des adresses");
        }
    }

    /**
     * Create pay model.
     *
     * @param payModel the pay model
     * @return the pay model
     */
    public PayModel createPay(PayModel payModel){
        try {
            return pay.save(payModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erreur lors de la creation de la pay");
        }
    }

    /**
     * Get pay by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<PayModel> getPayById(Integer id){
        try {
            return pay.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erreur lors de la recherche d'un pay");
        }
    }

    /**
     * Delete pay by id.
     *
     * @param id the id
     * @return
     */
    public void deletePayById(Integer id){
        try {
            log.warn("Suppresion du pay avec l'id : {}", id);
            pay.deleteById(id);
        } catch (Exception e) {
            log.error("Problem lors de la suppression de", id);
            throw new RuntimeException("Impossible de supprimer la pay avec l'id : " + id);
        }

    }

}
