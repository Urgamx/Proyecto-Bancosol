package com.example.proyectobancosol.controller.coordinador;

import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.service.coordinador.ColaboradorService;
import com.example.proyectobancosol.service.coordinador.UsuarioColaboradorService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final ColaboradorService colaboradorService;
    private final UsuarioColaboradorService usuarioColaboradorService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = "usuario",required = false) Usuario user,
                       HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        return "coordinador/homeCoordinador";

    }

    @GetMapping("/colaborador")
    public String listarColaboradores(@SessionAttribute(name = "usuario",required = false) Usuario user,
                         Model model, HttpSession session)
    {

        if (user == null){
            return "redirect:/login";
        }

        List<Colaborador> colaboradores = this.colaboradorService.findAllActivos();
        List<UsuarioColaborador> usuarios = this.usuarioColaboradorService.findAll();
        model.addAttribute("colaboradores",colaboradores);
        model.addAttribute("usuarios", usuarios);
        return "coordinador/colaborador";
    }

    @GetMapping("/editarColaborador")
    public String editarColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                    @RequestParam("id") Integer id,
                                    Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        Colaborador colaborador = this.colaboradorService.findById(id);
        model.addAttribute("colaborador",colaborador);
        return "coordinador/editarColaborador";
    }

    @PostMapping("/guardarColaborador")
    public String guardarColaborador(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                     @RequestParam("id") Integer id,
                                     @RequestParam("contactoNom") String nombre,
                                     @RequestParam("contactoTlf") String tlf,
                                     @RequestParam("email") String email,
                                     Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        Colaborador colaborador = this.colaboradorService.findById(id);

        colaborador.setEmail(email);
        colaborador.setContactoNom(nombre);
        colaborador.setContactoTlf(tlf);
        colaborador.setEstado(2);

        this.colaboradorService.save(colaborador);

        return "redirect:/coordinador/colaborador";
    }

    @GetMapping("/asignacionVoluntarios")
    public String listarTurnosVoluntarios(@SessionAttribute(name = "usuario",required = false) Usuario user,
                                          Model model, HttpSession session)
    {
        if (user == null){
            return "redirect:/login";
        }

        return "coordinador/asignacionVoluntarios";
    }

}
