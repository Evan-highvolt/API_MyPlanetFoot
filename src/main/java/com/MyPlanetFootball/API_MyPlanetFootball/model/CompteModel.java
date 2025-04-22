package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "compte")
public class CompteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_cpt")
    private Integer idCpt;

    @NotBlank
    @Column(name = "mdp_cpt", nullable = false, length = 255)
    private String mdpCpt;

    @NotBlank
    @Column(name = "login_cpt", nullable = false, unique = true)
    private String loginCpt;

    @NotBlank
    @Column(name = "role_cpt", nullable = false)
    private String roleCpt;


    @Override
    public String toString() {
        return "CompteModel{" +
                "idCpt=" + idCpt +
                ", loginCpt='" + loginCpt + '\'' +
                ", roleCpt='" + roleCpt + '\'' +
                '}';
    }
}

