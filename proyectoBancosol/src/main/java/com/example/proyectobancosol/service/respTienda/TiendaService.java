package com.example.proyectobancosol.service.respTienda;

import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.entity.Tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TiendaService {
    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> obtenerTodasLasTiendas() {
        return tiendaRepository.findAll();
    }
}