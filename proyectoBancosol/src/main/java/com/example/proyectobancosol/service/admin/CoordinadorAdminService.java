package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.dto.response.CoordinadorResponseDTO;
import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoordinadorAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public CoordinadorAdminService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    public List<CoordinadorResponseDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CoordinadorRequestDTO buscarFormulario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        return new CoordinadorRequestDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                "",
                usuario.getActivo()
        );
    }

    @Transactional
    public String guardar(CoordinadorRequestDTO coordinadorRequestDTO) {
        String error = validar(coordinadorRequestDTO);

        if (error != null) {
            return error;
        }

        Rol rolCoordinador = rolRepository.findByNombre(ROL_COORDINADOR).orElseThrow();

        Usuario usuario;

        if (coordinadorRequestDTO.getId() == null) {
            usuario = new Usuario();
            usuario.setId(usuarioRepository.findMaxId() + 1);
            usuario.setPassword(coordinadorRequestDTO.getPassword().trim());
        } else {
            usuario = usuarioRepository.findById(coordinadorRequestDTO.getId()).orElseThrow();

            if (coordinadorRequestDTO.getPassword() != null && !coordinadorRequestDTO.getPassword().trim().isEmpty()) {
                usuario.setPassword(coordinadorRequestDTO.getPassword().trim());
            }
        }

        usuario.setIdRol(rolCoordinador);
        usuario.setNombreCompleto(coordinadorRequestDTO.getNombreCompleto().trim());
        usuario.setEmail(coordinadorRequestDTO.getEmail().trim());
        usuario.setActivo(coordinadorRequestDTO.getActivo() == null ? 1 : coordinadorRequestDTO.getActivo());

        usuarioRepository.save(usuario);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return "El coordinador no existe";
        }

        Long tiendas = usuarioRepository.countTiendasByUsuario(id);
        Long colaboradores = usuarioRepository.countColaboradoresByUsuario(id);

        if (tiendas > 0 || colaboradores > 0) {
            return "No se puede eliminar un coordinador con tiendas o colaboradores asignados";
        }

        usuarioRepository.deleteById(id);
        return null;
    }

    private String validar(CoordinadorRequestDTO coordinadorRequestDTO) {
        if (coordinadorRequestDTO == null) {
            return "Los datos del coordinador son obligatorios";
        }

        if (coordinadorRequestDTO.getNombreCompleto() == null || coordinadorRequestDTO.getNombreCompleto().trim().isEmpty()) {
            return "El nombre es obligatorio";
        }

        if (coordinadorRequestDTO.getEmail() == null || coordinadorRequestDTO.getEmail().trim().isEmpty()) {
            return "El email es obligatorio";
        }

        if (!coordinadorRequestDTO.getEmail().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "El email no tiene un formato valido";
        }

        if (coordinadorRequestDTO.getId() == null
                && (coordinadorRequestDTO.getPassword() == null || coordinadorRequestDTO.getPassword().trim().isEmpty())) {
            return "La contrasena es obligatoria al crear";
        }

        if (coordinadorRequestDTO.getNombreCompleto().trim().length() > 150) {
            return "El nombre no puede superar 150 caracteres";
        }

        if (coordinadorRequestDTO.getEmail().trim().length() > 150) {
            return "El email no puede superar 150 caracteres";
        }

        if (usuarioRepository.existsEmailDuplicado(coordinadorRequestDTO.getEmail().trim(), coordinadorRequestDTO.getId())) {
            return "Ya existe un usuario con ese email";
        }

        return null;
    }

    private CoordinadorResponseDTO convertirAResponseDTO(Usuario usuario) {
        Long tiendas = usuarioRepository.countTiendasByUsuario(usuario.getId());
        Long colaboradores = usuarioRepository.countColaboradoresByUsuario(usuario.getId());

        return new CoordinadorResponseDTO(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getActivo() != null && usuario.getActivo() == 1 ? "Activo" : "Inactivo",
                tiendas,
                colaboradores
        );
    }
}