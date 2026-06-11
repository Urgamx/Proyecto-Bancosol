package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.mapper.admin.CadenaAdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadenaAdminService {

    private final CadenaRepository cadenaRepository;
    private final CadenaAdminMapper cadenaAdminMapper;

    public CadenaAdminService(CadenaRepository cadenaRepository,
                              CadenaAdminMapper cadenaAdminMapper) {
        this.cadenaRepository = cadenaRepository;
        this.cadenaAdminMapper = cadenaAdminMapper;
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listar() {
        return cadenaAdminMapper.toDTOList(cadenaRepository.findAllByOrderByNombreAsc());
    }

    @Transactional(readOnly = true)
    public CadenaRequestDTO buscarFormulario(Integer id) {
        return cadenaAdminMapper.toRequestDTO(cadenaRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(CadenaRequestDTO cadenaRequestDTO) {
        String error = validar(cadenaRequestDTO);

        if (error != null) {
            return error;
        }

        Cadena cadena;

        if (cadenaRequestDTO.getId() == null) {
            cadena = new Cadena();
            cadena.setId(cadenaRepository.findMaxId() + 1);
        } else {
            cadena = cadenaRepository.findById(cadenaRequestDTO.getId()).orElseThrow();
        }

        cadenaAdminMapper.aplicarRequest(cadenaRequestDTO, cadena);
        cadenaRepository.save(cadena);

        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!cadenaRepository.existsById(id)) {
            return "La cadena no existe";
        }

        Long tiendas = cadenaRepository.countTiendasByCadena(id);
        Long campanas = cadenaRepository.countCampanasByCadena(id);

        if (tiendas > 0 || campanas > 0) {
            return "No se puede eliminar una cadena con tiendas o campanas asociadas";
        }

        cadenaRepository.deleteById(id);
        return null;
    }

    private String validar(CadenaRequestDTO cadenaRequestDTO) {
        if (cadenaRequestDTO == null || cadenaRequestDTO.getNombre() == null || cadenaRequestDTO.getNombre().trim().isEmpty()) {
            return "El nombre es obligatorio";
        }

        if (cadenaRequestDTO.getNombre().trim().length() > 150) {
            return "El nombre no puede superar 150 caracteres";
        }

        if (cadenaRepository.existsNombreDuplicado(cadenaRequestDTO.getNombre().trim(), cadenaRequestDTO.getId())) {
            return "Ya existe una cadena con ese nombre";
        }

        return null;
    }
}