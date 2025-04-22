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

/**
 * The type Admin controller.
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Gets all admins.
     *
     * @return the all admins
     */
    @GetMapping()
    public Iterable<AdminModel> getAllAdmins() {
        try {
            return adminService.getAllAdmins();
        } catch (Exception e) {
            log.error("Erreur lors de la recherche des admins : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les admins");
        }
    }

    /**
     * Find admin by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
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

    /**
     * Create admin response entity.
     *
     * @param adminModel the admin model
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<?> createAdmin(@RequestBody @Valid AdminModel adminModel) {
        try {
           AdminModel newAdmin = adminService.createAdmin(adminModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Admin créée avec succès : " + newAdmin.getEmailAdm());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la creation d'admin : " + e.getMessage());
        }
    }

    /**
     * Update admin response entity.
     *
     * @param admin the admin
     * @param email the email
     * @return the response entity
     */
    @PutMapping("{email}")
    public ResponseEntity<?>updateAdmin(@RequestBody @Valid AdminModel admin, @PathVariable String email) {
        try {
            AdminModel updatedAdmin = adminService.updateAdmin(email, admin);
            return ResponseEntity.ok(updatedAdmin);
        } catch (Exception e) {
            log.error("Erreur lors de la modification d'admin : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur lors de la modification d'admin : " + e.getMessage());
        }
    }


    /**
     * Delete admin response entity.
     *
     * @param id the id
     * @return the response entity
     */
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
