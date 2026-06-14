package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.mapper.admin.CadenaAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Clase que representa la clase AsignacionTurnoService.
 *
 * Autores:
 * - IA Generatiova: 50%
 * - David Vilaseca Pareja: 25%
 *
 */

@Service
@AllArgsConstructor
public class CadenaService {
    private final CadenaRepository cadenaRepository;
    private final CadenaAdminMapper cadenaAdminMapper;

    public List<CadenaResponseDTO> findAll() { return cadenaAdminMapper.toDTOList(this.cadenaRepository.findAll()); }
}
