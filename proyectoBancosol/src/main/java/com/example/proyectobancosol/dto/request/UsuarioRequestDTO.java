/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.dto.request;

import lombok.Data;
import com.example.proyectobancosol.entity.Usuario;

@Data
public class UsuarioRequestDTO {
    private Integer id;
    private String nombre;
    private String email;

    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(Usuario u) {
        this.id = u.getId();
        this.nombre = u.getNombreCompleto();
        this.email = u.getEmail();
    }
}