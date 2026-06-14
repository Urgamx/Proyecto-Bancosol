package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioColaboradorDTO;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import com.example.proyectobancosol.mapper.coordinador.UsuarioColaboradorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase que representa la clase AsignacionTurnoService.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 *
 */

@Service
@AllArgsConstructor
public class UsuarioColaboradorService {

    private final UsuarioColaboradorRepository usuarioColaboradorRepository;
    private final UsuarioColaboradorMapper usuarioColaboradorMapper;
    private final ColaboradorAdminMapper colaboradorAdminMapper;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioColaboradorDTO> findAll() {
        return usuarioColaboradorMapper.toDTOList(this.usuarioColaboradorRepository.findAll());
    }

    public List<ColaboradorResponseDTO> findByZonaLocalidad(String zonaGeo, String localidad) {
        return colaboradorAdminMapper
                .toDTOList(this.usuarioColaboradorRepository.findByZonaLocalidad(zonaGeo,localidad));
    }

    public List<ColaboradorResponseDTO> findByZonaLocalidadCoorId(String zonaGeo, String localidad, Integer coordinadorId) {
        return colaboradorAdminMapper
                .toDTOList(this.usuarioColaboradorRepository.findByZonaLocalidadCoorId(zonaGeo,localidad,coordinadorId));
    }

    public void save(UsuarioColaboradorDTO usuarioColaborador) {
        UsuarioColaborador uc = this.usuarioColaboradorRepository.findById(usuarioColaborador.getId()).orElse(new UsuarioColaborador());
        uc.setColaborador(this.colaboradorRepository.findById(usuarioColaborador.getColaboradorId()).get());
        uc.setUsuario(this.usuarioRepository.findById(usuarioColaborador.getUsuarioId()).get());
        this.usuarioColaboradorRepository.save(uc);
    }

    @Transactional
    public void deleteByUsuarioId(Integer usuarioId) {
        this.usuarioColaboradorRepository.deleteByUsuarioId(usuarioId);
    }
}
