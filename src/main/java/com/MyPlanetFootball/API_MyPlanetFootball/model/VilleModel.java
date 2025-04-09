package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "ville")
public class VilleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_vil")
    private Integer idVille;

    @Column(name = "nom_vil")
    private String nomVil;
}
