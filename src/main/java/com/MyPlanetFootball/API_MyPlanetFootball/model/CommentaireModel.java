package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@NotNull
@Data
public class CommentaireModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_com")
    private Integer idComentaire;

    @NotNull
    @Min(value = 0,  message = "Le nombre de like ne peut pas être négatif.")
    @Column(name = "like_com")
    private int likeCom;

    @NotBlank(message = "Le commentatire ne peut pas être vide")
    @Column(name = "contenu_com")
    private String contenuCom;

    @Column(name = "date_com")
    private LocalDate dateCom = LocalDate.now();
}
