package com.example.proyectobancosol.controller.capitan;


import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.service.capitan.AsignacionTurnoService;
import com.example.proyectobancosol.service.capitan.TiendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/capitan")
public class CapitanController {

    private TiendaService tiendaService;
    private AsignacionTurnoService asignacionTurnoService;


    @GetMapping({"","/"})
    public String doInit (Model model) {

        List<Tienda> tiendas = tiendaService.ListarTiendas();

        model.addAttribute("tiendas", tiendas);


        return "capitan/tiendas_asignadas";
    }

    @GetMapping("/asignacion_turnos")
    public String turnosTiendas(@RequestParam("id") Integer idTienda, Model model) {

        Tienda tienda = tiendaService.findTiendaById(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurno> turnos = asignacionTurnoService.ListarAsignacionTurnos(idTienda);
        model.addAttribute("turnos", turnos);

        return "capitan/asignacion_turnos";
    }

}
