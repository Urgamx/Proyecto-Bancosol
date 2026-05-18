package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioColaboradorService {

    private final UsuarioColaboradorRepository usuarioColaboradorRepository;

    public List<UsuarioColaborador> findAll() {
        return this.usuarioColaboradorRepository.findAll();
    }
}
