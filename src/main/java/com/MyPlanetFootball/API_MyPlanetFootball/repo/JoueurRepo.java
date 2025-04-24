package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdminModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JoueurRepo extends CrudRepository<JoueurModel, Integer> {
    Optional<JoueurModel> emailJou(String emailJoueur);
}
