package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioColaboradorService {

    private final UsuarioColaboradorRepository usuarioColaboradorRepository;

    public List<UsuarioColaborador> findAll() {
        return this.usuarioColaboradorRepository.findAll();
    }

    public List<Colaborador> findByZonaLocalidad(String zonaGeo, String localidad) {
        return this.usuarioColaboradorRepository.findByZonaLocalidad(zonaGeo,localidad);
    }

    public List<Colaborador> findByZonaLocalidadCoorId(String zonaGeo, String localidad, Integer coordinadorId) {
        return this.usuarioColaboradorRepository.findByZonaLocalidadCoorId(zonaGeo,localidad,coordinadorId);
    }

    public void save(UsuarioColaborador usuarioColaborador) { this.usuarioColaboradorRepository.save(usuarioColaborador); }

    @Transactional
    public void deleteByUsuarioId(Integer usuarioId) {
        this.usuarioColaboradorRepository.deleteByUsuarioId(usuarioId);
    }
}
