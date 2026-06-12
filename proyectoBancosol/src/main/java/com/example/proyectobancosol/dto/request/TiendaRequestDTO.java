package com.example.proyectobancosol.dto.request;

public class TiendaRequestDTO {

    private Integer id;
    private Integer idCadena;
    private String nombre;
    private String direccion;
    private String codPostal;

    public TiendaRequestDTO() {
    }

    public TiendaRequestDTO(Integer id, Integer idCadena, String nombre, String direccion, String codPostal) {
        this.id = id;
        this.idCadena = idCadena;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codPostal = codPostal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Integer idCadena) {
        this.idCadena = idCadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
}