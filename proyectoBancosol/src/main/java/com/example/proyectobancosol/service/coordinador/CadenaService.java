package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.entity.Cadena;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadenaService {
    private final CadenaRepository cadenaRepository;

    public List<Cadena> findAll() { return this.cadenaRepository.findAll(); }
}
