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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampanaAdminService {

    private final CampanaRepository campanaRepository;
    private final TipoDeCampanaRepository tipoDeCampanaRepository;
    private final CadenaRepository cadenaRepository;
    private final CampanaCadenaRepository campanaCadenaRepository;
    private final CampanaAdminMapper campanaAdminMapper;
    private final CadenaAdminMapper cadenaAdminMapper;

    public CampanaAdminService(CampanaRepository campanaRepository,
                               TipoDeCampanaRepository tipoDeCampanaRepository,
                               CadenaRepository cadenaRepository,
                               CampanaCadenaRepository campanaCadenaRepository,
                               CampanaAdminMapper campanaAdminMapper,
                               CadenaAdminMapper cadenaAdminMapper) {
        this.campanaRepository = campanaRepository;
        this.tipoDeCampanaRepository = tipoDeCampanaRepository;
        this.cadenaRepository = cadenaRepository;
        this.campanaCadenaRepository = campanaCadenaRepository;
        this.campanaAdminMapper = campanaAdminMapper;
        this.cadenaAdminMapper = cadenaAdminMapper;
    }

    @Transactional(readOnly = true)
    public List<CampanaResponseDTO> listar() {
        return campanaRepository.findAllConTipo()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CampanaRequestDTO buscarFormulario(Integer id) {
        Campana campana = campanaRepository.findById(id).orElseThrow();
        List<Integer> idsCadenas = campanaCadenaRepository.findIdsCadenasByCampanaId(id);

        return campanaAdminMapper.toRequestDTO(campana, idsCadenas);
    }

    @Transactional(readOnly = true)
    public List<TipoCampanaResponseDTO> listarTipos() {
        return tipoDeCampanaRepository.findAllByOrderByNombreAsc()
                .stream()
                .map(campanaAdminMapper::toTipoCampanaResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CadenaResponseDTO> listarCadenas() {
        return cadenaAdminMapper.toDTOList(cadenaRepository.findAllByOrderByNombreAsc());
    }

    @Transactional
    public String guardar(CampanaRequestDTO campanaRequestDTO) {
        String error = validar(campanaRequestDTO);

        if (error != null) {
            return error;
        }

        Integer fecha = campanaAdminMapper.convertirFechaFormularioAEntero(campanaRequestDTO.getFechaFormulario());
        TipoDeCampana tipoDeCampana = tipoDeCampanaRepository.findById(campanaRequestDTO.getIdTipoCampana()).orElseThrow();

        Campana campana;

        if (campanaRequestDTO.getId() == null) {
            campana = new Campana();
            campana.setId(campanaRepository.findMaxId() + 1);
        } else {
            campana = campanaRepository.findById(campanaRequestDTO.getId()).orElseThrow();
        }

        campanaAdminMapper.aplicarRequest(campanaRequestDTO, campana, tipoDeCampana, fecha);
        campanaRepository.save(campana);

        campanaCadenaRepository.deleteByCampanaId(campana.getId());

        campanaRequestDTO.getIdsCadenas()
                .stream()
                .distinct()
                .forEach(idCadena -> guardarRelacionCampanaCadena(campana, idCadena));

        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!campanaRepository.existsById(id)) {
            return "La campana no existe";
        }

        Long tiendas = campanaRepository.countTiendasByCampana(id);
        Long turnos = campanaRepository.countTurnosByCampana(id);

        if (tiendas > 0 || turnos > 0) {
            return "No se puede eliminar una campana con tiendas o turnos asociados";
        }

        campanaCadenaRepository.deleteByCampanaId(id);
        campanaRepository.deleteById(id);

        return null;
    }

    private String validar(CampanaRequestDTO campanaRequestDTO) {
        if (campanaRequestDTO == null) {
            return "Los datos de la campana son obligatorios";
        }

        if (campanaRequestDTO.getIdTipoCampana() == null) {
            return "El tipo de campana es obligatorio";
        }

        if (!tipoDeCampanaRepository.existsById(campanaRequestDTO.getIdTipoCampana())) {
            return "El tipo de campana no existe";
        }

        if (campanaRequestDTO.getFechaFormulario() == null || campanaRequestDTO.getFechaFormulario().isBlank()) {
            return "La fecha es obligatoria";
        }

        Integer fecha = campanaAdminMapper.convertirFechaFormularioAEntero(campanaRequestDTO.getFechaFormulario());

        if (fecha == null) {
            return "La fecha no tiene un formato valido";
        }

        if (campanaRequestDTO.getIdsCadenas() == null || campanaRequestDTO.getIdsCadenas().isEmpty()) {
            return "Debe seleccionar al menos una cadena";
        }

        if (campanaRepository.existsDuplicada(
                campanaRequestDTO.getIdTipoCampana(),
                fecha,
                campanaRequestDTO.getId())) {
            return "Ya existe una campana con ese tipo y fecha";
        }

        return null;
    }

    private void guardarRelacionCampanaCadena(Campana campana, Integer idCadena) {
        Cadena cadena = cadenaRepository.findById(idCadena).orElseThrow();
        campanaCadenaRepository.save(campanaAdminMapper.toCampanaCadena(campana, cadena));
    }

    private CampanaResponseDTO toResponseDTO(Campana campana) {
        String cadenas = campanaCadenaRepository.findByCampanaIdConCadena(campana.getId())
                .stream()
                .map(campanaCadena -> campanaCadena.getCadena().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin cadenas");

        CampanaResponseDTO campanaResponseDTO = campanaAdminMapper.toDTO(campana);
        campanaResponseDTO.setCadenasParticipantes(cadenas);

        return campanaResponseDTO;
    }
}