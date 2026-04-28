package com.example.proyectobancosol.service.respTienda;

import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.entity.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoluntarioService {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public List<Voluntario> obtenerTodosLosVoluntarios() {
        return voluntarioRepository.findAll();
    }
}