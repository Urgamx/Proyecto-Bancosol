package com.example.proyectobancosol.dto.response;

public class AdminMenuItemDTO {
    private String titulo;
    private String descripcion;
    private String url;
    private String estado;

    public AdminMenuItemDTO() {
    }

    public AdminMenuItemDTO(String titulo, String descripcion, String url, String estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
