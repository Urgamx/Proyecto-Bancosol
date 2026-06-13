package com.example.proyectobancosol.mapper.capitan;

import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class TiendaCapitanMapper extends MapperDTO<TiendaResponseDTO, Tienda> {
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
}
