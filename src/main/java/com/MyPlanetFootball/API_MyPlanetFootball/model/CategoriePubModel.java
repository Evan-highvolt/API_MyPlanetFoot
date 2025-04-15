package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "categorie_pub")
public class CategoriePubModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_cat")
    private Integer idCat;

    @NotBlank
    @Column(name = "nom_cat", nullable = false)
    private String nomCat;


}
