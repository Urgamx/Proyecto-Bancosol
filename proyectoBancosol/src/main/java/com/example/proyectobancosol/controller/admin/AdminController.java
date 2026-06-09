package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.service.admin.AdminMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminMenuService adminMenuService;

    public AdminController(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    @GetMapping({"", "/"})
    public String index(@SessionAttribute("usuarioSesion") UsuarioSesionDTO usuarioSesionDTO,
                        Model model) {

        model.addAttribute("usuarioSesion", usuarioSesionDTO);
        model.addAttribute("menuAdmin", adminMenuService.obtenerMenuAdministrador());

        return "admin/index";
    }
}