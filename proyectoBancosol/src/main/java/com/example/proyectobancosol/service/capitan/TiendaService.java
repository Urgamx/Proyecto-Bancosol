package com.example.proyectobancosol.service.capitan;

import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.mapper.admin.TiendaAdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que representa la clase TiendaService.
 *
 * Autores:
 * - David Vilaseca Pareja: 50%
 * - Carlos Sánchez: 40%
 * - IA Generativa: 10%
 */

@Service
@AllArgsConstructor
public class TiendaService {

    private final TiendaRepository tiendaRepository;
    private final TiendaAdminMapper tiendaAdminMapper;

    public TiendaRequestDTO findTiendaById(@Param("id") Integer id){
        return this.tiendaAdminMapper.toRequestDTO(tiendaRepository.findById(id).get());
    }

    public List<TiendaResponseDTO> ListarTiendas(){
        return this.ListarTiendas(null);
    }

    public List<TiendaResponseDTO> ListarTiendas(Integer idUsuario){
        List<TiendaResponseDTO> tiendas;

        if(idUsuario == null){
            tiendas = tiendaAdminMapper.toDTOList(this.tiendaRepository.findAll());
        }else{
            tiendas = tiendaAdminMapper.toDTOList(this.tiendaRepository.findTiendasByUsuarioId(idUsuario));
        }

        return tiendas;
    }




}
