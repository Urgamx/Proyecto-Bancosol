package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Cadena;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class TiendaAdminMapper extends MapperDTO<TiendaResponseDTO, Tienda> {

    @Override
    public TiendaResponseDTO toDTO(Tienda tienda) {
        return new TiendaResponseDTO(
                tienda.getId(),
                tienda.getIdCadena().getNombre(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getLocalidad(),
                tienda.getCodPostal()
        );
    }

    public TiendaRequestDTO toRequestDTO(Tienda tienda) {
        return new TiendaRequestDTO(
                tienda.getId(),
                tienda.getIdCadena().getId(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getLocalidad(),
                tienda.getCodPostal()
        );
    }

    public void aplicarRequest(TiendaRequestDTO request, Tienda tienda, Cadena cadena) {
        tienda.setIdCadena(cadena);
        tienda.setNombre(request.getNombre().trim());
        tienda.setDireccion(request.getDireccion().trim());
        tienda.setLocalidad(request.getLocalidad().trim());
        tienda.setCodPostal(request.getCodPostal().trim());
    }
}