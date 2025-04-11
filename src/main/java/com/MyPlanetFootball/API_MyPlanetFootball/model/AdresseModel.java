package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "numero_adr")
    private int numeroAdr;

    @NotBlank
    @Column(name = "rue_adr")
    private String rueAdr;

    @NotBlank
    @Column(name = "cp_adr")
    private String codePostaleAdr;

}
