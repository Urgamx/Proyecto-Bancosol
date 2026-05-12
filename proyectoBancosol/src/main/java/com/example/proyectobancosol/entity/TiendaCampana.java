package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tienda_campana")
public class TiendaCampana {
    @EmbeddedId
    private TiendaCampanaId id;

    @MapsId("idTienda")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda tienda;

    @MapsId("idCampana")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_campana", nullable = false)
    private Campana campana;

}