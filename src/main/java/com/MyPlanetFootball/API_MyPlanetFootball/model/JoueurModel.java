package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

/**
 * The type Joueur model.
 */
@Entity
@Data
@Table(name = "joueur")
public class JoueurModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_jou")
    private Integer idJou;

    @NotBlank
    @Column(name = "prenom_jou")
    private String prenomJou;

    @NotBlank
    @Column(name = "nom_jou")
    private String nomJou;

    @NotBlank
    @Column(name = "email_jou")
    private String emailJou;

    @NotBlank
    @Column(name = "telephone_jou")
    private String telephoneJou;

    @Column(name = "photo_jou")
    private String photoJou;

    @Column(name = "cv_jou")
    private String cvJou;

    @NotNull
    @Past(message = "La date de naissance doit être dans le passé.")
    @Column(name = "date_jou")
    private LocalDate dateJou;


}
