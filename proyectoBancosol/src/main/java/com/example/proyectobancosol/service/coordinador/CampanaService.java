package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.entity.Campana;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampanaService {
    private final CampanaRepository campanaRepository;

    public Campana findById(Integer campanaId) { return this.campanaRepository.findById(campanaId).get(); }

    public List<Campana> findByCadena(Integer cadenaId) { return this.campanaRepository.findByCadena(cadenaId); }
}
