package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradorAdminService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;
    private final ColaboradorAdminMapper colaboradorAdminMapper;

    public ColaboradorAdminService(ColaboradorRepository colaboradorRepository,
                                   UsuarioColaboradorRepository usuarioColaboradorRepository,
                                   ColaboradorAdminMapper colaboradorAdminMapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.usuarioColaboradorRepository = usuarioColaboradorRepository;
        this.colaboradorAdminMapper = colaboradorAdminMapper;
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listar() {
        return colaboradorRepository.findAllByOrderByNombreEntidadAsc()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarPendientes() {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(2)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarActivos() {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(1)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ColaboradorRequestDTO buscarFormulario(Integer id) {
        return colaboradorAdminMapper.toRequestDTO(colaboradorRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(ColaboradorRequestDTO colaboradorRequestDTO) {
        String error = validar(colaboradorRequestDTO);

        if (error != null) {
            return error;
        }

        Colaborador colaborador;

        if (colaboradorRequestDTO.getId() == null) {
            colaborador = new Colaborador();
        } else {
            colaborador = colaboradorRepository.findById(colaboradorRequestDTO.getId()).orElseThrow();
        }

        colaboradorAdminMapper.aplicarRequest(colaboradorRequestDTO, colaborador);
        colaboradorRepository.save(colaborador);

        return null;
    }

    @Transactional
    public String rechazar(Integer id) {
        if (!colaboradorRepository.existsById(id)) {
            return "El colaborador no existe";
        }

        Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow();
        colaborador.setEstado(0);
        colaboradorRepository.save(colaborador);

        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!colaboradorRepository.existsById(id)) {
            return "El colaborador no existe";
        }

        Long voluntarios = colaboradorRepository.countVoluntariosByColaborador(id);
        Long turnos = colaboradorRepository.countTurnosByColaborador(id);
        Long usuarios = colaboradorRepository.countUsuariosByColaborador(id);

        if (voluntarios > 0 || turnos > 0 || usuarios > 0) {
            return "No se puede eliminar un colaborador con voluntarios, turnos o coordinadores asociados";
        }

        colaboradorRepository.deleteById(id);
        return null;
    }

    private String validar(ColaboradorRequestDTO colaboradorRequestDTO) {
        if (colaboradorRequestDTO == null) {
            return "Los datos del colaborador son obligatorios";
        }

        if (colaboradorRequestDTO.getNombreEntidad() == null || colaboradorRequestDTO.getNombreEntidad().trim().isEmpty()) {
            return "El nombre de entidad es obligatorio";
        }

        if (colaboradorRequestDTO.getEmail() == null || colaboradorRequestDTO.getEmail().trim().isEmpty()) {
            return "El email es obligatorio";
        }

        if (!colaboradorRequestDTO.getEmail().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "El email no tiene un formato valido";
        }

        if (colaboradorRequestDTO.getNombreEntidad().trim().length() > 150) {
            return "El nombre de entidad no puede superar 150 caracteres";
        }

        if (colaboradorRequestDTO.getEmail().trim().length() > 100) {
            return "El email no puede superar 100 caracteres";
        }

        if (colaboradorRequestDTO.getContactoTlf() != null && colaboradorRequestDTO.getContactoTlf().trim().length() > 20) {
            return "El telefono no puede superar 20 caracteres";
        }

        if (colaboradorRequestDTO.getObservaciones() != null && colaboradorRequestDTO.getObservaciones().trim().length() > 255) {
            return "Las observaciones no pueden superar 255 caracteres";
        }

        if (colaboradorRequestDTO.getEstado() == null) {
            return "El estado es obligatorio";
        }

        if (colaboradorRequestDTO.getEstado() != 0 && colaboradorRequestDTO.getEstado() != 1 && colaboradorRequestDTO.getEstado() != 2) {
            return "El estado no es valido";
        }

        if (colaboradorRepository.existsEmailDuplicado(colaboradorRequestDTO.getEmail().trim(), colaboradorRequestDTO.getId())) {
            return "Ya existe un colaborador con ese email";
        }

        return null;
    }

    private ColaboradorResponseDTO toResponseDTO(Colaborador colaborador) {
        String coordinadores = usuarioColaboradorRepository.findByColaboradorIdConUsuario(colaborador.getId())
                .stream()
                .map(usuarioColaborador -> usuarioColaborador.getUsuario().getNombreCompleto())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin coordinador");

        ColaboradorResponseDTO colaboradorResponseDTO = colaboradorAdminMapper.toDTO(colaborador);
        colaboradorResponseDTO.setCoordinadoresAsignados(coordinadores);
        colaboradorResponseDTO.setVoluntarios(colaboradorRepository.countVoluntariosByColaborador(colaborador.getId()));
        colaboradorResponseDTO.setTurnos(colaboradorRepository.countTurnosByColaborador(colaborador.getId()));

        return colaboradorResponseDTO;
    }
}