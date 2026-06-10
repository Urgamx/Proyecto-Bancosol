package com.example.proyectobancosol.dto.request;

import java.util.ArrayList;
import java.util.List;

public class AsignacionTiendasRequestDTO {

    private Integer idCoordinador;
    private List<Integer> idsTiendas = new ArrayList<>();

    public AsignacionTiendasRequestDTO() {
    }

    public AsignacionTiendasRequestDTO(Integer idCoordinador, List<Integer> idsTiendas) {
        this.idCoordinador = idCoordinador;
        this.idsTiendas = idsTiendas;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public List<Integer> getIdsTiendas() {
        return idsTiendas;
    }

    public void setIdsTiendas(List<Integer> idsTiendas) {
        this.idsTiendas = idsTiendas;
    }
}