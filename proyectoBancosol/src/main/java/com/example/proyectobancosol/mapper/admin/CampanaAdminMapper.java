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
        return new CampanaResponseDTO(
                campana.getId(),
                campana.getTipoDeCampana().getNombre(),
                campana.getFecha(),
                convertirFechaEnteroATexto(campana.getFecha()),
                campana.getActivo() != null && campana.getActivo() == 1 ? "Activa" : "Inactiva",
                null
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
        return new TipoCampanaResponseDTO(
                tipoDeCampana.getId(),
                tipoDeCampana.getNombre()
        );
    }

    public void aplicarRequest(CampanaRequestDTO campanaRequestDTO, Campana campana, TipoDeCampana tipoDeCampana, Integer fecha) {
        campana.setTipoDeCampana(tipoDeCampana);
        campana.setFecha(fecha);
        campana.setActivo(campanaRequestDTO.getActivo() == null ? 0 : campanaRequestDTO.getActivo());
    }

    public CampanaCadena toCampanaCadena(Campana campana, Cadena cadena) {
        CampanaCadenaId campanaCadenaId = new CampanaCadenaId();
        campanaCadenaId.setIdCampana(campana.getId());
        campanaCadenaId.setIdCadena(cadena.getId());

        CampanaCadena campanaCadena = new CampanaCadena();
        campanaCadena.setId(campanaCadenaId);
        campanaCadena.setCampana(campana);
        campanaCadena.setCadena(cadena);

        return campanaCadena;
    }

    public Integer convertirFechaFormularioAEntero(String fechaFormulario) {
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