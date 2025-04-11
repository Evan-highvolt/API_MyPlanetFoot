package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_adm")
    private Integer idAdm;

    @Column(name = "prenom_adm")
    @NotBlank
    private String prenomAdm;

    @Column(name = "nom_adm")
    @NotBlank
    private String nomAdm;

    @Column(name = "email_adm")
    @Email
    @NotBlank
    private String emailAdm;

    @Column(name = "telephone_adm")
    @NotBlank
    private String telephoneAdm;

    @Column(name = "photo_prfl_adm")
    @NotBlank
    private String photoPrflAdm;

    @Column(name = "date_adm")
    @NotNull
    @Past(message = "La date de naissance doit être dans le passé.")
    private LocalDate dateAdm;

}
