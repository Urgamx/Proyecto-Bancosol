package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.dto.response.VoluntarioDTO;
import com.example.proyectobancosol.mapper.coordinador.VoluntarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final VoluntarioMapper voluntarioMapper;

    public VoluntarioDTO findById(Integer id) {
        return voluntarioMapper.toDTO(this.voluntarioRepository.findById(id).get());
    }

    public List<VoluntarioDTO> findAllByColaborador(Integer id) {
        return voluntarioMapper.toDTOList(this.voluntarioRepository.findAllByColaborador(id));
    }
}
