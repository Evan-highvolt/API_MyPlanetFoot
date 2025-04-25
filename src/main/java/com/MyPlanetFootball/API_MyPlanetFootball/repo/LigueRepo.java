package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.LigueModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LigueRepo extends CrudRepository<LigueModel, Integer> {
    Optional<LigueModel> nomLegue(String ligueNom);
}
