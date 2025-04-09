package com.MyPlanetFootball.API_MyPlanetFootball.model;


import jakarta.persistence.*;
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

    @Column(name = "id_pay")
    private Integer id;

    @Column(name = "nom_pay")
    private String nomPay;
}
