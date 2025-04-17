package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdminModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.AdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public Iterable <AdminModel> getAllAdmins() {
        try {
            return adminRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des Admin : {}", e.getMessage());
            throw new RuntimeException("Impossible de supprimer les admins");
        }
    }

    public Optional<AdminModel> getAdminById(Integer id) {
        try {
            return adminRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de le compte avec l'ID: {}{}", e.getMessage(), id);
            throw new RuntimeException("Impossible d'afficher le compte avec l'ID: " + id);
        }
    }

    public void deleteAdminById(Integer id) {
        if (!adminRepo.existsById(id)) {
            throw new RuntimeException("Admin Inexistant avec l'ID : " + id);
        }
        try {
            log.warn("Suppresion du Admin avec l'ID : {} " , id);
            adminRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression d'admin avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer l'admin avec l'id :" + id);
        }
    }

}
