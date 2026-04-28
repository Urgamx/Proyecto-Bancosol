package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "ASIGNACION_TURNOS")
public class AsignacionTurnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;

    @Column(name = "id_campana")
    private Integer idCampana;

    @Column(name = "id_tienda")
    private Integer idTienda;

    @Column(name = "id_voluntario")
    private Integer idVoluntario;

    @Column(name = "dia")
    private String dia;

    @Column(name = "franja")
    private String franja;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;
}