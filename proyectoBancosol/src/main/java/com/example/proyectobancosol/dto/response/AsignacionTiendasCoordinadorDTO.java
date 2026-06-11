package com.example.proyectobancosol.dto.response;

public class AsignacionTiendasCoordinadorDTO {

    private Integer idCoordinador;
    private String nombreCompleto;
    private String email;
    private String tiendasAsignadas;

    public AsignacionTiendasCoordinadorDTO() {
    }

    public AsignacionTiendasCoordinadorDTO(Integer idCoordinador, String nombreCompleto, String email, String tiendasAsignadas) {
        this.idCoordinador = idCoordinador;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.tiendasAsignadas = tiendasAsignadas;
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

    public String getTiendasAsignadas() {
        return tiendasAsignadas;
    }

    public void setTiendasAsignadas(String tiendasAsignadas) {
        this.tiendasAsignadas = tiendasAsignadas;
    }
}