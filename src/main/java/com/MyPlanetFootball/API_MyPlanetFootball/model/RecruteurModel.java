package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class RecruteurModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_rec")
    private Integer id;

    @NotBlank
    @Column(name = "prenom_rec")
    private String prenomRec;

    @Column(name = "nom_rec")
    @NotBlank
    private String nomRec;

    @Column(name = "email_rec")
    @Email(message = "Le format de l'email est invalide.")
    @NotBlank
    private String emailRec;

    @Column(name = "telephone_rec")
    @NotBlank
    private String telephoneRec;

    @Column(name = "photo_rec")
    private String photoRec;

    @Column(name = "date_rec")
    @NotNull
    @Past(message = "La date de naissance doit être dans le passé.")
    private LocalDate dateRec;

}
