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
        return pay.findAll();
    }

    /**
     * Create pay model.
     *
     * @param payModel the pay model
     * @return the pay model
     */
    public PayModel createPay(PayModel payModel){
        return pay.save(payModel);
    }

    /**
     * Get pay by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<PayModel> getPayById(Integer id){
        return pay.findById(id);
    }

    /**
     * Delete pay by id.
     *
     * @param id the id
     */
    public void deletePayById(Integer id){
        pay.deleteById(id);
    }

}
