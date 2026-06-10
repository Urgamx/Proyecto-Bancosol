package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.CadenaRepository;
import com.example.proyectobancosol.dao.CampanaCadenaRepository;
import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.response.ValidacionBasicaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Campana;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ValidacionAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";

    private final UsuarioRepository usuarioRepository;
    private final TiendaRepository tiendaRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final CampanaRepository campanaRepository;
    private final CampanaCadenaRepository campanaCadenaRepository;
    private final CadenaRepository cadenaRepository;

    public ValidacionAdminService(UsuarioRepository usuarioRepository,
                                  TiendaRepository tiendaRepository,
                                  ColaboradorRepository colaboradorRepository,
                                  CampanaRepository campanaRepository,
                                  CampanaCadenaRepository campanaCadenaRepository,
                                  CadenaRepository cadenaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tiendaRepository = tiendaRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.campanaRepository = campanaRepository;
        this.campanaCadenaRepository = campanaCadenaRepository;
        this.cadenaRepository = cadenaRepository;
    }

    @Transactional(readOnly = true)
    public List<ValidacionBasicaResponseDTO> listar() {
        return List.of(
                validarCoordinadoresSinTiendas(),
                validarCoordinadoresSinColaboradores(),
                validarTiendasSinCoordinador(),
                validarColaboradoresSinCoordinador(),
                validarCampanasSinCadenas(),
                validarCadenasSinTiendas(),
                validarColaboradoresPendientes()
        );
    }

    private ValidacionBasicaResponseDTO validarCoordinadoresSinTiendas() {
        List<String> elementos = usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .filter(this::estaActivo)
                .filter(usuario -> usuarioRepository.countTiendasByUsuario(usuario.getId()) == 0)
                .map(Usuario::getNombreCompleto)
                .toList();

        return crear("Coordinadores", "Coordinadores activos sin tiendas asignadas", elementos);
    }

    private ValidacionBasicaResponseDTO validarCoordinadoresSinColaboradores() {
        List<String> elementos = usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .filter(this::estaActivo)
                .filter(usuario -> usuarioRepository.countColaboradoresByUsuario(usuario.getId()) == 0)
                .map(Usuario::getNombreCompleto)
                .toList();

        return crear("Coordinadores", "Coordinadores activos sin colaboradores asignados", elementos);
    }

    private ValidacionBasicaResponseDTO validarTiendasSinCoordinador() {
        List<String> elementos = tiendaRepository.findAllConCadena()
                .stream()
                .filter(tienda -> tiendaRepository.countUsuariosByTienda(tienda.getId()) == 0)
                .map(this::nombreTienda)
                .toList();

        return crear("Tiendas", "Tiendas sin coordinador asignado", elementos);
    }

    private ValidacionBasicaResponseDTO validarColaboradoresSinCoordinador() {
        List<String> elementos = colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(1)
                .stream()
                .filter(colaborador -> colaboradorRepository.countUsuariosByColaborador(colaborador.getId()) == 0)
                .map(Colaborador::getNombreEntidad)
                .toList();

        return crear("Colaboradores", "Colaboradores activos sin coordinador asignado", elementos);
    }

    private ValidacionBasicaResponseDTO validarCampanasSinCadenas() {
        List<String> elementos = campanaRepository.findAllConTipo()
                .stream()
                .filter(campana -> campana.getActivo() != null && campana.getActivo() == 1)
                .filter(campana -> campanaCadenaRepository.findIdsCadenasByCampanaId(campana.getId()).isEmpty())
                .map(this::nombreCampana)
                .toList();

        return crear("Campanas", "Campanas activas sin cadenas asignadas", elementos);
    }

    private ValidacionBasicaResponseDTO validarCadenasSinTiendas() {
        List<String> elementos = cadenaRepository.findAllByOrderByNombreAsc()
                .stream()
                .filter(cadena -> cadenaRepository.countTiendasByCadena(cadena.getId()) == 0)
                .map(Cadena::getNombre)
                .toList();

        return crear("Cadenas", "Cadenas sin tiendas asociadas", elementos);
    }

    private ValidacionBasicaResponseDTO validarColaboradoresPendientes() {
        List<String> elementos = colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(2)
                .stream()
                .map(Colaborador::getNombreEntidad)
                .toList();

        return crear("Colaboradores", "Colaboradores pendientes de validar", elementos);
    }

    private ValidacionBasicaResponseDTO crear(String modulo, String validacion, List<String> elementos) {
        Long total = (long) elementos.size();

        return new ValidacionBasicaResponseDTO(
                modulo,
                validacion,
                total,
                total == 0 ? "OK" : "Revisar",
                total == 0 ? "Sin incidencias" : String.join(", ", elementos)
        );
    }

    private boolean estaActivo(Usuario usuario) {
        return usuario.getActivo() != null && usuario.getActivo() == 1;
    }

    private String nombreTienda(Tienda tienda) {
        return tienda.getIdCadena().getNombre() + " - " + tienda.getNombre();
    }

    private String nombreCampana(Campana campana) {
        return campana.getTipoDeCampana().getNombre() + " - " + campana.getFecha();
    }
}