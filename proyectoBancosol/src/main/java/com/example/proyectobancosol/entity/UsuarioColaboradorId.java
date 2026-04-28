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
public class UsuarioColaboradorId implements Serializable {
    private static final long serialVersionUID = -6411622268755404661L;
    @Column(name = "USUARIOid_usuario", nullable = false)
    private Integer usuarioidUsuario;

    @Column(name = "id_colaborador", nullable = false)
    private Integer idColaborador;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioColaboradorId entity = (UsuarioColaboradorId) o;
        return Objects.equals(this.usuarioidUsuario, entity.usuarioidUsuario) &&
                Objects.equals(this.idColaborador, entity.idColaborador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioidUsuario, idColaborador);
    }

}