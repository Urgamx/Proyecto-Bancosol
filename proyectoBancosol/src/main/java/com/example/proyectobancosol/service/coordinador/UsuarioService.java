package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.dto.response.CoordinadorResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.admin.CoordinadorAdminMapper;
import com.example.proyectobancosol.mapper.coordinador.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Usuario usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.getActivo());
        usuario.setIdRol(this.rolRepository.findById(dto.getIdRol()).get());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setPassword(dto.getPassword());
        this.usuarioRepository.save(usuario);
    }

    public void delete(UsuarioDTO dto) {
        this.usuarioRepository.delete(this.usuarioRepository.findById(dto.getId()).get());
    }

}
