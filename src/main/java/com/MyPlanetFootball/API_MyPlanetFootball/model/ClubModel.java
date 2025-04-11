package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ClubModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_clb")
    private Integer idClub;

    @Column(name = "nom_clb")
    @NotBlank
    private String nomClub;

    @Column(name = "photo_clb")
    private String photoClub;

    @Column(name = "email_clb")
    @Email
    @NotBlank
    private String emailClub;

    @Column(name = "telephone_clb")
    @NotBlank
    private String telephoneClub;

    @Column(name = "date_clb")
    @NotNull
    @PastOrPresent(message = "La date ne peut pas être dans le futur.")
    private LocalDate dateClub;


}
