package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;


@Data
@Entity
@Table(name = "fan")
public class FanModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_fan")
    private Integer idFan;

    @Column(name = "prenom_fan")
    private String prenomFan;

    @Column(name = "nom_fan")
    private String nomFan;

    @Column(name = "email_fan")
    private String emailFan;

    @Column(name = "telephone_fan")
    private String telephoneFan;

    @Column(name = "photo_fan")
    private String photoFan;

    @Column(name = "date_fan")
    private LocalDate dateFan;


}
