package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.mapper.admin.CadenaAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadenaAdminService {

    private final CadenaRepository cadenaRepository;
    private final CadenaAdminMapper cadenaAdminMapper;

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listar() {
        return cadenaAdminMapper.toDTOList(cadenaRepository.findAllByOrderByNombreAsc());
    }

    @Transactional(readOnly = true)
    public CadenaRequestDTO buscarFormulario(Integer id) {
        return cadenaAdminMapper.toRequestDTO(cadenaRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(CadenaRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Cadena cadena = request.getId() == null ? new Cadena() : cadenaRepository.findById(request.getId()).orElseThrow();        cadenaAdminMapper.aplicarRequest(request, cadena);
        cadenaRepository.save(cadena);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!cadenaRepository.existsById(id)) {
            return "La cadena no existe";
        }

        if (cadenaRepository.countTiendasByCadena(id) > 0 || cadenaRepository.countCampanasByCadena(id) > 0) {
            return "No se puede eliminar una cadena con tiendas o campanas asociadas";
        }

        cadenaRepository.deleteById(id);
        return null;
    }

    private String validar(CadenaRequestDTO request) {
        if (request == null || vacio(request.getNombre())) {
            return "El nombre es obligatorio";
        }

        if (request.getNombre().trim().length() > 150) {
            return "El nombre no puede tener mas de 150 caracteres";
        }

        return cadenaRepository.existsNombreDuplicado(request.getNombre().trim(), request.getId())
                ? "Cadena duplicada"
                : null;
    }


    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}