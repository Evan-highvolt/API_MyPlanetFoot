package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class CompteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_cpt")
    private Integer idCpt;

    @NotBlank
    @Column(name = "mdp_cpt")
    private String mdpCpt;

    @NotBlank
    @Column(name = "login_cpt")
    private String loginCpt;

    @NotNull
    @Column(name = "est_inscrit_cpt")
    private Boolean estInscritCpt;

    @NotBlank
    @Column(name = "role_cpt")
    private String roleCpt;


    @Override
    public String toString() {
        return "CompteModel{" +
                "idCpt=" + idCpt +
                ", loginCpt='" + loginCpt + '\'' +
                ", estInscritCpt=" + estInscritCpt +
                ", roleCpt='" + roleCpt + '\'' +
                '}';
    }
}

