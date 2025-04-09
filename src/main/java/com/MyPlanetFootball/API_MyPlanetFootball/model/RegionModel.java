package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "region")
public class RegionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_reg")
    private Integer idReg;

    @Column(name = "nom_reg")
    private String nomReg;


}
