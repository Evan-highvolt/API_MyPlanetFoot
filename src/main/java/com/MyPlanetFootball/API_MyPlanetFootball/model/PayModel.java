package com.MyPlanetFootball.API_MyPlanetFootball.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * The type Pay model.
 */
@Data
@Entity
@Table(name = "pay")
public class PayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pay", nullable = false)
    private Integer id;

    @NotBlank
    @Column(name = "nom_pay")
    private String nomPay;
}
