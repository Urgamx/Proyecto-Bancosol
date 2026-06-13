package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.dto.response.CoordinadorResponseDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.admin.CoordinadorAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinadorAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final CoordinadorAdminMapper coordinadorAdminMapper;

    @Transactional(readOnly = true)
    public List<CoordinadorResponseDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CoordinadorResponseDTO> filtrar(String nombre, Integer activo) {
        return usuarioRepository.findCoordinadoresFiltrados(nombre, activo).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CoordinadorRequestDTO buscarFormulario(Integer id) {
        return coordinadorAdminMapper.toRequestDTO(usuarioRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(CoordinadorRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Usuario usuario = request.getId() == null ? new Usuario() : usuarioRepository.findById(request.getId()).orElseThrow();
        actualizarPassword(usuario, request);
        coordinadorAdminMapper.aplicarRequest(request, usuario, rolRepository.findByNombre(ROL_COORDINADOR).orElseThrow());
        usuarioRepository.save(usuario);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return "El coordinador no existe";
        }

        if (usuarioRepository.countTiendasByUsuario(id) > 0 || usuarioRepository.countColaboradoresByUsuario(id) > 0) {
            return "No se puede eliminar un coordinador con tiendas o colaboradores asignados";
        }

        usuarioRepository.deleteById(id);
        return null;
    }

    private String validar(CoordinadorRequestDTO request) {
        if (request == null) {
            return "Los datos del coordinador son obligatorios";
        }

        if (vacio(request.getNombreCompleto())) {
            return "El nombre es obligatorio";
        }

        if (vacio(request.getEmail())) {
            return "El email es obligatorio";
        }

        if (!request.getEmail().trim().matches(EMAIL_REGEX)) {
            return "El email no tiene un formato valido";
        }

        if (request.getId() == null && vacio(request.getPassword())) {
            return "Debes introducir una contrasena";
        }

        if (largo(request.getNombreCompleto(), 150)) {
            return "El nombre no puede superar los 150 caracteres";
        }

        if (largo(request.getEmail(), 150)) {
            return "El email no puede superar los 150 caracteres";
        }

        return usuarioRepository.existsEmailDuplicado(request.getEmail().trim(), request.getId())
                ? "Ya existe un usuario con ese email"
                : null;
    }



    private void actualizarPassword(Usuario usuario, CoordinadorRequestDTO request) {
        if (!vacio(request.getPassword())) {
            usuario.setPassword(request.getPassword().trim());
        }
    }

    private CoordinadorResponseDTO toResponseDTO(Usuario usuario) {
        return coordinadorAdminMapper.toDTO(
                usuario,
                usuarioRepository.countTiendasByUsuario(usuario.getId()),
                usuarioRepository.countColaboradoresByUsuario(usuario.getId())
        );
    }

    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean largo(String valor, int maximo) {
        return valor != null && valor.trim().length() > maximo;
    }
}