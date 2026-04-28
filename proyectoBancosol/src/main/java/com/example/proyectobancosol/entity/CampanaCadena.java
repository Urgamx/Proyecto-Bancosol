package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "campana_cadenas")
public class CampanaCadena {
    @EmbeddedId
    private CampanaCadenaId id;

    @MapsId("campañaidCampana")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`CAMPAÑAid_campana`", nullable = false)
    private Campana campañaidCampana;

    @MapsId("cadenasidCadena")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CADENASid_cadena", nullable = false)
    private Cadena cadenasidCadena;

}