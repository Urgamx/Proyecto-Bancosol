package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.entity.Voluntario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;

    public Voluntario findById(Integer id) { return this.voluntarioRepository.findById(id).get(); }

    public List<Voluntario> findAllByColaborador(Integer id) { return this.voluntarioRepository.findAllByColaborador(id); }
}
