package com.example.proyectobancosol.service.coordinador;


import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.entity.UsuarioTienda;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioTiendaService {

    private final UsuarioTiendaRepository usuarioTiendaRepository;

    public List<UsuarioTienda> findAll() { return this.usuarioTiendaRepository.findAll();}

    public Optional<UsuarioTienda> findByUsuarioId(Integer id) { return this.usuarioTiendaRepository.findByUsuarioId(id); }

    public void save(UsuarioTienda ua) { this.usuarioTiendaRepository.save(ua); }

    @Transactional
    public void deleteByUsuarioId(Integer usuarioId) {
        this.usuarioTiendaRepository.deleteByUsuarioId(usuarioId);
    }
}
