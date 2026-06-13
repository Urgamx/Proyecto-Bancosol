package com.example.proyectobancosol.controller.respTienda;

import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.Voluntario;
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

    
    @GetMapping({"", "/"})
    public String home(@SessionAttribute(name = "usuario", required = false) Usuario user,
                       Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        List<Tienda> tiendas = resTiendaService.obtenerTiendasDelUsuario(user.getId());
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("usuario", user);

        return "respTienda/index";
    }

    @GetMapping("/tienda")
    public String verTienda(@SessionAttribute(name = "usuario", required = false) Usuario user,
                            @RequestParam("id") Integer idTienda,
                            Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        Tienda tienda = resTiendaService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurno> turnos = resTiendaService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
        model.addAttribute("usuario", user);

        return "respTienda/tienda_detalles";
    }


    @GetMapping("/voluntarios")
    public String listarVoluntarios(@SessionAttribute(name = "usuario", required = false) Usuario user,
                                    @RequestParam("id") Integer idTienda,
                                    Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/login";
        }

        Tienda tienda = resTiendaService.obtenerTiendaPorId(idTienda);
        model.addAttribute("tienda", tienda);

        List<AsignacionTurno> turnos = resTiendaService.obtenerVoluntariosPorTienda(idTienda);
        model.addAttribute("turnos", turnos);
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

        Voluntario voluntario = resTiendaService.obtenerVoluntarioPorId(idVoluntario);
        model.addAttribute("voluntario", voluntario);
        model.addAttribute("idTienda", idTienda);
        model.addAttribute("usuario", user);

        return "respTienda/detalles_voluntario";
    }

}
