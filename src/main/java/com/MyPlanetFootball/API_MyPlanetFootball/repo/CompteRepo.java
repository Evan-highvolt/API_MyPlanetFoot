package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepo extends CrudRepository<CompteModel, Integer> {
    Optional<CompteModel> findByLoginCpt(String loginCpt);
}
