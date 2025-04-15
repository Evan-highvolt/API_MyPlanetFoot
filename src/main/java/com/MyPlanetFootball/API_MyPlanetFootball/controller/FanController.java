package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.FanModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.FanService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("fan")
public class FanController {

    @Autowired
    private FanService fanService;


    @PostMapping("/")
    public ResponseEntity<FanModel> createFan(@RequestBody FanModel fan) throws URISyntaxException {
        FanModel savedFan = fanService.saveFan(fan);
        return ResponseEntity.created(new URI("/fans" + savedFan.getIdFan())).body(savedFan);
    }

    @GetMapping("/fans")
    public Iterable<FanModel> getFans() {
        return fanService.getAllFans();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FanModel> updateFan(@PathVariable Integer id, @RequestBody FanModel fanModel) {
        Optional<FanModel> fanModelOptional = fanService.getFanById(id);
        if (fanModelOptional.isPresent()) {
            FanModel fanModelToUpdate = fanModelOptional.get();

            String nomFan = fanModel.getNomFan();
            if (nomFan != null) {
                fanModelToUpdate.setNomFan(nomFan);
            }

            String prenomFan = fanModel.getPrenomFan();
            if (prenomFan != null) {
                fanModelToUpdate.setPrenomFan(prenomFan);
            }

            String emailFan = fanModel.getEmailFan();
            if (emailFan != null) {
                fanModelToUpdate.setEmailFan(emailFan);
            }

            String telephoneFan = fanModel.getTelephoneFan();
            if (telephoneFan != null) {
                fanModelToUpdate.setTelephoneFan(telephoneFan);
            }

            String photoFan = fanModel.getPhotoFan();
            if (photoFan != null) {
                fanModelToUpdate.setPhotoFan(photoFan);
            }

            LocalDate dateFan = fanModel.getDateFan();
            if (dateFan != null) {
                fanModelToUpdate.setDateFan(dateFan);
            }


            fanService.saveFan(fanModelToUpdate);
            return ResponseEntity.ok(fanModelToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

