/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.*;
import com.example.proyectobancosol.dto.request.UsuarioRequestDTO;
import com.example.proyectobancosol.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRespTiendaService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }

    @Transactional(readOnly = true)
    public UsuarioRequestDTO buscarFormulario(Integer id) {
        return usuarioRepository.findById(id)
                .map(UsuarioRequestDTO::new)
                .orElse(new UsuarioRequestDTO());
    }

    @Transactional
    public String guardar(UsuarioRequestDTO request, String nombreRol) {
        Usuario usuario;

        if (request.getId() != null) {
            usuario = usuarioRepository.findById(request.getId()).orElse(new Usuario());
        } else {
            usuario = new Usuario();
            usuario.setActivo(1);
        }

        usuario.setNombreCompleto(request.getNombre());
        usuario.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPassword(request.getPassword().trim());
        }

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
        usuario.setIdRol(rol);

        usuarioRepository.save(usuario);
        return null;
    }

    @Transactional
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}