package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "adresse")
public class AdresseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_adr")
    private Integer id;

    @Column(name = "numero_adr")
    private int numeroAdr;

    @Column(name = "rue_adr")
    private String rueAdr;

    @Column(name = "cp_adr")
    private String codePostaleAdr;

}
