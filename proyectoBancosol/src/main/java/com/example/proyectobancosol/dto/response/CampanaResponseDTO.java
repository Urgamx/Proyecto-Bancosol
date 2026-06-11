package com.example.proyectobancosol.dto.response;

public class CampanaResponseDTO {

    private Integer id;
    private String tipoCampana;
    private Integer fecha;
    private String fechaTexto;
    private String activo;
    private String cadenasParticipantes;

    public CampanaResponseDTO() {
    }

    public CampanaResponseDTO(Integer id, String tipoCampana, Integer fecha, String fechaTexto, String activo, String cadenasParticipantes) {
        this.id = id;
        this.tipoCampana = tipoCampana;
        this.fecha = fecha;
        this.fechaTexto = fechaTexto;
        this.activo = activo;
        this.cadenasParticipantes = cadenasParticipantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoCampana() {
        return tipoCampana;
    }

    public void setTipoCampana(String tipoCampana) {
        this.tipoCampana = tipoCampana;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public String getFechaTexto() {
        return fechaTexto;
    }

    public void setFechaTexto(String fechaTexto) {
        this.fechaTexto = fechaTexto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCadenasParticipantes() {
        return cadenasParticipantes;
    }

    public void setCadenasParticipantes(String cadenasParticipantes) {
        this.cadenasParticipantes = cadenasParticipantes;
    }
}