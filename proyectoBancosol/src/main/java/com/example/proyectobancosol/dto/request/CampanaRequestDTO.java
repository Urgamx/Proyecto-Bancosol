package com.example.proyectobancosol.dto.request;

import java.util.ArrayList;
import java.util.List;

public class CampanaRequestDTO {

    private Integer id;
    private Integer idTipoCampana;
    private Integer fecha;
    private String fechaFormulario;
    private Integer activo;
    private List<Integer> idsCadenas = new ArrayList<>();

    public CampanaRequestDTO() {
    }

    public CampanaRequestDTO(Integer id, Integer idTipoCampana, Integer fecha, String fechaFormulario, Integer activo, List<Integer> idsCadenas) {
        this.id = id;
        this.idTipoCampana = idTipoCampana;
        this.fecha = fecha;
        this.fechaFormulario = fechaFormulario;
        this.activo = activo;
        this.idsCadenas = idsCadenas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoCampana() {
        return idTipoCampana;
    }

    public void setIdTipoCampana(Integer idTipoCampana) {
        this.idTipoCampana = idTipoCampana;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormulario() {
        return fechaFormulario;
    }

    public void setFechaFormulario(String fechaFormulario) {
        this.fechaFormulario = fechaFormulario;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<Integer> getIdsCadenas() {
        return idsCadenas;
    }

    public void setIdsCadenas(List<Integer> idsCadenas) {
        this.idsCadenas = idsCadenas;
    }
}