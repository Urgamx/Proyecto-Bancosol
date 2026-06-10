package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadenaAdminService {

    private final CadenaRepository cadenaRepository;

    public CadenaAdminService(CadenaRepository cadenaRepository) {
        this.cadenaRepository = cadenaRepository;
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listar() {
        return cadenaRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CadenaRequestDTO buscarFormulario(Integer id) {
        Cadena cadena = cadenaRepository.findById(id).orElseThrow();
        return convertirARequestDTO(cadena);
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

        cadena.setNombre(cadenaRequestDTO.getNombre().trim());
        cadena.setPersonaContacto(limpiar(cadenaRequestDTO.getPersonaContacto()));
        cadena.setTelefonoContacto(limpiar(cadenaRequestDTO.getTelefonoContacto()));

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

        if (cadenaRequestDTO.getId() == null && cadenaRepository.existsByNombreIgnoreCase(cadenaRequestDTO.getNombre().trim())) {
            return "Ya existe una cadena con ese nombre";
        }

        return null;
    }

    private String limpiar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        return valor.trim();
    }

    private CadenaResponseDTO convertirAResponseDTO(Cadena cadena) {
        return new CadenaResponseDTO(
                cadena.getId(),
                cadena.getNombre(),
                cadena.getPersonaContacto(),
                cadena.getTelefonoContacto()
        );
    }

    private CadenaRequestDTO convertirARequestDTO(Cadena cadena) {
        return new CadenaRequestDTO(
                cadena.getId(),
                cadena.getNombre(),
                cadena.getPersonaContacto(),
                cadena.getTelefonoContacto()
        );
    }
}