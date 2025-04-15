package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


/**
 * Represents a comment entity within the system.
 * This class is mapped to the "commentaire" table in the database.
 * Relationships:
 * - The comment is linked to a user account through a many-to-one relationship with {@link CompteModel}.
 * - The comment is also linked to a publication through a many-to-one relationship with {@link PublicationModel}.
 * Fields and constraints:
 * - `idComentaire`: The unique identifier for the comment, automatically generated.
 * - `compteModel`: The associated user account who made the comment. This field cannot be null.
 * - `publicationModel`: The associated publication for the comment. This field cannot be null.
 * - `likeCom`: The number of likes for the comment, which cannot be negative.
 * - `contenuCom`: The content of the comment, which cannot be empty and has a maximum length of 255 characters.
 * - `dateCom`: The date the comment was created. This field cannot be null and defaults to the current date.
 */

@Entity
@Data
@Table(name = "commentaire")
public class CommentaireModel {
    @ManyToOne
    @JoinColumn(name = "id_cpt", nullable = false)
    private CompteModel compteModel;

    @ManyToOne
    @JoinColumn(name ="id_pub", nullable = false)
    private PublicationModel publicationModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_com")
    private Integer idComentaire;

    @NotNull
    @Min(value = 0,  message = "Le nombre de like ne peut pas être négatif.")
    @Column(name = "like_com")
    private int likeCom;

    @NotBlank(message = "Le commentatire ne peut pas être vide")
    @Column(name = "contenu_com", nullable = false, length = 255)
    private String contenuCom;

    @Column(name = "date_com", nullable = false)
    private LocalDate dateCom = LocalDate.now();
}
