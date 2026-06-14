package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.CapitanRequestDTO;
import com.example.proyectobancosol.dto.response.CapitanResponseDTO;
import com.example.proyectobancosol.entity.Campana;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.admin.CapitanAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapitanAdminService {

    private static final String ROL_CAPITAN = "CAPITAN";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final CapitanAdminMapper  capitanAdminMapper;

    private final CampanaRepository campanaRepository;
    private final TiendaRepository tiendaRepository;


    @Transactional(readOnly = true)
    public List<CapitanResponseDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_CAPITAN).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private CapitanResponseDTO toResponseDTO(Usuario usuario) {
        List<String> nombresCampanas = campanaRepository.findNombresCampanasByUsuarioId(usuario.getId());
        List<String> nombresTiendas = tiendaRepository.findNombresTiendasByUsuarioId(usuario.getId());

        return capitanAdminMapper.toDTO(usuario, nombresCampanas, nombresTiendas);
    }

    @Transactional(readOnly = true)
    public CapitanRequestDTO buscarFormulario(Integer id) {
        List<Integer> idsCampanas = campanaRepository.findIdCampanasByUsuarioId(id);
        List<Integer> idsTiendas = tiendaRepository.findIdTiendasByUsuarioId(id);

        return capitanAdminMapper.toRequestDTO(usuarioRepository.findById(id).orElseThrow(), idsCampanas, idsTiendas);
    }

    @Transactional
    public String guardar(CapitanRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Usuario usuario = request.getId() == null ? nuevoUsuario(request) : usuarioRepository.findById(request.getId()).orElseThrow();
        actualizarPassword(usuario, request);
        capitanAdminMapper.aplicarRequest(request, usuario, rolRepository.findByNombre(ROL_CAPITAN).orElseThrow());


        //tiendas
        usuario.getTiendas().clear();
        if (request.getIdTiendasSeleccionadas() != null) {
            List<Tienda> tiendasNuevas = tiendaRepository.findAllById(request.getIdTiendasSeleccionadas());
            usuario.getTiendas().addAll(tiendasNuevas);
        }


        //Campañas
        List<Campana> campanasAntiguas = campanaRepository.findAll();
        for (Campana c : campanasAntiguas) {
            if (c.getUsuario() != null && c.getUsuario().getId().equals(usuario.getId())) {
                c.setUsuario(null);
            }
        }

        if (request.getIdCampanasSeleccionadas() != null) {
            List<Campana> campanasNuevas = campanaRepository.findAllById(request.getIdCampanasSeleccionadas());
            for (Campana c : campanasNuevas) {
                c.setUsuario(usuario);
            }
        }

        usuarioRepository.save(usuario);
        return null;
    }

    @Transactional
    public String eliminar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return "El capitán no existe";
        }
        //borramos tiendas
        usuario.getTiendas().clear();

        //borramos campañas
        List<Campana> campanasAsignadas = campanaRepository.findAll();
        for (Campana c : campanasAsignadas) {
            if (c.getUsuario() != null && c.getUsuario().getId().equals(id)) {
                c.setUsuario(null);
            }
        }

        usuarioRepository.save(usuario);
        usuarioRepository.delete(usuario);

        return null;
    }

    private String validar(CapitanRequestDTO request) {
        if (request == null) {
            return "Los datos del capitán son obligatorios";
        }

        if (vacio(request.getNombreCompleto())) {
            return "El nombre es obligatorio";
        }

        if (vacio(request.getEmail())) {
            return "El email es obligatorio";
        }

        if (!request.getEmail().trim().matches(EMAIL_REGEX)) {
            return "El email no tiene un formato válido";
        }

        if (request.getId() == null && vacio(request.getPassword())) {
            return "Debes introducir una contraseña";
        }

        if (largo(request.getNombreCompleto(), 150)) {
            return "El nombre no puede superar los 150 caracteres";
        }

        if (largo(request.getEmail(), 150)) {
            return "El email no puede superar los 150 caracteres";
        }

        return usuarioRepository.existsEmailDuplicado(request.getEmail().trim(), request.getId())
                ? "Ya existe un usuario con ese email"
                : null;
    }


    private Usuario nuevoUsuario(CapitanRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setPassword(request.getPassword().trim());
        return usuario;
    }

    private void actualizarPassword(Usuario usuario, CapitanRequestDTO request) {
        if (!vacio(request.getPassword())) {
            usuario.setPassword(request.getPassword().trim());
        }
    }


    private boolean vacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean largo(String valor, int maximo) {
        return valor != null && valor.trim().length() > maximo;
    }

    @Transactional(readOnly = true)
    public List<CapitanResponseDTO> listarConFiltros(String texto, Integer idCampana, Integer idTienda) {
        String textoFiltrado = (texto != null && !texto.trim().isEmpty()) ? texto.trim() : null;

        return usuarioRepository.findByRolAndFiltros(ROL_CAPITAN, textoFiltrado, idCampana, idTienda).stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
