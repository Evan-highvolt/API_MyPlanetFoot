package com.MyPlanetFootball.API_MyPlanetFootball.controller;

import com.MyPlanetFootball.API_MyPlanetFootball.model.AdminModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.AdminService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public Iterable<AdminModel> getAllAdmins() {
        try {
            return adminService.getAllAdmins();
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des admins : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les admins");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAdminById(@PathVariable Integer id) {
        try {
            Optional<AdminModel> adminId = adminService.getAdminById(id);
            if (adminId.isPresent()) {
                return ResponseEntity.ok(adminId.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Admin non trouvée avec l'id : " + id);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de l'admin avec l'id {} :", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération de l'admin.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable @Valid Integer id) {
        try {
            adminService.deleteAdminById(id);
            return ResponseEntity.ok("Admin supprimée avec succès ! ");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du admin avec id {} : {}", id, e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression du admin.");
        }
    }


}
