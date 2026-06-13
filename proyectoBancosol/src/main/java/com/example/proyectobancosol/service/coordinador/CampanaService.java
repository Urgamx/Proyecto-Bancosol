package com.example.proyectobancosol.service.coordinador;

import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.dto.response.CampanaResponseDTO;
import com.example.proyectobancosol.mapper.admin.CampanaAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampanaService {
    private final CampanaRepository campanaRepository;
    private final CampanaAdminMapper campanaAdminMapper;

    public CampanaResponseDTO findById(Integer campanaId) {
        return campanaAdminMapper.toDTO(this.campanaRepository.findById(campanaId).get());
    }

    public List<CampanaResponseDTO> findByCadena(Integer cadenaId) {
        return campanaAdminMapper.toDTOList(this.campanaRepository.findByCadena(cadenaId));
    }
}
