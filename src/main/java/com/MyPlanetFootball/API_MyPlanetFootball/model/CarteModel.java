package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * The type Carte model.
 */
@Entity
@Data
@Table(name = "carte_joueur")
public class CarteModel {
    @OneToOne
    @JoinColumn(name = "id_jou")
    private JoueurModel joueurModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_crt")
    private Integer id;

    @Column(name = "post_crt")
    @NotBlank
    private String postCrt;

    @Min(value = 0, message = "Le nombre de matchs ne peut pas être négatif.")
    @NotNull
    @Column(name = "nbr_match_crt")
    private int nbrMatchCrt;

    @Column(name = "vid_jou")
    private String vidJou;

}
