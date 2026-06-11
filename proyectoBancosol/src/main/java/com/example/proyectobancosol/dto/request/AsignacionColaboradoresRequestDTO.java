package com.example.proyectobancosol.dto.request;

import java.util.ArrayList;
import java.util.List;

public class AsignacionColaboradoresRequestDTO {

    private Integer idCoordinador;
    private List<Integer> idsColaboradores = new ArrayList<>();

    public AsignacionColaboradoresRequestDTO() {
    }

    public AsignacionColaboradoresRequestDTO(Integer idCoordinador, List<Integer> idsColaboradores) {
        this.idCoordinador = idCoordinador;
        this.idsColaboradores = idsColaboradores;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public List<Integer> getIdsColaboradores() {
        return idsColaboradores;
    }

    public void setIdsColaboradores(List<Integer> idsColaboradores) {
        this.idsColaboradores = idsColaboradores;
    }
}