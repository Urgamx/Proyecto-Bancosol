package com.example.proyectobancosol.dto.request;

public class ColaboradorRequestDTO {

    private Integer id;
    private String nombreEntidad;
    private String email;
    private String contactoNom;
    private String contactoTlf;
    private String domicilio;
    private String localidad;
    private String zonaGeografica;
    private String observaciones;
    private String codPostal;
    private Integer estado;

    public ColaboradorRequestDTO() {
    }

    public ColaboradorRequestDTO(Integer id, String nombreEntidad, String email, String contactoNom, String contactoTlf, String domicilio, String localidad, String zonaGeografica, String observaciones, String codPostal, Integer estado) {
        this.id = id;
        this.nombreEntidad = nombreEntidad;
        this.email = email;
        this.contactoNom = contactoNom;
        this.contactoTlf = contactoTlf;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.zonaGeografica = zonaGeografica;
        this.observaciones = observaciones;
        this.codPostal = codPostal;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactoNom() {
        return contactoNom;
    }

    public void setContactoNom(String contactoNom) {
        this.contactoNom = contactoNom;
    }

    public String getContactoTlf() {
        return contactoTlf;
    }

    public void setContactoTlf(String contactoTlf) {
        this.contactoTlf = contactoTlf;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}