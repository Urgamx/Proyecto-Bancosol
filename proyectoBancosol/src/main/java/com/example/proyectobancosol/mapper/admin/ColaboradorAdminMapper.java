package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorAdminMapper extends MapperDTO<ColaboradorResponseDTO, Colaborador> {

    @Override
    public ColaboradorResponseDTO toDTO(Colaborador colaborador) {
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
                null,
                0L,
                0L
        );
    }

    public ColaboradorRequestDTO toRequestDTO(Colaborador colaborador) {
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

    public void aplicarRequest(ColaboradorRequestDTO colaboradorRequestDTO, Colaborador colaborador) {
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

    private String limpiar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        return valor.trim();
    }
}