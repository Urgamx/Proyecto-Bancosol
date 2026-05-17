package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.entity.Colaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired ColaboradorRepository colaboradorRepository;

    public List<Colaborador> findAll() {
        return this.colaboradorRepository.findAll();
    }
}
