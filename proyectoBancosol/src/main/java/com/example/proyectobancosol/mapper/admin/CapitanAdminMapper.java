package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CapitanRequestDTO;
import com.example.proyectobancosol.dto.response.CapitanResponseDTO;
import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Componente Mapper para convertir entre Usuario y DTOs de Capitán en el panel Admin.
 *
 * Autores:
 * - Carlos Sánchez: 100%
 */

@Component
public class CapitanAdminMapper extends MapperDTO<CapitanResponseDTO, Usuario> {

    @Override
    public CapitanResponseDTO toDTO(Usuario usuario) {
        return toDTO(usuario, new ArrayList<>(), new ArrayList<>());
    }

    public CapitanResponseDTO toDTO(Usuario usuario, List<String> campanas, List<String> tiendas) {
        return new CapitanResponseDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getActivo(),
                campanas,
                tiendas
        );
    }

    public CapitanRequestDTO toRequestDTO(Usuario usuario, List<Integer> idCampanas, List<Integer> idTiendas) {
        return new CapitanRequestDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                "",
                usuario.getActivo(),
                idCampanas,
                idTiendas
        );
    }

    public void aplicarRequest(CapitanRequestDTO request, Usuario usuario, Rol rolCapitan) {
        usuario.setIdRol(rolCapitan);
        usuario.setNombreCompleto(request.getNombreCompleto().trim());
        usuario.setEmail(request.getEmail().trim());
        usuario.setActivo(request.getActivo() == null ? 1 : request.getActivo());
    }
}
