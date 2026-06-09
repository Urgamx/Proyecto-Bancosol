package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "asignacion_turnos")
public class AsignacionTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda idTienda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_voluntario", nullable = false)
    private Voluntario idVoluntario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador idColaborador;

    @Column(name = "dia", nullable = false, length = 20)
    private String dia;

    @Column(name = "franja", nullable = false, length = 10)
    private String franja;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @OneToOne(mappedBy = "asignacion", fetch = FetchType.LAZY)
    private Incidencia incidencia;
}