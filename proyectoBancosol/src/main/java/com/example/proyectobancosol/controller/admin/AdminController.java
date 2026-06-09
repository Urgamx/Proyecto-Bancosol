package com.example.proyectobancosol.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"", "/"})
    public String index(@SessionAttribute("usuarioSesion") UsuarioSesionDTO usuarioSesionDTO,
                        Model model) {

        // NUEVO: enviamos los datos del usuario a la vista
        model.addAttribute("usuarioSesion", usuarioSesionDTO);

        return "admin/index";
    }
}