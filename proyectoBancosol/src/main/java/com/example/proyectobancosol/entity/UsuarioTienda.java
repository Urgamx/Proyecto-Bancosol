package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_tienda")
public class UsuarioTienda {
    @EmbeddedId
    private UsuarioTiendaId id;

    @MapsId("usuarioidUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USUARIOid_usuario", nullable = false)
    private Usuario usuarioidUsuario;

    @MapsId("tiendaidTienda")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TIENDAid_tienda", nullable = false)
    private Tienda tiendaidTienda;

}