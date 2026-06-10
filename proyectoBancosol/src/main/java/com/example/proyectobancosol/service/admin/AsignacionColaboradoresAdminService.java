package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.AsignacionColaboradoresRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionColaboradoresCoordinadorDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionColaboradoresAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";

    private final UsuarioRepository usuarioRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;

    public AsignacionColaboradoresAdminService(UsuarioRepository usuarioRepository,
                                               ColaboradorRepository colaboradorRepository,
                                               UsuarioColaboradorRepository usuarioColaboradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.usuarioColaboradorRepository = usuarioColaboradorRepository;
    }

    @Transactional(readOnly = true)
    public List<AsignacionColaboradoresCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionColaboradoresRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = buscarCoordinador(idCoordinador);
        List<Integer> idsColaboradores = usuarioColaboradorRepository.findIdsColaboradoresByUsuarioId(coordinador.getId());

        return new AsignacionColaboradoresRequestDTO(
                coordinador.getId(),
                idsColaboradores
        );
    }

    @Transactional(readOnly = true)
    public Usuario buscarCoordinador(Integer idCoordinador) {
        Usuario coordinador = usuarioRepository.findById(idCoordinador).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            throw new IllegalArgumentException("El usuario no es coordinador");
        }

        return coordinador;
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarColaboradores() {
        return colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(1)
                .stream()
                .map(colaborador -> new ColaboradorResponseDTO(
                        colaborador.getId(),
                        colaborador.getNombreEntidad(),
                        colaborador.getEmail(),
                        colaborador.getContactoNom(),
                        colaborador.getContactoTlf(),
                        colaborador.getDomicilio(),
                        colaborador.getLocalidad(),
                        colaborador.getZonaGeografica(),
                        colaborador.getCodPostal(),
                        "Activo",
                        null,
                        0L,
                        0L
                ))
                .toList();
    }

    @Transactional
    public String guardar(AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO) {
        String error = validar(asignacionColaboradoresRequestDTO);

        if (error != null) {
            return error;
        }

        Usuario coordinador = usuarioRepository.findById(asignacionColaboradoresRequestDTO.getIdCoordinador()).orElseThrow();

        usuarioColaboradorRepository.deleteByUsuarioId(coordinador.getId());

        List<Integer> idsColaboradores = asignacionColaboradoresRequestDTO.getIdsColaboradores();

        if (idsColaboradores == null) {
            idsColaboradores = new ArrayList<>();
        }

        idsColaboradores.stream()
                .distinct()
                .forEach(idColaborador -> guardarRelacion(coordinador, idColaborador));

        return null;
    }

    private String validar(AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO) {
        if (asignacionColaboradoresRequestDTO == null) {
            return "Los datos de asignacion son obligatorios";
        }

        if (asignacionColaboradoresRequestDTO.getIdCoordinador() == null) {
            return "El coordinador es obligatorio";
        }

        if (!usuarioRepository.existsById(asignacionColaboradoresRequestDTO.getIdCoordinador())) {
            return "El coordinador no existe";
        }

        Usuario coordinador = usuarioRepository.findById(asignacionColaboradoresRequestDTO.getIdCoordinador()).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            return "El usuario seleccionado no es coordinador";
        }

        if (asignacionColaboradoresRequestDTO.getIdsColaboradores() != null) {
            for (Integer idColaborador : asignacionColaboradoresRequestDTO.getIdsColaboradores()) {
                if (!colaboradorRepository.existsById(idColaborador)) {
                    return "Uno de los colaboradores seleccionados no existe";
                }
            }
        }

        return null;
    }

    private void guardarRelacion(Usuario coordinador, Integer idColaborador) {
        Colaborador colaborador = colaboradorRepository.findById(idColaborador).orElseThrow();

        UsuarioColaboradorId usuarioColaboradorId = new UsuarioColaboradorId();
        usuarioColaboradorId.setIdUsuario(coordinador.getId());
        usuarioColaboradorId.setIdColaborador(colaborador.getId());

        UsuarioColaborador usuarioColaborador = new UsuarioColaborador();
        usuarioColaborador.setId(usuarioColaboradorId);
        usuarioColaborador.setUsuario(coordinador);
        usuarioColaborador.setColaborador(colaborador);

        usuarioColaboradorRepository.save(usuarioColaborador);
    }

    private AsignacionColaboradoresCoordinadorDTO convertirAResponseDTO(Usuario coordinador) {
        String colaboradores = usuarioColaboradorRepository.findColaboradoresAsignadosByUsuarioId(coordinador.getId())
                .stream()
                .map(usuarioColaborador -> usuarioColaborador.getColaborador().getNombreEntidad())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin colaboradores");

        return new AsignacionColaboradoresCoordinadorDTO(
                coordinador.getId(),
                coordinador.getNombreCompleto(),
                coordinador.getEmail(),
                colaboradores
        );
    }
}