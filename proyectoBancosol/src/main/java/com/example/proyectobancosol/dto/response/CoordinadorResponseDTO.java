package com.example.proyectobancosol.dto.response;

public class CoordinadorResponseDTO {

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String activo;
    private Long tiendasAsignadas;
    private Long colaboradoresAsignados;

    public CoordinadorResponseDTO() {
    }

    public CoordinadorResponseDTO(Integer id, String nombreCompleto, String email, String activo, Long tiendasAsignadas, Long colaboradoresAsignados) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.activo = activo;
        this.tiendasAsignadas = tiendasAsignadas;
        this.colaboradoresAsignados = colaboradoresAsignados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getTiendasAsignadas() {
        return tiendasAsignadas;
    }

    public void setTiendasAsignadas(Long tiendasAsignadas) {
        this.tiendasAsignadas = tiendasAsignadas;
    }

    public Long getColaboradoresAsignados() {
        return colaboradoresAsignados;
    }

    public void setColaboradoresAsignados(Long colaboradoresAsignados) {
        this.colaboradoresAsignados = colaboradoresAsignados;
    }
}