package com.MyPlanetFootball.API_MyPlanetFootball;


import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.repo.CompteRepo;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CompteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompteTest {

    @InjectMocks
    private CompteService compteService;

    @Mock
    private CompteRepo compteRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetCompte() {

    }

    @Test
    void testCreateCompte() {
        CompteModel compte = new CompteModel();
        compte.setLoginCpt("evan");
        compte.setMdpCpt("password");

        when(compteRepo.findByLoginCpt("evan")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(compteRepo.save(any(CompteModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CompteModel saved = compteService.createCopmte(compte);

        assertNotNull(saved);
        assertEquals("hashedPassword", saved.getMdpCpt());
        verify(compteRepo).save(any(CompteModel.class));
    }


    @Test
    void testGetCompteById() {
        CompteModel compte = new CompteModel();
        compte.setIdCpt(1);
        compte.setLoginCpt("evan");

        when(compteRepo.findById(1)).thenReturn(Optional.of(compte));

        Optional<CompteModel> result = compteService.GetCompteById(1);
        assertTrue(result.isPresent());
        assertEquals("evan", result.get().getLoginCpt());
    }

    @Test
    void testUpdateMdp() {
        CompteModel compte = new CompteModel();
        compte.setLoginCpt("evan");
        compte.setMdpCpt("password");

        when(compteRepo.findByLoginCpt("evan")).thenReturn(Optional.of(compte));
        when(passwordEncoder.encode("123456")).thenReturn("mdpHashe");
        when(compteRepo.save(any(CompteModel.class))).thenAnswer(i -> i.getArgument(0));

        CompteModel updated = compteService.updateMdp("evan", "123456", "123456");

        assertEquals("mpdHashe", updated.getMdpCpt());
        verify(passwordEncoder).encode("123456");
        verify(compteRepo).save(compte);
    }


    @Test
    void testDeleteCompte() {
        when(compteRepo.existsById(1)).thenReturn(true);
        doNothing().when(compteRepo).deleteById(1);

        assertDoesNotThrow(() -> compteService.DeleteCompte(1));
        verify(compteRepo).deleteById(1);
    }

    @Test
    void testDeleteCompte_NonExistent() {
        when(compteRepo.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                compteService.DeleteCompte(99)
        );

        assertEquals("Compte inexistant avec l'id : 99", ex.getMessage());
    }
}
