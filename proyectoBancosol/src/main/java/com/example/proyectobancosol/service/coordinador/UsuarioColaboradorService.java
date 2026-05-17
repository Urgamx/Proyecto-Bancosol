package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioColaboradorService {

    @Autowired
    UsuarioColaboradorRepository usuarioColaboradorRepository;

    public List<UsuarioColaborador> findAll() {
        return this.usuarioColaboradorRepository.findAll();
    }
}
