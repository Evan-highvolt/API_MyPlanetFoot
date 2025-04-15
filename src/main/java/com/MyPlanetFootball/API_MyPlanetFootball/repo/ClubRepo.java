package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.ClubModel;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepo extends CrudRepository<ClubModel, Integer> {
}
