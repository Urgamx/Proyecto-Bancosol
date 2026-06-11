package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.request.AsignacionColaboradoresRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionColaboradoresCoordinadorDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsignacionColaboradoresAdminMapper extends MapperDTO<AsignacionColaboradoresCoordinadorDTO, Usuario> {

    @Override
    public AsignacionColaboradoresCoordinadorDTO toDTO(Usuario coordinador) {
        return new AsignacionColaboradoresCoordinadorDTO(
                coordinador.getId(),
                coordinador.getNombreCompleto(),
                coordinador.getEmail(),
                null
        );
    }

    public AsignacionColaboradoresRequestDTO toRequestDTO(Integer idCoordinador, List<Integer> idsColaboradores) {
        return new AsignacionColaboradoresRequestDTO(idCoordinador, idsColaboradores);
    }

    public UsuarioColaborador toUsuarioColaborador(Usuario coordinador, Colaborador colaborador) {
        UsuarioColaboradorId usuarioColaboradorId = new UsuarioColaboradorId();
        usuarioColaboradorId.setIdUsuario(coordinador.getId());
        usuarioColaboradorId.setIdColaborador(colaborador.getId());

        UsuarioColaborador usuarioColaborador = new UsuarioColaborador();
        usuarioColaborador.setId(usuarioColaboradorId);
        usuarioColaborador.setUsuario(coordinador);
        usuarioColaborador.setColaborador(colaborador);

        return usuarioColaborador;
    }
}