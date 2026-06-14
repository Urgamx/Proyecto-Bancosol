package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.response.UsuarioDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.coordinador.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que representa la clase AsignacionTurnoService.
 *
 * Autores:
 * - IA Generatiova: 50%
 * - David Vilaseca Pareja: 25%
 *
 */

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository rolRepository;

    public List<UsuarioDTO> findAll() {
        return usuarioMapper.toDTOList(this.usuarioRepository.findAll());
    }

    public UsuarioDTO findById(Integer id) {
        return usuarioMapper.toDTO(this.usuarioRepository.findById(id).get());
    }

    public List<UsuarioDTO> findCoordinador() {
        return usuarioMapper.toDTOList(this.usuarioRepository.findCoordinador());
    }

    public List<UsuarioDTO> findCapitan() {
        return usuarioMapper.toDTOList(this.usuarioRepository.findCapitan());
    }

    public List<UsuarioDTO> findByNombreRol(Integer rolId, String nombre) {
        return usuarioMapper.toDTOList(this.usuarioRepository.findByNombreRol(rolId,nombre));
    }

    public List<UsuarioDTO> findByNombre(String nombre) {
        return usuarioMapper.toDTOList(this.usuarioRepository.findByNombre(nombre));
    }

    public void save(UsuarioDTO dto) {
        Usuario usuario;

        if (dto.getId() != null) {
            usuario = this.usuarioRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getId()));
        } else {
            usuario = new Usuario();
        }

        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.getActivo());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setPassword(dto.getPassword());

        usuario.setIdRol(this.rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + dto.getIdRol())));

        this.usuarioRepository.save(usuario);
    }

    public void delete(UsuarioDTO dto) {
        this.usuarioRepository.delete(this.usuarioRepository.findById(dto.getId()).get());
    }

}
