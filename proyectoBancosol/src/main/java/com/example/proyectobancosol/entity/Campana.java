package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "campana")
public class Campana {
    @Id
    @Column(name = "id_campana", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_campana", nullable = false)
    private TipoDeCampana tipoDeCampana;

    @Column(name = "fecha", nullable = false)
    private Integer fecha;

    @Column(name = "activo", nullable = false)
    private Integer activo;

}