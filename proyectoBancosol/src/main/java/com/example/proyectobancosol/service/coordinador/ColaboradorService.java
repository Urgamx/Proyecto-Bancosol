package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.entity.Colaborador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public List<Colaborador> findAll() {
        return this.colaboradorRepository.findAll();
    }

    public List<Colaborador> findAllActivos() {
        return this.colaboradorRepository.findAllActivos();
    }

    public Colaborador findById(Integer id) { return this.colaboradorRepository.findById(id).get();}

    public void save(Colaborador colaborador){ this.colaboradorRepository.save(colaborador);}
}
