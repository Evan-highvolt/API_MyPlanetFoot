package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity

@Table(name = "adresse")

public class AdresseModel {
    @ManyToOne
    @JoinColumn(name = "id_vil", nullable = false)
    private VilleModel villeModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_adr")
    private Integer idAdresse;

    @NotNull(message = "Le champs 'Ville' est obligatoire")
    @Column(name = "numero_adr", nullable = false)
    private int numeroAdr;

    @NotBlank(message = "Le champs 'Rue' est obligatoire")
    @Column(name = "rue_adr", nullable = false, length = 100)
    private String rueAdr;

    @NotBlank(message = "Le champs 'Code Postale' est obligatoire")
    @Column(name = "cp_adr", nullable = false, length = 10)
    private String codePostaleAdr;

}
