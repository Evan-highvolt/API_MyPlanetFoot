package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

import jakarta.validation.constraints.PastOrPresent;

@Entity
@Data
@Table(name = "club")
public class ClubModel {
    @ManyToOne
    @JoinColumn(name = "id_cpt", nullable = false)
    private CompteModel compteModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_clb")
    private Integer idClub;

    @Column(name = "nom_clb", nullable = false, length = 50)
    @NotBlank
    private String nomClub;

    @Column(name = "photo_clb")
    private String photoClub;

    @Column(name = "email_clb", nullable = false, unique = true)
    @Email(message = "L'email n'est pas valide.")
    @NotBlank(message = "Le champ 'Email' ne doit pas être vide.")
    private String emailClub;

    @Column(name = "telephone_clb", nullable = false, length = 20)
    @NotBlank(message = "Le champ 'Téléphone' ne doit pas être vide.")
    private String telephoneClub;

    @Column(name = "date_clb", nullable = false)
    @NotNull(message = "Le champ 'Date du club' est obligatoire.")
    @PastOrPresent(message = "La date ne peut pas être dans le futur.")
    private LocalDate dateClub;


}
