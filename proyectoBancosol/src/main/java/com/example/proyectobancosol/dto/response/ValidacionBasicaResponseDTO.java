package com.example.proyectobancosol.dto.response;

public class ValidacionBasicaResponseDTO {

    private String modulo;
    private String validacion;
    private Long total;
    private String estado;
    private String detalle;

    public ValidacionBasicaResponseDTO() {
    }

    public ValidacionBasicaResponseDTO(String modulo, String validacion, Long total, String estado, String detalle) {
        this.modulo = modulo;
        this.validacion = validacion;
        this.total = total;
        this.estado = estado;
        this.detalle = detalle;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}