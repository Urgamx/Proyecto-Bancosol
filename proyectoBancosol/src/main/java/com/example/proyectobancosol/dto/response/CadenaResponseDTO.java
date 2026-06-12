package com.example.proyectobancosol.dto.response;

public class CadenaResponseDTO {

    private Integer id;
    private String nombre;
    private String personaContacto;
    private String telefonoContacto;

    public CadenaResponseDTO() {
    }

    public CadenaResponseDTO(Integer id, String nombre, String personaContacto, String telefonoContacto) {
        this.id = id;
        this.nombre = nombre;
        this.personaContacto = personaContacto;
        this.telefonoContacto = telefonoContacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
