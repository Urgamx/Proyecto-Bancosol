package com.example.proyectobancosol.service.coordinador;


import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dto.response.UsuarioTiendaDTO;
import com.example.proyectobancosol.entity.UsuarioTienda;
import com.example.proyectobancosol.mapper.coordinador.UsuarioTiendaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioTiendaService {

    private final UsuarioTiendaRepository usuarioTiendaRepository;
    private final UsuarioTiendaMapper usuarioTiendaMapper;
    private final TiendaRepository tiendaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioTiendaDTO> findAll() {
        return usuarioTiendaMapper.toDTOList(this.usuarioTiendaRepository.findAll());
    }

    public Optional<UsuarioTiendaDTO> findByUsuarioId(Integer id) {
        return this.usuarioTiendaRepository.findByUsuarioId(id)
                .map(usuarioTiendaMapper::toDTO);
    }

    public void save(UsuarioTiendaDTO dto) {
        UsuarioTienda ua = this.usuarioTiendaRepository.findById(dto.getId()).orElse(new UsuarioTienda());
        ua.setTienda(this.tiendaRepository.findById(dto.getTiendaId()).get());
        ua.setUsuario(this.usuarioRepository.findById(dto.getUsuarioId()).get());
        this.usuarioTiendaRepository.save(ua);
    }

    @Transactional
    public void deleteByUsuarioId(Integer usuarioId) {
        this.usuarioTiendaRepository.deleteByUsuarioId(usuarioId);
    }
}
