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

    @MapsId("tiendaidTienda")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TIENDAid_tienda", nullable = false)
    private Tienda tiendaidTienda;

    @MapsId("idCampana")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_campana", nullable = false)
    private Campana idCampana;

}