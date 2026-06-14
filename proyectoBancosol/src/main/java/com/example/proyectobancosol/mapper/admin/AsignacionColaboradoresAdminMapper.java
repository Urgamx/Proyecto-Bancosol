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
/**
 * Transforma las asignaciones de colaboradores entre entidad y DTO
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * -IA: 25%
 */

@Component
public class AsignacionColaboradoresAdminMapper extends MapperDTO<AsignacionColaboradoresCoordinadorDTO, Usuario> {

    @Override
    public AsignacionColaboradoresCoordinadorDTO toDTO(Usuario coordinador) {
        return toDTO(coordinador, null);
    }

    public AsignacionColaboradoresCoordinadorDTO toDTO(Usuario coordinador, String colaboradoresAsignados) {
        return new AsignacionColaboradoresCoordinadorDTO(
                coordinador.getId(),
                coordinador.getNombreCompleto(),
                coordinador.getEmail(),
                colaboradoresAsignados
        );
    }

    public AsignacionColaboradoresRequestDTO toRequestDTO(Integer idCoordinador, List<Integer> idsColaboradores) {
        return new AsignacionColaboradoresRequestDTO(idCoordinador, idsColaboradores);
    }

    public UsuarioColaborador toUsuarioColaborador(Usuario coordinador, Colaborador colaborador) {
        UsuarioColaboradorId id = new UsuarioColaboradorId();
        id.setIdUsuario(coordinador.getId());
        id.setIdColaborador(colaborador.getId());

        UsuarioColaborador usuarioColaborador = new UsuarioColaborador();
        usuarioColaborador.setId(id);
        usuarioColaborador.setUsuario(coordinador);
        usuarioColaborador.setColaborador(colaborador);
        return usuarioColaborador;
    }
}