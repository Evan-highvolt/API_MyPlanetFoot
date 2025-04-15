package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a football league entity in the application.
 * The LigueModel class maps to the "ligue" table in the database.
 * It contains information about the league's name, level, and its association with a country.
 * This entity is used to manage league-related data in the system.
 *
 * Annotations:
 * - @Entity: Specifies that this class is a JPA entity.
 * - @Table: Specifies the table in the database with which this entity is associated.
 * - Lombok's @Data: Generates boilerplate code such as getters, setters, equals, hashCode, and toString methods.
 *
 * Relationships:
 * - @ManyToOne: Indicates a many-to-one relationship with the PayModel entity, representing the country associated with the league.
 *
 * Columns:
 * - The idLegue field is the primary key for the "ligue" table.
 * - The nomLegue and niveauLegue fields represent the league's name and level, respectively, and are mandatory fields.
 */
@Entity
@Data
@Table(name = "ligue")
public class LigueModel {

    @ManyToOne
    @JoinColumn(name = "id_pay", nullable = false)
    private PayModel payModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_lig")
    private Integer idLegue;

    @NotBlank
    @Column(name = "nom_lig", nullable = false)
    private String nomLegue;

    @NotNull
    @Column(name = "niveau_lig", nullable = false)
    private int niveauLegue;


}
