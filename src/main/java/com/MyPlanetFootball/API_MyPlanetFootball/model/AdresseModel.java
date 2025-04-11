package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity

@Table(name = "adresse")

public class AdresseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_adr")
    private Integer id;

    @NotEmpty
    @Column(name = "numero_adr")
    private int numeroAdr;

    @NotEmpty
    @Column(name = "rue_adr")
    private String rueAdr;

    @NotEmpty
    @Column(name = "cp_adr")
    private String codePostaleAdr;

}
