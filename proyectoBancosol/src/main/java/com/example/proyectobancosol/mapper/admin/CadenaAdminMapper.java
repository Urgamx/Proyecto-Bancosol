package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

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

    public void aplicarRequest(CadenaRequestDTO cadenaRequestDTO, Cadena cadena) {
        cadena.setNombre(cadenaRequestDTO.getNombre().trim());
        cadena.setPersonaContacto(limpiar(cadenaRequestDTO.getPersonaContacto()));
        cadena.setTelefonoContacto(limpiar(cadenaRequestDTO.getTelefonoContacto()));
    }

    private String limpiar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        return valor.trim();
    }
}