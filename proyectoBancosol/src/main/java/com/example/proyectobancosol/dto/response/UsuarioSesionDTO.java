package com.example.proyectobancosol.dto.response;

public class UsuarioSesionDTO {    private Integer idUsuario;
    private String email;
    private String nombreCompleto;
    private String rol;

    public UsuarioSesionDTO() {
    }

    public UsuarioSesionDTO(Integer idUsuario, String email, String nombreCompleto, String rol) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
