/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.controller.respEntidad;

import com.example.proyectobancosol.dto.response.AsignacionTurnoResponseDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.dto.response.VoluntarioResponseDTO;
import com.example.proyectobancosol.entity.Usuario;
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

    private UsuarioSesionDTO protegerUsuarioParaVista(Usuario user) {
        if (user == null) return null;
        UsuarioSesionDTO userDTO = new UsuarioSesionDTO();
        userDTO.setNombreCompleto(user.getNombreCompleto()); // ¡Cámbialo si tu DTO usa otro nombre!
        userDTO.setIdUsuario(user.getId());                 // ¡Cámbialo si tu DTO usa otro nombre!
        return userDTO;
    }

    @GetMapping({"", "/"})
    public String home(@SessionAttribute(name = "usuario", required = false) Usuario user,
                       Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        
        List<TiendaResponseDTO> tiendas = respEntidadService.obtenerTiendasDelUsuario(user.getId());
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

        return "respEntidad/index";
    }

    @GetMapping("/tienda")
    public String verTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                            @RequestParam("id") Integer idTienda,
                            Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        
        TiendaResponseDTO tienda = respEntidadService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurnoResponseDTO> turnos = respEntidadService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

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
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

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

        respEntidadService.registrarIncidencia(idAsignacion, descripcion);

        return "redirect:/resp-entidad/tienda?id=" + idTienda;
    }

    @GetMapping("/voluntarios")
    public String listarVoluntarios(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                    @RequestParam("id") Integer idTienda,
                                    Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        
        TiendaResponseDTO tienda = respEntidadService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);
        
        List<AsignacionTurnoResponseDTO> turnos = respEntidadService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

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
        
        VoluntarioResponseDTO voluntario = respEntidadService.obtenerVoluntarioPorId(idVoluntario);
        model.addAttribute("voluntario", voluntario);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

        return "respEntidad/detalles_voluntario";
    }
}