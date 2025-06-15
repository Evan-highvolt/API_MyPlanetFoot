package com.MyPlanetFootball.API_MyPlanetFootball;

import com.MyPlanetFootball.API_MyPlanetFootball.controller.CompteController;
import com.MyPlanetFootball.API_MyPlanetFootball.model.CompteModel;
import com.MyPlanetFootball.API_MyPlanetFootball.service.CompteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.awaitility.Awaitility.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompteController.class)
public class CompteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompteService compteService;

    @Test
    public void getCompte() throws Exception {

        CompteService list = (CompteService) compteService.GetAllComptes();

        List<CompteService> allComptes = Arrays.asList(list);
        given()

        mockMvc.perform(get("/compte")).andExpect(status().isOk());
    }
}
