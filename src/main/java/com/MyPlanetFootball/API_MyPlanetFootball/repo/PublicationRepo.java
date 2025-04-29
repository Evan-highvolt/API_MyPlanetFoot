package com.MyPlanetFootball.API_MyPlanetFootball.repo;


import com.MyPlanetFootball.API_MyPlanetFootball.model.PublicationModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PublicationRepo extends CrudRepository<PublicationModel, Integer> {
    List<PublicationModel> getPublicationModelByCompteModel_LoginCpt(String compteModelLoginCpt);

}
