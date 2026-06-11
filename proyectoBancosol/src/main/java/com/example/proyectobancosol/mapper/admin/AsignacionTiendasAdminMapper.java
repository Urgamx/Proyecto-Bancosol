package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.AsignacionTiendasRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionTiendasCoordinadorDTO;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioTienda;
import com.example.proyectobancosol.entity.UsuarioTiendaId;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsignacionTiendasAdminMapper extends MapperDTO<AsignacionTiendasCoordinadorDTO, Usuario> {

    @Override
    public AsignacionTiendasCoordinadorDTO toDTO(Usuario coordinador) {
        return new AsignacionTiendasCoordinadorDTO(
                coordinador.getId(),
                coordinador.getNombreCompleto(),
                coordinador.getEmail(),
                null
        );
    }

    public AsignacionTiendasRequestDTO toRequestDTO(Integer idCoordinador, List<Integer> idsTiendas) {
        return new AsignacionTiendasRequestDTO(idCoordinador, idsTiendas);
    }

    public UsuarioTienda toUsuarioTienda(Usuario coordinador, Tienda tienda) {
        UsuarioTiendaId usuarioTiendaId = new UsuarioTiendaId();
        usuarioTiendaId.setIdUsuario(coordinador.getId());
        usuarioTiendaId.setIdTienda(tienda.getId());

        UsuarioTienda usuarioTienda = new UsuarioTienda();
        usuarioTienda.setId(usuarioTiendaId);
        usuarioTienda.setUsuario(coordinador);
        usuarioTienda.setTienda(tienda);

        return usuarioTienda;
    }
}