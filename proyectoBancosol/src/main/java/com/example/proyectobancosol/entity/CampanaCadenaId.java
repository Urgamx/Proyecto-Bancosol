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
public class CampanaCadenaId implements Serializable {
    private static final long serialVersionUID = -3074513619780911054L;
    @Column(name = "id_campana", nullable = false)
    private Integer idCampana;

    @Column(name = "id_cadena", nullable = false)
    private Integer idCadena;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CampanaCadenaId entity = (CampanaCadenaId) o;
        return Objects.equals(this.idCampana, entity.idCampana) &&
                Objects.equals(this.idCadena, entity.idCadena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampana, idCadena);
    }

}