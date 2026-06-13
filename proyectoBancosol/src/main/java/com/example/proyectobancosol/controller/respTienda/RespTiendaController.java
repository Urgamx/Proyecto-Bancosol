package com.example.proyectobancosol.controller.respTienda;

// Importamos los DTOs
import com.example.proyectobancosol.dto.response.AsignacionTurnoResponseDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.dto.response.VoluntarioResponseDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.service.respTienda.ResTiendaService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/resp-tienda")
public class RespTiendaController {

    private ResTiendaService resTiendaService;

    private UsuarioSesionDTO protegerUsuarioParaVista(Usuario user) {
        if (user == null) return null;
        UsuarioSesionDTO userDTO = new UsuarioSesionDTO();
        userDTO.setNombreCompleto(user.getNombreCompleto()); 
        userDTO.setIdUsuario(user.getId());
        return userDTO;
    }

    @GetMapping({"", "/"})
    public String home(@SessionAttribute(name = "usuario", required = false) Usuario user,
                       Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        List<TiendaResponseDTO> tiendas = resTiendaService.obtenerTiendasDelUsuario(user.getId());
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

        return "respTienda/index";
    }

    @GetMapping("/tienda")
    public String verTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                            @RequestParam("id") Integer idTienda,
                            Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        TiendaResponseDTO tienda = resTiendaService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurnoResponseDTO> turnos = resTiendaService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

        return "respTienda/tienda_detalles";
    }

    @GetMapping("/voluntarios")
    public String verVoluntariosDeTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                        @RequestParam("id") Integer idTienda,
                                        Model model) {
        if (user == null) {
            return "redirect:/login";
        }
        
        List<VoluntarioResponseDTO> voluntarios = resTiendaService.obtenerVoluntariosDeTienda(idTienda);
        model.addAttribute("voluntarios", voluntarios);

        TiendaResponseDTO tienda = resTiendaService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        model.addAttribute("usuario", user);
        
        return "respTienda/voluntarios"; 
    }

    @GetMapping("/detalles-voluntario")
    public String verDetallesVoluntario(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                        @RequestParam("id") Integer idVoluntario,
                                        @RequestParam(value = "tienda", required = false) Integer idTienda,
                                        Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        VoluntarioResponseDTO voluntario = resTiendaService.obtenerVoluntarioPorId(idVoluntario);
        model.addAttribute("voluntario", voluntario);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", protegerUsuarioParaVista(user));

        return "respTienda/detalles_voluntario";
    }
}