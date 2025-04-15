package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity

@Table(name = "ville")
public class VilleModel {
    @ManyToOne
    @JoinColumn(name = "id_reg")
    private RegionModel regionModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_vil")
    private Integer idVille;

    @NotBlank
    @Column(name = "nom_vil", nullable = false)
    private String nomVil;
}
