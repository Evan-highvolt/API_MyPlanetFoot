package com.MyPlanetFootball.API_MyPlanetFootball.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a publication entity in the system.
 * This class is mapped to the "publication" table in the database.
 * It contains information about a publication, including associated user account,
 * content details, and metadata such as like count and comment count.
 * The publication also tracks the creation date.
 *
 * Constraints and validation rules:
 * - The like count and comment count must be non-negative.
 * - The content cannot be blank and has a maximum length of 1500 characters.
 * - The publication date cannot be null and defaults to the current date.
 *
 * Relationships:
 * - It is associated with a user account through a many-to-one relationship with the {@link CompteModel}.
 */
@Data
@Entity
@Table(name = "publication")
public class PublicationModel {
    @ManyToOne
    @JoinColumn(name = "id_cpt", nullable = false)
    @JsonBackReference
    private CompteModel compteModel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pub")
    private Integer id;

    @Min(value = 0, message = "Le nombre de like ne peut pas être négatif.")
    @NotNull
    @Column(name = "nbr_like_pub")
    private int nbrLikePub;

    @NotBlank(message = "Le contenu ne peut pas être vide.")
    @Column(name = "contenu_pub", nullable = false, length = 1500)
    private String contenuPub;

    @NotNull
    @Min(value = 0, message = "Le nombre de matchs ne peut pas être négatif.")
    @Column(name = "nbr_commentair_pub")
    private int nbrCommentairePub;

    @Column(name = "date_pub", nullable = false)
    private LocalDate datePub = LocalDate.now();

}
