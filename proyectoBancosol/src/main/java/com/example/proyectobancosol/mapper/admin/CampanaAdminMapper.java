package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.CampanaRequestDTO;
import com.example.proyectobancosol.dto.response.CampanaResponseDTO;
import com.example.proyectobancosol.dto.response.TipoCampanaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Campana;
import com.example.proyectobancosol.entity.CampanaCadena;
import com.example.proyectobancosol.entity.CampanaCadenaId;
import com.example.proyectobancosol.entity.TipoDeCampana;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class CampanaAdminMapper extends MapperDTO<CampanaResponseDTO, Campana> {

    @Override
    public CampanaResponseDTO toDTO(Campana campana) {
        return toDTO(campana, null);
    }

    public CampanaResponseDTO toDTO(Campana campana, String cadenasParticipantes) {
        return new CampanaResponseDTO(
                campana.getId(),
                campana.getTipoDeCampana().getNombre(),
                campana.getFecha(),
                convertirFechaEnteroATexto(campana.getFecha()),
                campana.getActivo() != null && campana.getActivo() == 1 ? "Activa" : "Inactiva",
                cadenasParticipantes
        );
    }

    public CampanaRequestDTO toRequestDTO(Campana campana, List<Integer> idsCadenas) {
        return new CampanaRequestDTO(
                campana.getId(),
                campana.getTipoDeCampana().getId(),
                campana.getFecha(),
                convertirFechaEnteroAFormulario(campana.getFecha()),
                campana.getActivo(),
                idsCadenas
        );
    }

    public TipoCampanaResponseDTO toTipoCampanaResponseDTO(TipoDeCampana tipoDeCampana) {
        return new TipoCampanaResponseDTO(tipoDeCampana.getId(), tipoDeCampana.getNombre());
    }

    public void aplicarRequest(CampanaRequestDTO request, Campana campana, TipoDeCampana tipoDeCampana, Integer fecha) {
        campana.setTipoDeCampana(tipoDeCampana);
        campana.setFecha(fecha);
        campana.setActivo(request.getActivo() == null ? 0 : request.getActivo());
    }

    public CampanaCadena toCampanaCadena(Campana campana, Cadena cadena) {
        CampanaCadenaId id = new CampanaCadenaId();
        id.setIdCampana(campana.getId());
        id.setIdCadena(cadena.getId());

        CampanaCadena campanaCadena = new CampanaCadena();
        campanaCadena.setId(id);
        campanaCadena.setCampana(campana);
        campanaCadena.setCadena(cadena);
        return campanaCadena;
    }

    public Integer convertirFechaFormularioAEntero(String fechaFormulario) {
        if (fechaFormulario == null || fechaFormulario.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(LocalDate.parse(fechaFormulario).format(DateTimeFormatter.BASIC_ISO_DATE));
        } catch (DateTimeParseException | NumberFormatException exception) {
            return null;
        }
    }

    private String convertirFechaEnteroAFormulario(Integer fechaEntero) {
        return convertirFecha(fechaEntero, DateTimeFormatter.ISO_LOCAL_DATE, "");
    }

    private String convertirFechaEnteroATexto(Integer fechaEntero) {
        return convertirFecha(fechaEntero, DateTimeFormatter.ofPattern("yyyy/MM/dd"), String.valueOf(fechaEntero));
    }

    private String convertirFecha(Integer fechaEntero, DateTimeFormatter salida, String error) {
        if (fechaEntero == null) {
            return "";
        }

        try {
            String fechaTexto = String.valueOf(fechaEntero);
            return fechaTexto.length() == 8
                    ? LocalDate.parse(fechaTexto, DateTimeFormatter.BASIC_ISO_DATE).format(salida)
                    : error;
        } catch (DateTimeParseException exception) {
            return error;
        }
    }
}