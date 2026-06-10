package com.example.proyectobancosol.controller.capitan;

import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.service.capitan.CapitanService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/capitan")
public class CapitanController {

    private CapitanService capitanService;


    @GetMapping({"", "/"})
    public String home(@SessionAttribute(name = "usuario", required = false) Usuario user,
                       Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        List<Tienda> tiendas = capitanService.obtenerTiendasDelUsuario(user.getId());
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("usuario", user);

        return "capitan/index";
    }

    @GetMapping("/tienda")
    public String verTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                            @RequestParam("id") Integer idTienda,
                            Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        Tienda tienda = capitanService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurno> turnos = capitanService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", user);

        return "capitan/tienda_detalles";
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

        return "capitan/registrar_incidencia";
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

        capitanService.registrarIncidencia(idAsignacion, descripcion);

        return "redirect:/capitan/tienda?id=" + idTienda;
    }

    @GetMapping("/voluntarios")
    public String listarVoluntarios(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                    @RequestParam("id") Integer idTienda,
                                    Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        Tienda tienda = capitanService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurno> turnos = capitanService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", user);

        return "capitan/voluntarios";
    }

    @GetMapping("/detalles-voluntario")
    public String verDetallesVoluntario(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                        @RequestParam("id") Integer idVoluntario,
                                        @RequestParam(value = "tienda", required = false) Integer idTienda,
                                        Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        Voluntario voluntario = capitanService.obtenerVoluntarioPorId(idVoluntario);
        model.addAttribute("voluntario", voluntario);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", user);

        return "capitan/detalles_voluntario";
    }

}