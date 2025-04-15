package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.PublicationModel;
import org.springframework.data.repository.CrudRepository;

public interface PublicationRepo extends CrudRepository<PublicationModel, Integer> {
}
