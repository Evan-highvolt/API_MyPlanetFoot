package com.MyPlanetFootball.API_MyPlanetFootball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity

@Table(name = "region")
public class RegionModel {
    @ManyToOne
    @JoinColumn(name = "id_pay")
    private PayModel payModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_reg")
    private Integer idReg;

    @NotBlank
    @Column(name = "nom_reg", nullable = false)
    private String nomReg;


}
