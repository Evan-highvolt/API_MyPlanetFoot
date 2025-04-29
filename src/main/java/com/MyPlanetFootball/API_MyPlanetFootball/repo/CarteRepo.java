package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CarteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.JoueurModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarteRepo extends CrudRepository<CarteModel, Integer> {
    Optional<CarteModel> findByJoueurModel(JoueurModel joueur);
}
