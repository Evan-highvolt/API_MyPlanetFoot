package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.PayModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.PayService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Pay controller.
 */
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * Get all pay iterable.
     *
     * @return the iterable
     */
    @GetMapping("/pays")
    public Iterable<PayModel>getAllPay(){
        return payService.getAllPay();
    }

    /**
     * Create pay pay model.
     *
     * @param payModel the pay model
     * @return the pay model
     */
    @PostMapping("/pay")
    public PayModel createPay(@RequestBody PayModel payModel){
        return payService.createPay(payModel);
    }

    /**
     * Get pay by id pay model.
     *
     * @param id the id
     * @return the pay model
     */
    @GetMapping("/pay/{id}")
    public PayModel getPayById(@PathVariable Integer id){
        Optional<PayModel> payModel = payService.getPayById(id);
        return payModel.orElse(null);
    }

    /**
     * Delete pay.
     *
     * @param id the id
     */
    @DeleteMapping("/pay/{id}")
    public void deletePay(@PathVariable Integer id){
        payService.deletePayById(id);
    }

    /**
     * Update pay response entity.
     *
     * @param id       the id
     * @param payModel the pay model
     * @return the response entity
     */
    @PutMapping("/pay/{id}")
    public ResponseEntity<Object> updatePay(@PathVariable Integer id, @RequestBody PayModel payModel){
        Optional<PayModel> payModelOptional = payService.getPayById(id);
        if(payModelOptional.isPresent()){
            PayModel payModelToUpdate = payModelOptional.get();

            String nomPay = payModel.getNomPay();
            if(nomPay != null){
                payModelToUpdate.setNomPay(nomPay);
            }

            payService.createPay(payModelToUpdate);
            return ResponseEntity.ok(payModelToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
