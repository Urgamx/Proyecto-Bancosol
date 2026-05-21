package com.example.proyectobancosol.service.capitan;

import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.entity.Tienda;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TiendaService {

    private TiendaRepository tiendaRepository;

    public Tienda findTiendaById(@Param("id") Integer id){
        return tiendaRepository.findById(id).get();
    }

    public List<Tienda> ListarTiendas(){
        return this.ListarTiendas(null);
    }

    public List<Tienda> ListarTiendas(Integer idUsuario){
        List<Tienda> tiendas;

        if(idUsuario == null){
            tiendas = this.tiendaRepository.findAll();
        }else{
            tiendas = this.tiendaRepository.findTiendasByUsuarioId(idUsuario);
        }

        return tiendas;
    }




}
