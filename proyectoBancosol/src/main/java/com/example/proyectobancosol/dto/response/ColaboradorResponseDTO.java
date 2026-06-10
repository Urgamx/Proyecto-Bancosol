package com.example.proyectobancosol.dto.response;

public class ColaboradorResponseDTO {

    private Integer id;
    private String nombreEntidad;
    private String email;
    private String contactoNom;
    private String contactoTlf;
    private String domicilio;
    private String localidad;
    private String zonaGeografica;
    private String codPostal;
    private String estado;
    private String coordinadoresAsignados;
    private Long voluntarios;
    private Long turnos;

    public ColaboradorResponseDTO() {
    }

    public ColaboradorResponseDTO(Integer id, String nombreEntidad, String email, String contactoNom, String contactoTlf, String domicilio, String localidad, String zonaGeografica, String codPostal, String estado, String coordinadoresAsignados, Long voluntarios, Long turnos) {
        this.id = id;
        this.nombreEntidad = nombreEntidad;
        this.email = email;
        this.contactoNom = contactoNom;
        this.contactoTlf = contactoTlf;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.zonaGeografica = zonaGeografica;
        this.codPostal = codPostal;
        this.estado = estado;
        this.coordinadoresAsignados = coordinadoresAsignados;
        this.voluntarios = voluntarios;
        this.turnos = turnos;
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

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCoordinadoresAsignados() {
        return coordinadoresAsignados;
    }

    public void setCoordinadoresAsignados(String coordinadoresAsignados) {
        this.coordinadoresAsignados = coordinadoresAsignados;
    }

    public Long getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(Long voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Long getTurnos() {
        return turnos;
    }

    public void setTurnos(Long turnos) {
        this.turnos = turnos;
    }
}