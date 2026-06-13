package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dao.CampanaCadenaRepository;
import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.dao.TipoDeCampanaRepository;
import com.example.proyectobancosol.dto.request.CampanaRequestDTO;
import com.example.proyectobancosol.dto.response.CadenaResponseDTO;
import com.example.proyectobancosol.dto.response.CampanaResponseDTO;
import com.example.proyectobancosol.dto.response.TipoCampanaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Campana;
import com.example.proyectobancosol.entity.TipoDeCampana;
import com.example.proyectobancosol.mapper.admin.CadenaAdminMapper;
import com.example.proyectobancosol.mapper.admin.CampanaAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanaAdminService {

    private static final String SIN_CADENAS = "Sin cadenas";

    private final CampanaRepository campanaRepository;
    private final TipoDeCampanaRepository tipoDeCampanaRepository;
    private final CadenaRepository cadenaRepository;
    private final CampanaCadenaRepository campanaCadenaRepository;
    private final CampanaAdminMapper campanaAdminMapper;
    private final CadenaAdminMapper cadenaAdminMapper;

    @Transactional(readOnly = true)
    public List<CampanaResponseDTO> listar() {
        return campanaRepository.findAllConTipo().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CampanaRequestDTO buscarFormulario(Integer id) {
        return campanaAdminMapper.toRequestDTO(
                campanaRepository.findById(id).orElseThrow(),
                campanaCadenaRepository.findIdsCadenasByCampanaId(id)
        );
    }

    @Transactional(readOnly = true)
    public List<TipoCampanaResponseDTO> listarTipos() {
        return tipoDeCampanaRepository.findAllByOrderByNombreAsc().stream()
                .map(campanaAdminMapper::toTipoCampanaResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listarCadenas() {
        return cadenaAdminMapper.toDTOList(cadenaRepository.findAllByOrderByNombreAsc());
    }

    @Transactional
    public String guardar(CampanaRequestDTO request) {
        Integer fecha = campanaAdminMapper.convertirFechaFormularioAEntero(request == null ? null : request.getFechaFormulario());
        String error = validar(request, fecha);

        if (error != null) {
            return error;
        }

        TipoDeCampana tipo = tipoDeCampanaRepository.findById(request.getIdTipoCampana()).orElseThrow();
        Campana campana = request.getId() == null ? new Campana() : campanaRepository.findById(request.getId()).orElseThrow();
        campanaAdminMapper.aplicarRequest(request, campana, tipo, fecha);
        campanaRepository.save(campana);
        campanaCadenaRepository.deleteByCampanaId(campana.getId());
        request.getIdsCadenas().stream().distinct().forEach(idCadena -> guardarRelacion(campana, idCadena));

        return null;
    }

    @Transactional(readOnly = true)
    public List<CampanaResponseDTO> filtrar(Integer idTipoCampana, String fechaFormulario, Integer activo) {
        Integer fecha = campanaAdminMapper.convertirFechaFormularioAEntero(fechaFormulario);

        return campanaRepository.findFiltradas(idTipoCampana, fecha, activo).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!campanaRepository.existsById(id)) {
            return "La campana no existe";
        }

        if (campanaRepository.countTiendasByCampana(id) > 0 || campanaRepository.countTurnosByCampana(id) > 0) {
            return "No se puede eliminar una campana con tiendas o turnos asociados";
        }

        campanaCadenaRepository.deleteByCampanaId(id);
        campanaRepository.deleteById(id);
        return null;
    }

    private String validar(CampanaRequestDTO request, Integer fecha) {
        if (request == null) {
            return "Los datos de la campana son obligatorios";
        }

        if (request.getIdTipoCampana() == null) {
            return "El tipo de campana es obligatorio";
        }

        if (!tipoDeCampanaRepository.existsById(request.getIdTipoCampana())) {
            return "Ese tipo de campana no existe";
        }

        if (vacio(request.getFechaFormulario())) {
            return "La fecha es obligatoria";
        }

        if (fecha == null) {
            return "La fecha no tiene un formato valido";
        }

        if (request.getIdsCadenas() == null || request.getIdsCadenas().isEmpty()) {
            return "Tienes que elegir minimo una cadena";
        }

        if (!request.getIdsCadenas().stream().allMatch(cadenaRepository::existsById)) {
            return "Una de las cadenas seleccionadas no existe";
        }

        return campanaRepository.existsDuplicada(request.getIdTipoCampana(), fecha, request.getId())
                ? "Ya existe una campana con ese tipo y fecha"
                : null;
    }

    private void guardarRelacion(Campana campana, Integer idCadena) {
        Cadena cadena = cadenaRepository.findById(idCadena).orElseThrow();
        campanaCadenaRepository.save(campanaAdminMapper.toCampanaCadena(campana, cadena));
    }

    private CampanaResponseDTO toResponseDTO(Campana campana) {
        String cadenas = campanaCadenaRepository.findByCampanaIdConCadena(campana.getId()).stream()
                .map(campanaCadena -> campanaCadena.getCadena().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse(SIN_CADENAS);

        return campanaAdminMapper.toDTO(campana, cadenas);
    }



    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}