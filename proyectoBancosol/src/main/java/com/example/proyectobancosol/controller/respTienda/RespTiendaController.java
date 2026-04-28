package com.example.proyectobancosol.controller.respTienda; // <-- Paquete actualizado

import com.example.proyectobancosol.service.respTienda.TiendaService;
import com.example.proyectobancosol.service.respTienda.VoluntarioService;
import com.example.proyectobancosol.service.respTienda.AsignacionTurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/responsable") // Ruta base en el navegador
public class RespTiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private AsignacionTurnosService asignacionService;

    // URL: http://localhost:8080/responsable/tiendas
    @GetMapping("/tiendas")
    public String listarTiendas(Model model) {
        model.addAttribute("lista", tiendaService.obtenerTodasLasTiendas());
        return "respTienda/tiendas"; // Busca en WEB-INF/view/respTienda/tiendas.jsp
    }

    // URL: http://localhost:8080/responsable/voluntarios
    @GetMapping("/voluntarios")
    public String listarVoluntarios(Model model) {
        model.addAttribute("voluntarios", voluntarioService.obtenerTodosLosVoluntarios());
        return "respTienda/voluntarios"; // Busca en WEB-INF/view/respTienda/voluntarios.jsp
    }

    // URL: http://localhost:8080/responsable/turnos
    @GetMapping("/turnos")
    public String listarTurnos(Model model) {
        model.addAttribute("turnos", asignacionService.obtenerTodasLasAsignaciones());
        return "respTienda/turnos"; // Busca en WEB-INF/view/respTienda/turnos.jsp
    }
}