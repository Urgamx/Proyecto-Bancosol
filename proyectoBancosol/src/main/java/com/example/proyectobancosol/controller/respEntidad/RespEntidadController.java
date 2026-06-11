package com.example.proyectobancosol.controller.respEntidad;

import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.service.respEntidad.RespEntidadService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/resp-entidad")
public class RespEntidadController {

    private RespEntidadService respEntidadService;

    
    @GetMapping({"", "/"})
    public String home(@SessionAttribute(name = "usuario", required = false) Usuario user,
                       Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        // Obtener las tiendas asignadas al usuario (generalmente solo 1 para responsable tienda)
        List<Tienda> tiendas = respEntidadService.obtenerTiendasDelUsuario(user.getId());
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("usuario", user);

        return "respEntidad/index";
    }

    @GetMapping("/tienda")
    public String verTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                            @RequestParam("id") Integer idTienda,
                            Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        // Obtener tienda
        Tienda tienda = respEntidadService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        // Obtener voluntarios asignados (turnos)
        List<AsignacionTurno> turnos = respEntidadService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", user);

        return "respEntidad/tienda_detalles";
    }

    @GetMapping("/registrar-incidencia")
    public String mostrarFormularioIncidencia(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                              @RequestParam("id") Integer idAsignacion,
                                              @RequestParam("tienda") Integer idTienda,
                                              Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("idAsignacion", idAsignacion);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", user);

        return "respEntidad/registrar_incidencia";
    }

    @PostMapping("/registrarIncidencia")
    public String registrarIncidencia(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                      @RequestParam("idAsignacion") Integer idAsignacion,
                                      @RequestParam("idTienda") Integer idTienda,
                                      @RequestParam("descripcion") String descripcion,
                                      Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        // Registrar incidencia a través del servicio
        respEntidadService.registrarIncidencia(idAsignacion, descripcion);

        // Redirigir a la tienda
        return "redirect:/resp-entidad/tienda?id=" + idTienda;
    }

    @GetMapping("/voluntarios")
    public String listarVoluntarios(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                    @RequestParam("id") Integer idTienda,
                                    Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        // Obtener tienda
        Tienda tienda = respEntidadService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        // Obtener turnos/voluntarios asignados
        List<AsignacionTurno> turnos = respEntidadService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", user);

        return "respEntidad/voluntarios";
    }

    @GetMapping("/detalles-voluntario")
    public String verDetallesVoluntario(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                        @RequestParam("id") Integer idVoluntario,
                                        @RequestParam(value = "tienda", required = false) Integer idTienda,
                                        Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        // Obtener voluntario por ID
        Voluntario voluntario = respEntidadService.obtenerVoluntarioPorId(idVoluntario);
        model.addAttribute("voluntario", voluntario);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", user);

        return "respEntidad/detalles_voluntario";
    }

}
