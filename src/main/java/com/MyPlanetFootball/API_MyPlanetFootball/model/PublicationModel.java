package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class PublicationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pub")
    private Integer id;

    @Min(value = 0, message = "Le nombre de like ne peut pas être négatif.")
    @NotNull
    @Column(name = "nbr_like_pub")
    private int nbrLikePub;

    @NotBlank(message = "Le contenu ne peut pas être vide.")
    @Column(name = "contenu_pub")
    private String contenuPub;

    @NotNull
    @Min(value = 0, message = "Le nombre de matchs ne peut pas être négatif.")
    @Column(name = "nbr_commentaire_pub")
    private int nbrCommentairePub;

    @Column(name = "date_pub")
    private LocalDate datePub = LocalDate.now();

}
