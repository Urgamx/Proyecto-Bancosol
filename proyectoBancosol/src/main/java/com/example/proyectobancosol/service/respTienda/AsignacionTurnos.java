package com.example.proyectobancosol.service.respTienda;

import com.example.proyectobancosol.dao.AsignacionTurnosRepository;
import com.example.proyectobancosol.entity.AsignacionTurnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsignacionTurnosService {
    @Autowired
    private AsignacionTurnosRepository asignacionRepository;

    public List<AsignacionTurnos> obtenerTodasLasAsignaciones() {
        return asignacionRepository.findAll();
    }
}