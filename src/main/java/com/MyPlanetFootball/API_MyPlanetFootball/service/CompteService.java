package com.MyPlanetFootball.API_MyPlanetFootball.service;

import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CompteService {

    @Autowired
    private CompteRepo compteRepo;


    public Iterable<CompteModel> GetAllComptes() {
        try {
            return compteRepo.findAll();
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des comptes : {}", e.getMessage());
            throw new RuntimeException("Impossible d'afficher les comptes");
        }
    }


    public Optional<CompteModel> GetCompteById( Integer id) {
        try {
            return compteRepo.findById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de le compte avec l'ID: {}{}", e.getMessage(), id);
            throw new RuntimeException("Impossible d'afficher le compte avec l'ID: " + id);
        }
    }

    public Optional<CompteModel> GetCompteByLogin(String login) {
        try {
                return compteRepo.findByLoginCpt(login);
        } catch (Exception e) {
            log.error("Compte introuvable avec l'login : {}{}", e.getMessage(), login);
            throw new RuntimeException("Compte introuvable avec l'login" + login, e);
        }
    }

    public CompteModel createCopmte(CompteModel compte) {
        try {
            if (compte.getLoginCpt() == null || compte.getLoginCpt().isBlank()) {
                throw new RuntimeException("Le login est obligatoire");
            }

            if (compteRepo.findByLoginCpt(compte.getLoginCpt()).isPresent()) {
                throw new RuntimeException("Ce login est déjà utilisé.");
            }


            if (compte.getMdpCpt() == null || compte.getMdpCpt().isBlank()) {
                throw new RuntimeException("Le mdp est obligatoire");
            }


            return compteRepo.save(compte);
        } catch (Exception e) {
            log.error("Erreur lors de la création du compte", e);
            throw new RuntimeException("Impossible de créer le compte " + e.getMessage());
        }
    }

    public CompteModel updateCompte(String login, CompteModel updatedCompte) {
        try {
            CompteModel existingCompte = compteRepo.findByLoginCpt(login)
                    .orElseThrow(() -> new RuntimeException("Compte inexistant avec l'email : " + login));
            if (updatedCompte.getLoginCpt() == null || updatedCompte.getLoginCpt().isBlank()) {
                throw new RuntimeException("Le login est obligatoire");
            }

            if (updatedCompte.getRoleCpt() == null || updatedCompte.getRoleCpt().isBlank()) {
                throw new RuntimeException("Le role est obligatoire");
            }

            existingCompte.setLoginCpt(updatedCompte.getLoginCpt());

            return compteRepo.save(existingCompte);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du compte : {}", e.getMessage());
            throw new RuntimeException("Impossible de modifier le compte" + e.getMessage());
        }

    }

    public CompteModel updateMdp(String login, String newMdp, String confirmMdp ) {
        try {
            CompteModel compte = compteRepo.findByLoginCpt(login)
                    .orElseThrow(() -> new RuntimeException("Compte inexistant avec l'email : " + login));
            if (newMdp == null || newMdp.isBlank() || confirmMdp == null || confirmMdp.isBlank()) {
                throw new RuntimeException("Le deux champ de mot de pass sont obligatoire");
            }

            if (!newMdp.equals(confirmMdp)) {
                throw new RuntimeException("Les deux mots de passs doivent etre idantique");
            }

            compte.setMdpCpt(newMdp);
            return compteRepo.save(compte);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du mot de passe : {}", e.getMessage());
            throw new RuntimeException("Impossible de modifier le mot de passe : " + e.getMessage());
        }
    }

    public void DeleteCompte(Integer id) {
        if (!compteRepo.existsById(id)) {
            throw new RuntimeException("Compte inexistant avec l'id : " + id);
        }
        try {
            log.warn("Suppresion du compte avec l'id : {}", id);
            compteRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Problème lors de la suppression du compte avec l'id : {}", id, e);
            throw new RuntimeException("Impossible de supprimer le compte avec l'id :" + id);
        }
    }




}
