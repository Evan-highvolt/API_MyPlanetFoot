package com.MyPlanetFootball.API_MyPlanetFootball;


import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CompteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CompteServiceIntegrationTest {

    @Autowired
    private CompteService compteService;

    @Autowired
    private CompteRepo compteRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateCompte_Success() {
        CompteModel compte = new CompteModel();
        compte.setLoginCpt("testuser");
        compte.setMdpCpt("password123");
        compte.setRoleCpt("UTILISATEUR");

        CompteModel savedCompte = compteService.createCopmte(compte);

        assertNotNull(savedCompte.getIdCpt());
        assertEquals("testuser", savedCompte.getLoginCpt());
        assertTrue(passwordEncoder.matches("password123", savedCompte.getMdpCpt()));
        assertEquals("UTILISATEUR", savedCompte.getRoleCpt());

        System.out.println("Nom " + savedCompte.getLoginCpt() + " MDP= " + savedCompte.getMdpCpt() );
    }
}
