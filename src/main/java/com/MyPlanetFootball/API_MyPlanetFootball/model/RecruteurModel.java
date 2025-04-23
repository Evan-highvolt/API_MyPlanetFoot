package com.MyPlanetFootball.API_MyPlanetFootball.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "recruteur")
public class RecruteurModel {
    @ManyToOne
    @JoinColumn(name = "id_clb", nullable = false)
    private ClubModel clubModel;

    @ManyToOne
    @JoinColumn(name = "id_adr", nullable = false)
    private AdresseModel adresseModel;

    @ManyToOne
    @JoinColumn(name = "id_cpt", nullable = false)
    private CompteModel compteModel;

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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
