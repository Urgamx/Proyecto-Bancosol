package com.example.proyectobancosol.service.capitan;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.entity.AsignacionTurno;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionTurnoService {

    private final AsignacionTurnoRepository asignacionTurnoRepository;

    public List<AsignacionTurno> ListarAsignacionTurnos(){
        return this.ListarAsignacionTurnos(null);
    }

    public List<AsignacionTurno> ListarAsignacionTurnos(Integer idTienda){
        List<AsignacionTurno> turnos;

        if(idTienda == null){
            turnos = this.asignacionTurnoRepository.findAll();
        }else{
            turnos= this.asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda);
        }

        return turnos;
    }

    public AsignacionTurno findById(Integer id) { return this.asignacionTurnoRepository.findById(id).get();}

    public void save(AsignacionTurno au) { this.asignacionTurnoRepository.save(au); }

}
