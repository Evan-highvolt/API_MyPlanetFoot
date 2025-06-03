package com.MyPlanetFootball.API_MyPlanetFootball.service;


import com.MyPlanetFootball.API_MyPlanetFootball.model.AdminModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.model.ComptePrincipal;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.AdminRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

/**
 * The type Admin service.
 */
@Service
@Slf4j

public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private CompteRepo compteRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Gets all admins.
     *
     * @return the all admins
     */
    public Iterable <AdminModel> getAllAdmins() {
        try {
            return adminRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des Admin : {}", e.getMessage());
            throw new RuntimeException("Impossible de récupérer les admins");
        }
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AdminModel> adminModel = adminRepo.emailAdm(email);
        if (adminModel.isEmpty()) {
            log.error("L'admin n'est pas trouve");
            throw new UsernameNotFoundException(email);
        }
        return new ComptePrincipal();
    }



    /**
     * Gets admin by id.
     *
     * @param id the id
     * @return the admin by id
     */
    public Optional<AdminModel> getAdminById(Integer id) {
        try {
            return adminRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de le compte avec l'ID: {}{}", e.getMessage(), id);
            throw new RuntimeException("Impossible d'afficher le compte avec l'ID: " + id);
        }
    }

    /**
     * Create admin model.
     *
     * @param adminModel the admin model
     * @return the admin model
     */
    public AdminModel createAdmin(AdminModel adminModel) {
        try {
            Optional<AdminModel> existAdmin = adminRepo.emailAdm(adminModel.getEmailAdm());

            if (existAdmin.isPresent()) {
                throw new RuntimeException("Un administrateur existe déjà avec cet email : " + adminModel.getEmailAdm());
            }

            String rawPassword = adminModel.getCompteModel().getMdpCpt();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            adminModel.getCompteModel().setMdpCpt(encodedPassword);

            CompteModel savedCompte = compteRepo.save(adminModel.getCompteModel());
            adminModel.setCompteModel(savedCompte);

            return adminRepo.save(adminModel);

        } catch (Exception e) {
            log.error("Erreur lors de la création de l'admin : {}", e.getMessage(), e);
            throw new RuntimeException("Impossible de créer l'administrateur");
        }
    }

//    /**
//     * Update admin admin model.
//     *
//     * @param email      the email
//     * @param adminModel the admin model
//     * @return the admin model
//     */
//    public AdminModel updateAdmin (String email, AdminModel adminModel) {
//        try {
//            AdminModel updatedAdmin = adminRepo.emailAdm(email)
//                    .orElseThrow(() -> new RuntimeException("Admin introuvable avec l'id: " + email));
//
//
//            if (adminModel.getNomAdm() != null && !adminModel.getNomAdm().isBlank()) {
//                updatedAdmin.setNomAdm(adminModel.getNomAdm());
//            }
//
//            if (adminModel.getPrenomAdm() != null && !adminModel.getPrenomAdm().isBlank()) {
//                updatedAdmin.setPrenomAdm(adminModel.getPrenomAdm());
//            }
//
//
//            if (adminModel.getTelephoneAdm() != null && !adminModel.getTelephoneAdm().isBlank()) {
//                updatedAdmin.setTelephoneAdm(adminModel.getTelephoneAdm());
//            }
//
//            if (adminModel.getPhotoPrflAdm() != null) {
//                updatedAdmin.setPhotoPrflAdm(adminModel.getPhotoPrflAdm());
//            }
//
//            if (adminModel.getRespoAdm() != null) {
//                updatedAdmin.setRespoAdm(adminModel.getRespoAdm());
//            }
//
//            if (adminModel.getDateAdm() != null) {
//                updatedAdmin.setDateAdm(adminModel.getDateAdm());
//            }
//
//
//            return adminRepo.save(updatedAdmin);
//
//        } catch (Exception e) {
//            throw new RuntimeException("");
//        }
//    }

    public AdminModel updateAdmin (Integer id, AdminModel adminModel) {
        try {
            AdminModel updatedAdmin = adminRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Admin introuvable avec l'id: " + id));


            if (adminModel.getNomAdm() != null && !adminModel.getNomAdm().isBlank()) {
                updatedAdmin.setNomAdm(adminModel.getNomAdm());
            }

            if (adminModel.getPrenomAdm() != null && !adminModel.getPrenomAdm().isBlank()) {
                updatedAdmin.setPrenomAdm(adminModel.getPrenomAdm());
            }


            if (adminModel.getTelephoneAdm() != null && !adminModel.getTelephoneAdm().isBlank()) {
                updatedAdmin.setTelephoneAdm(adminModel.getTelephoneAdm());
            }

            if (adminModel.getPhotoPrflAdm() != null) {
                updatedAdmin.setPhotoPrflAdm(adminModel.getPhotoPrflAdm());
            }

            if (adminModel.getRespoAdm() != null) {
                updatedAdmin.setRespoAdm(adminModel.getRespoAdm());
            }

            if (adminModel.getDateAdm() != null) {
                updatedAdmin.setDateAdm(adminModel.getDateAdm());
            }


            return adminRepo.save(updatedAdmin);

        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }


    /**
     * Delete admin by id.
     *
     * @param id the id
     */
    public void deleteAdminById(Integer id) {
        if (!adminRepo.existsById(id)) {
            throw new RuntimeException("Admin inexistant avec l'ID : " + id);
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