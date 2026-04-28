package com.example.proyectobancosol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UsuarioTiendaId implements Serializable {
    private static final long serialVersionUID = -6965737462523685009L;
    @Column(name = "USUARIOid_usuario", nullable = false)
    private Integer usuarioidUsuario;

    @Column(name = "TIENDAid_tienda", nullable = false)
    private Integer tiendaidTienda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioTiendaId entity = (UsuarioTiendaId) o;
        return Objects.equals(this.usuarioidUsuario, entity.usuarioidUsuario) &&
                Objects.equals(this.tiendaidTienda, entity.tiendaidTienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioidUsuario, tiendaidTienda);
    }

}