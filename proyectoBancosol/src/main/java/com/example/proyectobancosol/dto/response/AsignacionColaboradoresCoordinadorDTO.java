package com.example.proyectobancosol.dto.response;

public class AsignacionColaboradoresCoordinadorDTO {

    private Integer idCoordinador;
    private String nombreCompleto;
    private String email;
    private String colaboradoresAsignados;

    public AsignacionColaboradoresCoordinadorDTO() {
    }

    public AsignacionColaboradoresCoordinadorDTO(Integer idCoordinador, String nombreCompleto, String email, String colaboradoresAsignados) {
        this.idCoordinador = idCoordinador;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.colaboradoresAsignados = colaboradoresAsignados;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getColaboradoresAsignados() {
        return colaboradoresAsignados;
    }

    public void setColaboradoresAsignados(String colaboradoresAsignados) {
        this.colaboradoresAsignados = colaboradoresAsignados;
    }
}