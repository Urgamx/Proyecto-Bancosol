package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Tienda;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TiendaAdminService {

    private final TiendaRepository tiendaRepository;
    private final CadenaRepository cadenaRepository;

    public TiendaAdminService(TiendaRepository tiendaRepository, CadenaRepository cadenaRepository) {
        this.tiendaRepository = tiendaRepository;
        this.cadenaRepository = cadenaRepository;
    }

    @Transactional(readOnly = true)
    public List<TiendaResponseDTO> listar() {
        return tiendaRepository.findAllConCadena()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listarCadenas() {
        return cadenaRepository.findAllByOrderByNombreAsc()
                .stream()
                .map(cadena -> new CadenaResponseDTO(
                        cadena.getId(),
                        cadena.getNombre(),
                        cadena.getPersonaContacto(),
                        cadena.getTelefonoContacto()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public TiendaRequestDTO buscarFormulario(Integer id) {
        Tienda tienda = tiendaRepository.findById(id).orElseThrow();

        return new TiendaRequestDTO(
                tienda.getId(),
                tienda.getIdCadena().getId(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getCodPostal()
        );
    }

    @Transactional
    public String guardar(TiendaRequestDTO tiendaRequestDTO) {
        String error = validar(tiendaRequestDTO);

        if (error != null) {
            return error;
        }

        Cadena cadena = cadenaRepository.findById(tiendaRequestDTO.getIdCadena()).orElseThrow();

        Tienda tienda;

        if (tiendaRequestDTO.getId() == null) {
            tienda = new Tienda();
            tienda.setId(tiendaRepository.findMaxId() + 1);
        } else {
            tienda = tiendaRepository.findById(tiendaRequestDTO.getId()).orElseThrow();
        }

        tienda.setIdCadena(cadena);
        tienda.setNombre(tiendaRequestDTO.getNombre().trim());
        tienda.setDireccion(tiendaRequestDTO.getDireccion().trim());
        tienda.setCodPostal(tiendaRequestDTO.getCodPostal().trim());

        tiendaRepository.save(tienda);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!tiendaRepository.existsById(id)) {
            return "La tienda no existe";
        }

        Long campanas = tiendaRepository.countCampanasByTienda(id);
        Long usuarios = tiendaRepository.countUsuariosByTienda(id);
        Long turnos = tiendaRepository.countTurnosByTienda(id);

        if (campanas > 0 || usuarios > 0 || turnos > 0) {
            return "No se puede eliminar una tienda con campanas, usuarios o turnos asociados";
        }

        tiendaRepository.deleteById(id);
        return null;
    }

    private String validar(TiendaRequestDTO tiendaRequestDTO) {
        if (tiendaRequestDTO == null) {
            return "Los datos de la tienda son obligatorios";
        }

        if (tiendaRequestDTO.getIdCadena() == null) {
            return "La cadena es obligatoria";
        }

        if (!cadenaRepository.existsById(tiendaRequestDTO.getIdCadena())) {
            return "La cadena no existe";
        }

        if (tiendaRequestDTO.getNombre() == null || tiendaRequestDTO.getNombre().trim().isEmpty()) {
            return "El nombre es obligatorio";
        }

        if (tiendaRequestDTO.getDireccion() == null || tiendaRequestDTO.getDireccion().trim().isEmpty()) {
            return "La direccion es obligatoria";
        }

        if (tiendaRequestDTO.getCodPostal() == null || tiendaRequestDTO.getCodPostal().trim().isEmpty()) {
            return "El codigo postal es obligatorio";
        }

        if (!tiendaRequestDTO.getCodPostal().trim().matches("\\d{5}")) {
            return "El codigo postal debe tener 5 numeros";
        }

        if (tiendaRequestDTO.getNombre().trim().length() > 150) {
            return "El nombre no puede superar 150 caracteres";
        }

        if (tiendaRequestDTO.getDireccion().trim().length() > 255) {
            return "La direccion no puede superar 255 caracteres";
        }

        if (tiendaRepository.existsDuplicada(
                tiendaRequestDTO.getIdCadena(),
                tiendaRequestDTO.getNombre().trim(),
                tiendaRequestDTO.getDireccion().trim(),
                tiendaRequestDTO.getId())) {
            return "Ya existe una tienda con esa cadena, nombre y direccion";
        }

        return null;
    }

    private TiendaResponseDTO convertirAResponseDTO(Tienda tienda) {
        return new TiendaResponseDTO(
                tienda.getId(),
                tienda.getIdCadena().getNombre(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getCodPostal()
        );
    }
}