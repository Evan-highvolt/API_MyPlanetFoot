package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.time.LocalDate;


@Data
@Entity
@Table(name = "fan")
public class FanModel{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adr")
    private AdresseModel adresseModel;

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_fan")
    private Integer idFan;

    @NotEmpty
    @Column(name = "prenom_fan")
    private String prenomFan;

    @NotEmpty
    @Column(name = "nom_fan")
    private String nomFan;

    @NotEmpty
    @Column(name = "email_fan")
    private String emailFan;

    @NotEmpty
    @Column(name = "telephone_fan")
    private String telephoneFan;

    @Column(name = "photo_fan")
    private String photoFan;

    @NotEmpty
    @Column(name = "date_fan")
    private LocalDate dateFan;


}
