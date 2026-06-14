package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.dto.response.CoordinadorResponseDTO;
import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

/**
 * Transforma coordinadores entre entidad, formulario y respuesta.
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * - IA: 25%
 */

@Component
public class CoordinadorAdminMapper extends MapperDTO<CoordinadorResponseDTO, Usuario> {

    @Override
    public CoordinadorResponseDTO toDTO(Usuario usuario) {
        return toDTO(usuario, 0L, 0L);
    }

    public CoordinadorResponseDTO toDTO(Usuario usuario, Long tiendas, Long colaboradores) {
        return new CoordinadorResponseDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getActivo() != null && usuario.getActivo() == 1 ? "Activo" : "Inactivo",
                tiendas,
                colaboradores
        );
    }

    public CoordinadorRequestDTO toRequestDTO(Usuario usuario) {
        return new CoordinadorRequestDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                "",
                usuario.getActivo()
        );
    }

    public void aplicarRequest(CoordinadorRequestDTO request, Usuario usuario, Rol rolCoordinador) {
        usuario.setIdRol(rolCoordinador);
        usuario.setNombreCompleto(request.getNombreCompleto().trim());
        usuario.setEmail(request.getEmail().trim());
        usuario.setActivo(request.getActivo() == null ? 1 : request.getActivo());
    }
}