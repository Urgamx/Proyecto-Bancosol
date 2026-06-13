package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.mapper.admin.CadenaAdminMapper;
import com.example.proyectobancosol.mapper.admin.TiendaAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TiendaAdminService {

    private final TiendaRepository tiendaRepository;
    private final CadenaRepository cadenaRepository;
    private final TiendaAdminMapper tiendaAdminMapper;
    private final CadenaAdminMapper cadenaAdminMapper;

    @Transactional(readOnly = true)
    public List<TiendaResponseDTO> listar() {
        return tiendaAdminMapper.toDTOList(tiendaRepository.findAllConCadena());
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listarCadenas() {
        return cadenaAdminMapper.toDTOList(cadenaRepository.findAllByOrderByNombreAsc());
    }

    @Transactional(readOnly = true)
    public TiendaRequestDTO buscarFormulario(Integer id) {
        return tiendaAdminMapper.toRequestDTO(tiendaRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(TiendaRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Cadena cadena = cadenaRepository.findById(request.getIdCadena()).orElseThrow();
        Tienda tienda = request.getId() == null ? new Tienda() : tiendaRepository.findById(request.getId()).orElseThrow();        tiendaAdminMapper.aplicarRequest(request, tienda, cadena);
        tiendaRepository.save(tienda);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!tiendaRepository.existsById(id)) {
            return "La tienda no existe";
        }

        if (tiendaRepository.countCampanasByTienda(id) > 0
                || tiendaRepository.countUsuariosByTienda(id) > 0
                || tiendaRepository.countTurnosByTienda(id) > 0) {
            return "No se puede eliminar una tienda con campanas, usuarios o turnos asociados";
        }

        tiendaRepository.deleteById(id);
        return null;
    }

    private String validar(TiendaRequestDTO request) {
        if (request == null) {
            return "Los datos de la tienda son obligatorios";
        }

        if (request.getIdCadena() == null) {
            return "La cadena es obligatoria";
        }

        if (!cadenaRepository.existsById(request.getIdCadena())) {
            return "La cadena no existe";
        }

        if (vacio(request.getNombre())) {
            return "El nombre es obligatorio";
        }

        if (vacio(request.getDireccion())) {
            return "La direccion es obligatoria";
        }

        if (vacio(request.getCodPostal())) {
            return "El codigo postal es obligatorio";
        }

        if (vacio(request.getLocalidad())) {
            return "La localidad es obligatoria";
        }

        if (largo(request.getLocalidad(), 255)) {
            return "No hay ninguna ciudad tan larga, te la has inventado";
        }

        if (!request.getCodPostal().trim().matches("\\d{5}")) {
            return "El codigo postal debe valido";
        }

        if (largo(request.getNombre(), 150)) {
            return "El nombre no puede superar los 150 caracteres";
        }

        if (largo(request.getDireccion(), 255)) {
            return "La direccion no puede superar los 255 caracteres";
        }

        return tiendaRepository.existsDuplicada(
                request.getIdCadena(),
                request.getNombre().trim(),
                request.getDireccion().trim(),
                request.getId())
                ? "Ya existe una tienda con esa cadena, nombre y direccion"
                : null;
    }



    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean largo(String valor, int maximo) {
        return valor != null && valor.trim().length() > maximo;
    }
}