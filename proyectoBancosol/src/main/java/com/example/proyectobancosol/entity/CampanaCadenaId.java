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
    @Column(name = "`CAMPAÑAid_campana`", nullable = false)
    private Integer campañaidCampana;

    @Column(name = "CADENASid_cadena", nullable = false)
    private Integer cadenasidCadena;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CampanaCadenaId entity = (CampanaCadenaId) o;
        return Objects.equals(this.campañaidCampana, entity.campañaidCampana) &&
                Objects.equals(this.cadenasidCadena, entity.cadenasidCadena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campañaidCampana, cadenasidCadena);
    }

}