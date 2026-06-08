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

    @MapsId("idCampana")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_campana", nullable = false)
    private Campana campana;

    @MapsId("idCadena")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cadena", nullable = false)
    private Cadena cadena;

}