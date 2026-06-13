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
        return toDTO(colaborador, null, 0L, 0L);
    }

    public ColaboradorResponseDTO toDTO(Colaborador colaborador, String coordinadores, Long voluntarios, Long turnos) {
        return new ColaboradorResponseDTO(
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
                estadoTexto(colaborador.getEstado()),
                coordinadores,
                voluntarios,
                turnos
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

    public void aplicarRequest(ColaboradorRequestDTO request, Colaborador colaborador) {
        colaborador.setNombreEntidad(request.getNombreEntidad().trim());
        colaborador.setEmail(request.getEmail().trim());
        colaborador.setContactoNom(limpiar(request.getContactoNom()));
        colaborador.setContactoTlf(limpiar(request.getContactoTlf()));
        colaborador.setDomicilio(limpiar(request.getDomicilio()));
        colaborador.setLocalidad(limpiar(request.getLocalidad()));
        colaborador.setZonaGeografica(limpiar(request.getZonaGeografica()));
        colaborador.setObservaciones(limpiar(request.getObservaciones()));
        colaborador.setCodPostal(limpiar(request.getCodPostal()));
        colaborador.setEstado(request.getEstado() == null ? 2 : request.getEstado());
    }

    private String estadoTexto(Integer estado) {
        if (Integer.valueOf(1).equals(estado)) {
            return "Activo";
        }

        return Integer.valueOf(2).equals(estado) ? "Pendiente" : "Inactivo";
    }

    private String limpiar(String valor) {
        return valor == null || valor.trim().isEmpty() ? null : valor.trim();
    }
}