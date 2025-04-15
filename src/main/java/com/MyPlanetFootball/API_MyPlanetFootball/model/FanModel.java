package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Data;


import java.time.LocalDate;


@Data
@Entity
@Table(name = "fan")
public class FanModel{

    @ManyToOne
    @JoinColumn(name = "id_adr", nullable = false)
    private AdresseModel adresseModel;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_fan")
    private Integer idFan;

    @NotBlank(message = "Le prenom ne doit pas être vide.")
    @Column(name = "prenom_fan", nullable = false, length = 50)
    private String prenomFan;

    @NotBlank
    @Column(name = "nom_fan", nullable = false, length = 50)
    private String nomFan;

    @Email(message = "")
    @NotBlank(message = "Le mail ne doit pas être vide.")
    @Column(name = "email_fan")
    private String emailFan;

    @NotBlank(message = "Le téléphone ne doit pas être vide.")
    @Column(name = "telephone_fan", nullable = false, length = 20)
    private String telephoneFan;

    @Column(name = "photo_fan")
    private String photoFan;

    @NotEmpty
    @Past(message = "La date de naissance doit être dans le passé.")
    @Column(name = "date_fan", nullable = false)
    private LocalDate dateFan;


}
