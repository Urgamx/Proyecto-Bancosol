package com.example.proyectobancosol.dto.response;

import lombok.Data;

@Data
public class VoluntarioResponseDTO {
    private Integer id;
    private String nombre;
    private String telefono;
    private String email;
    
    private boolean perteneceAEntidad;
    private String nombreEntidad;
    private String emailEntidad;
    private String telefonoEntidad;
    private String domicilioEntidad;
}