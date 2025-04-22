package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "admin")
public class AdminModel {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cpt", nullable = false)
    private CompteModel compteModel;

//    @ManyToOne
//    @JoinColumn(name = "id_adr", nullable = false)
//    private AdresseModel adresseModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_adm")
    private Integer idAdm;


    @Column(name = "prenom_adm", nullable = false, length = 50)
    @NotBlank(message = "Le prenom ne doit pas être vide.")
    private String prenomAdm;

    @Column(name = "nom_adm", nullable = false, length = 50)
    @NotBlank(message = "Le nom ne doit pas être vide.")
    private String nomAdm;

    @Column(name = "email_adm", nullable = false, length = 255)
    @Email(message = "Le format de l'email est invalide.")
    @NotBlank(message = "Le mail ne doit pas être vide.")
    private String emailAdm;

    @Column(name = "telephone_adm", nullable = false, length = 20)
    @NotBlank(message = "Le telephone ne doit pas être vide")
    private String telephoneAdm;

    @Column(name = "photo_prfl_adm")
    private String photoPrflAdm;

    @Column(name = "respo_adm")
    private String respoAdm;

    @Column(name = "date_adm", nullable = false)
    @NotNull(message = "La date ne doit pas être vide.")
    @Past(message = "La date de naissance doit être dans le passé.")
    private LocalDate dateAdm;

}
