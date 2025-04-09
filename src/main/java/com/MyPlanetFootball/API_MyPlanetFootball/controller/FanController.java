package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.FanModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.FanService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Setter
public class FanController {

    @Autowired
    private FanService fanService;

    @GetMapping("/")
    public String Hello() {
        return "Hello World";
    }

    @PostMapping("/fan")
    public FanModel createFan(@RequestBody FanModel fan) {
        return fanService.saveFan(fan);
    }

    @GetMapping("/fans")
    public Iterable<FanModel> getFans() {
        return fanService.getAllFans();
    }



}
