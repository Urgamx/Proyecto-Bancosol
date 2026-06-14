package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

/**
 * Convierte los datos de cadenas entre entidad, formulario y respuesta.
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * - IA: 25%
 */

@Component
public class CadenaAdminMapper extends MapperDTO<CadenaResponseDTO, Cadena> {

    @Override
    public CadenaResponseDTO toDTO(Cadena cadena) {
        return new CadenaResponseDTO(
                cadena.getId(),
                cadena.getNombre(),
                cadena.getPersonaContacto(),
                cadena.getTelefonoContacto()
        );
    }

    public CadenaRequestDTO toRequestDTO(Cadena cadena) {
        return new CadenaRequestDTO(
                cadena.getId(),
                cadena.getNombre(),
                cadena.getPersonaContacto(),
                cadena.getTelefonoContacto()
        );
    }

    public void aplicarRequest(CadenaRequestDTO request, Cadena cadena) {
        cadena.setNombre(request.getNombre().trim());
        cadena.setPersonaContacto(limpiar(request.getPersonaContacto()));
        cadena.setTelefonoContacto(limpiar(request.getTelefonoContacto()));
    }

    private String limpiar(String valor) {
        return valor == null || valor.trim().isEmpty() ? null : valor.trim();
    }
}