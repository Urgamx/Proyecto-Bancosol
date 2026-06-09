package com.example.proyectobancosol.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.ui.Model;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;



@Controller
public class AdminController {

    @GetMapping("/admin")
    public String index(@SessionAttribute("usuarioSesion") UsuarioSesionDTO usuarioSesionDTO,
                        Model model) {

        // NUEVO: pasamos datos del admin a la vista
        model.addAttribute("usuarioSesion", usuarioSesionDTO);

        return "admin/index";
    }
}