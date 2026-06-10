package com.example.proyectobancosol.dto.request;

public class CoordinadorRequestDTO {

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String password;
    private Integer activo;

    public CoordinadorRequestDTO() {
    }

    public CoordinadorRequestDTO(Integer id, String nombreCompleto, String email, String password, Integer activo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.password = password;
        this.activo = activo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}