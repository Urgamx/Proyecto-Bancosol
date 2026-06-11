package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.dto.response.CoordinadorResponseDTO;
import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class CoordinadorAdminMapper extends MapperDTO<CoordinadorResponseDTO, Usuario> {

    @Override
    public CoordinadorResponseDTO toDTO(Usuario usuario) {
        return new CoordinadorResponseDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getActivo() != null && usuario.getActivo() == 1 ? "Activo" : "Inactivo",
                0L,
                0L
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

    public void aplicarRequest(CoordinadorRequestDTO coordinadorRequestDTO, Usuario usuario, Rol rolCoordinador) {
        usuario.setIdRol(rolCoordinador);
        usuario.setNombreCompleto(coordinadorRequestDTO.getNombreCompleto().trim());
        usuario.setEmail(coordinadorRequestDTO.getEmail().trim());
        usuario.setActivo(coordinadorRequestDTO.getActivo() == null ? 1 : coordinadorRequestDTO.getActivo());
    }
}