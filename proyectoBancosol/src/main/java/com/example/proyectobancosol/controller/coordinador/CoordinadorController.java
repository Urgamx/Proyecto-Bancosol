package com.example.proyectobancosol.controller.coordinador;

import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.service.coordinador.ColaboradorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    @Autowired
    ColaboradorService colaboradorService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = "usuario",required = false) Usuario user,
                       HttpSession session){
        if (user == null){
            return "redirect:/login";
        } else {
            return "coordinador/home";
        }
    }

    @GetMapping("/colaborador")
    public String listar(Model model, HttpSession session) {

        List<Colaborador> lista = this.colaboradorService.findAll();
        model.addAttribute("lista",lista);
        return "coordinador/colaborador";
    }

}
