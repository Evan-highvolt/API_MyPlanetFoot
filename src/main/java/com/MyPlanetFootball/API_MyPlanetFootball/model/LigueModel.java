package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class LigueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_lig")
    private Integer idLegue;

    @NotBlank
    @Column(name = "nom_lig")
    private String nomLegue;

    @NotNull
    @Column(name = "niveau_lig")
    private int niveauLegue;


}
