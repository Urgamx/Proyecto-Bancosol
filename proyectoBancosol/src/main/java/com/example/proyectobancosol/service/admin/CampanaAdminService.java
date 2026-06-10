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
import com.example.proyectobancosol.entity.CampanaCadena;
import com.example.proyectobancosol.entity.CampanaCadenaId;
import com.example.proyectobancosol.entity.TipoDeCampana;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CampanaAdminService {

    private final CampanaRepository campanaRepository;
    private final TipoDeCampanaRepository tipoDeCampanaRepository;
    private final CadenaRepository cadenaRepository;
    private final CampanaCadenaRepository campanaCadenaRepository;

    public CampanaAdminService(CampanaRepository campanaRepository,
                               TipoDeCampanaRepository tipoDeCampanaRepository,
                               CadenaRepository cadenaRepository,
                               CampanaCadenaRepository campanaCadenaRepository) {
        this.campanaRepository = campanaRepository;
        this.tipoDeCampanaRepository = tipoDeCampanaRepository;
        this.cadenaRepository = cadenaRepository;
        this.campanaCadenaRepository = campanaCadenaRepository;
    }

    @Transactional(readOnly = true)
    public List<CampanaResponseDTO> listar() {
        return campanaRepository.findAllConTipo()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CampanaRequestDTO buscarFormulario(Integer id) {
        Campana campana = campanaRepository.findById(id).orElseThrow();
        List<Integer> idsCadenas = campanaCadenaRepository.findIdsCadenasByCampanaId(id);

        return new CampanaRequestDTO(
                campana.getId(),
                campana.getTipoDeCampana().getId(),
                campana.getFecha(),
                convertirFechaEnteroAFormulario(campana.getFecha()),
                campana.getActivo(),
                idsCadenas
        );
    }

    @Transactional(readOnly = true)
    public List<TipoCampanaResponseDTO> listarTipos() {
        return tipoDeCampanaRepository.findAllByOrderByNombreAsc()
                .stream()
                .map(tipo -> new TipoCampanaResponseDTO(tipo.getId(), tipo.getNombre()))
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

    @Transactional
    public String guardar(CampanaRequestDTO campanaRequestDTO) {
        String error = validar(campanaRequestDTO);

        if (error != null) {
            return error;
        }

        Integer fecha = convertirFechaFormularioAEntero(campanaRequestDTO.getFechaFormulario());
        TipoDeCampana tipoDeCampana = tipoDeCampanaRepository.findById(campanaRequestDTO.getIdTipoCampana()).orElseThrow();

        Campana campana;

        if (campanaRequestDTO.getId() == null) {
            campana = new Campana();
            campana.setId(campanaRepository.findMaxId() + 1);
        } else {
            campana = campanaRepository.findById(campanaRequestDTO.getId()).orElseThrow();
        }

        campana.setTipoDeCampana(tipoDeCampana);
        campana.setFecha(fecha);
        campana.setActivo(campanaRequestDTO.getActivo() == null ? 0 : campanaRequestDTO.getActivo());

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

        Integer fecha = convertirFechaFormularioAEntero(campanaRequestDTO.getFechaFormulario());

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

        CampanaCadenaId campanaCadenaId = new CampanaCadenaId();
        campanaCadenaId.setIdCampana(campana.getId());
        campanaCadenaId.setIdCadena(cadena.getId());

        CampanaCadena campanaCadena = new CampanaCadena();
        campanaCadena.setId(campanaCadenaId);
        campanaCadena.setCampana(campana);
        campanaCadena.setCadena(cadena);

        campanaCadenaRepository.save(campanaCadena);
    }

    private CampanaResponseDTO convertirAResponseDTO(Campana campana) {
        String cadenas = campanaCadenaRepository.findByCampanaIdConCadena(campana.getId())
                .stream()
                .map(campanaCadena -> campanaCadena.getCadena().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin cadenas");

        return new CampanaResponseDTO(
                campana.getId(),
                campana.getTipoDeCampana().getNombre(),
                campana.getFecha(),
                convertirFechaEnteroATexto(campana.getFecha()),
                campana.getActivo() != null && campana.getActivo() == 1 ? "Activa" : "Inactiva",
                cadenas
        );
    }

    private Integer convertirFechaFormularioAEntero(String fechaFormulario) {
        try {
            LocalDate fecha = LocalDate.parse(fechaFormulario);
            return Integer.parseInt(fecha.format(DateTimeFormatter.BASIC_ISO_DATE));
        } catch (DateTimeParseException | NumberFormatException exception) {
            return null;
        }
    }

    private String convertirFechaEnteroAFormulario(Integer fechaEntero) {
        if (fechaEntero == null) {
            return "";
        }

        try {
            String fechaTexto = String.valueOf(fechaEntero);

            if (fechaTexto.length() != 8) {
                return "";
            }

            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.BASIC_ISO_DATE);
            return fecha.toString();
        } catch (DateTimeParseException exception) {
            return "";
        }
    }

    private String convertirFechaEnteroATexto(Integer fechaEntero) {
        if (fechaEntero == null) {
            return "";
        }

        try {
            String fechaTexto = String.valueOf(fechaEntero);

            if (fechaTexto.length() != 8) {
                return String.valueOf(fechaEntero);
            }

            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.BASIC_ISO_DATE);
            return fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException exception) {
            return String.valueOf(fechaEntero);
        }
    }
}