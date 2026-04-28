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
public class TiendaCampanaId implements Serializable {
    private static final long serialVersionUID = 7266499023551522934L;
    @Column(name = "TIENDAid_tienda", nullable = false)
    private Integer tiendaidTienda;

    @Column(name = "id_campana", nullable = false)
    private Integer idCampana;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TiendaCampanaId entity = (TiendaCampanaId) o;
        return Objects.equals(this.idCampana, entity.idCampana) &&
                Objects.equals(this.tiendaidTienda, entity.tiendaidTienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampana, tiendaidTienda);
    }

}