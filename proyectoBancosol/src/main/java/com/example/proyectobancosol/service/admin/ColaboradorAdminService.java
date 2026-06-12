package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColaboradorAdminService {

    private static final String SIN_COORDINADOR = "Sin coordinador";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;
    private final ColaboradorAdminMapper colaboradorAdminMapper;

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarPendientes() {
        return listarPorEstado(2);
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarActivos() {
        return listarPorEstado(1);
    }

    @Transactional(readOnly = true)
    public ColaboradorRequestDTO buscarFormulario(Integer id) {
        return colaboradorAdminMapper.toRequestDTO(colaboradorRepository.findById(id).orElseThrow());
    }

    @Transactional
    public String guardar(ColaboradorRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Colaborador colaborador = request.getId() == null ? new Colaborador() : colaboradorRepository.findById(request.getId()).orElseThrow();
        colaboradorAdminMapper.aplicarRequest(request, colaborador);
        colaboradorRepository.save(colaborador);
        return null;
    }

    @Transactional
    public String rechazar(Integer id) {
        Colaborador colaborador = colaboradorRepository.findById(id).orElse(null);

        if (colaborador == null) {
            return "El colaborador no existe";
        }

        colaborador.setEstado(0);
        colaboradorRepository.save(colaborador);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        if (!colaboradorRepository.existsById(id)) {
            return "El colaborador no existe";
        }

        if (colaboradorRepository.countVoluntariosByColaborador(id) > 0
                || colaboradorRepository.countTurnosByColaborador(id) > 0
                || colaboradorRepository.countUsuariosByColaborador(id) > 0) {
            return "No se puede eliminar un colaborador con voluntarios, turnos o coordinadores asociados";
        }

        colaboradorRepository.deleteById(id);
        return null;
    }

    private List<ColaboradorResponseDTO> listarPorEstado(Integer estado) {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(estado).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private String validar(ColaboradorRequestDTO request) {
        if (request == null) {
            return "Los datos del colaborador son obligatorios";
        }

        if (vacio(request.getNombreEntidad())) {
            return "El nombre delaentidad es obligatorio";
        }

        if (vacio(request.getEmail())) {
            return "El email es obligatorio";
        }

        if (!request.getEmail().trim().matches(EMAIL_REGEX)) {
            return "El email no tiene un formato valido";
        }

        if (largo(request.getNombreEntidad(), 150)) {
            return "El nombre de la entidad no puede superar los 150 caracteres";
        }

        if (largo(request.getEmail(), 100)) {
            return "El email no puede superar los 100 caracteres";
        }

        if (largo(request.getContactoTlf(), 20)) {
            return "El telefono no puede superar los 20 caracteres";
        }

        if (largo(request.getObservaciones(), 255)) {
            return "Las observaciones no pueden superar los 255 caracteres";
        }

        if (request.getEstado() == null) {
            return "El estado es obligatorio";
        }

        if (request.getEstado() < 0 || request.getEstado() > 2) {
            return "El estado no es valido";
        }

        return colaboradorRepository.existsEmailDuplicado(request.getEmail().trim(), request.getId())
                ? "Ya existe un colaborador con ese email"
                : null;
    }

    private ColaboradorResponseDTO toResponseDTO(Colaborador colaborador) {
        String coordinadores = usuarioColaboradorRepository.findByColaboradorIdConUsuario(colaborador.getId()).stream()
                .map(usuarioColaborador -> usuarioColaborador.getUsuario().getNombreCompleto())
                .reduce((a, b) -> a + ", " + b)
                .orElse(SIN_COORDINADOR);

        return colaboradorAdminMapper.toDTO(
                colaborador,
                coordinadores,
                colaboradorRepository.countVoluntariosByColaborador(colaborador.getId()),
                colaboradorRepository.countTurnosByColaborador(colaborador.getId())
        );
    }

    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean largo(String valor, int maximo) {
        return valor != null && valor.trim().length() > maximo;
    }
}