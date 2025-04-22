package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.RecruteurModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecruteurRepo extends CrudRepository<RecruteurModel, Integer> {
    Optional<RecruteurModel> emailRec(String emailRec);
}
