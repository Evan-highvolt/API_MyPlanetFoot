package com.MyPlanetFootball.API_MyPlanetFootball.repo;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdminModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepo extends CrudRepository<AdminModel, Integer> {
    Optional<AdminModel> emailAdm(String emailAdm);
}