package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.FanModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.FanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FanService {

    @Autowired
    private FanRepo fanRepo;

    public Iterable<FanModel> getAllFans() {
        return fanRepo.findAll();
    }

    public FanModel saveFan(FanModel fan) {
        return fanRepo.save(fan);
    }

}
