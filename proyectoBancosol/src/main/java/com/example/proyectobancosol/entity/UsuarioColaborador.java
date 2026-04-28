package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_colaborador")
public class UsuarioColaborador {
    @EmbeddedId
    private UsuarioColaboradorId id;

    @MapsId("usuarioidUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USUARIOid_usuario", nullable = false)
    private Usuario usuarioidUsuario;

    @MapsId("idColaborador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador idColaborador;

}