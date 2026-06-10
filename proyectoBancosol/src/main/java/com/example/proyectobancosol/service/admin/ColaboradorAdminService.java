package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradorAdminService {

    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;

    public ColaboradorAdminService(ColaboradorRepository colaboradorRepository,
                                   UsuarioColaboradorRepository usuarioColaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.usuarioColaboradorRepository = usuarioColaboradorRepository;
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listar() {
        return colaboradorRepository.findAllByOrderByNombreEntidadAsc()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarPendientes() {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(2)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarActivos() {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(1)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ColaboradorRequestDTO buscarFormulario(Integer id) {
        Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow();

        return new ColaboradorRequestDTO(
                colaborador.getId(),
                colaborador.getNombreEntidad(),
                colaborador.getEmail(),
                colaborador.getContactoNom(),
                colaborador.getContactoTlf(),
                colaborador.getDomicilio(),
                colaborador.getLocalidad(),
                colaborador.getZonaGeografica(),
                colaborador.getObservaciones(),
                colaborador.getCodPostal(),
                colaborador.getEstado()
        );
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

        colaborador.setNombreEntidad(colaboradorRequestDTO.getNombreEntidad().trim());
        colaborador.setEmail(colaboradorRequestDTO.getEmail().trim());
        colaborador.setContactoNom(limpiar(colaboradorRequestDTO.getContactoNom()));
        colaborador.setContactoTlf(limpiar(colaboradorRequestDTO.getContactoTlf()));
        colaborador.setDomicilio(limpiar(colaboradorRequestDTO.getDomicilio()));
        colaborador.setLocalidad(limpiar(colaboradorRequestDTO.getLocalidad()));
        colaborador.setZonaGeografica(limpiar(colaboradorRequestDTO.getZonaGeografica()));
        colaborador.setObservaciones(limpiar(colaboradorRequestDTO.getObservaciones()));
        colaborador.setCodPostal(limpiar(colaboradorRequestDTO.getCodPostal()));
        colaborador.setEstado(colaboradorRequestDTO.getEstado() == null ? 2 : colaboradorRequestDTO.getEstado());

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

    private String limpiar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        return valor.trim();
    }

    private ColaboradorResponseDTO convertirAResponseDTO(Colaborador colaborador) {
        String coordinadores = usuarioColaboradorRepository.findByColaboradorIdConUsuario(colaborador.getId())
                .stream()
                .map(usuarioColaborador -> usuarioColaborador.getUsuario().getNombreCompleto())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin coordinador");

        Long voluntarios = colaboradorRepository.countVoluntariosByColaborador(colaborador.getId());
        Long turnos = colaboradorRepository.countTurnosByColaborador(colaborador.getId());

        return new ColaboradorResponseDTO(
                colaborador.getId(),
                colaborador.getNombreEntidad(),
                colaborador.getEmail(),
                colaborador.getContactoNom(),
                colaborador.getContactoTlf(),
                colaborador.getDomicilio(),
                colaborador.getLocalidad(),
                colaborador.getZonaGeografica(),
                colaborador.getCodPostal(),
                convertirEstadoATexto(colaborador.getEstado()),
                coordinadores,
                voluntarios,
                turnos
        );
    }

    private String convertirEstadoATexto(Integer estado) {
        if (estado != null && estado == 1) {
            return "Activo";
        }

        if (estado != null && estado == 2) {
            return "Pendiente";
        }

        return "Inactivo";
    }
}