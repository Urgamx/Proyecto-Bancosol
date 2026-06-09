package com.example.proyectobancosol.service.coordinador;


import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.entity.UsuarioTienda;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioTiendaService {

    private final UsuarioTiendaRepository usuarioTiendaRepository;

    public List<UsuarioTienda> findAll() { return this.usuarioTiendaRepository.findAll();}

    public Optional<UsuarioTienda> findByUsuarioId(Integer id) { return this.usuarioTiendaRepository.findByUsuarioId(id); }
}
