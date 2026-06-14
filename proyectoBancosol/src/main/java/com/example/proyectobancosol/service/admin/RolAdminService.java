package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dto.response.RolResponseDTO;
import com.example.proyectobancosol.mapper.admin.RolAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que representa la clase RolAdminService.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 */

@Service
@AllArgsConstructor
public class RolAdminService {

    private final RolRepository rolRepository;
    private final RolAdminMapper rolAdminMapper;

    public List<RolResponseDTO> findAll() {
        return rolAdminMapper.toDTOList(this.rolRepository.findAll());
    }

    public RolResponseDTO findById(Integer id) {
        return rolAdminMapper.toDTO(this.rolRepository.findById(id).get());
    }
}
