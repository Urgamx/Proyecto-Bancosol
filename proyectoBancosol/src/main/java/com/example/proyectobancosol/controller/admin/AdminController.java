package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.service.admin.AdminMenuService;
import com.example.proyectobancosol.service.admin.ValidacionAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminMenuService adminMenuService;
    private final ValidacionAdminService validacionAdminService;

    public AdminController(AdminMenuService adminMenuService,
                           ValidacionAdminService validacionAdminService) {
        this.adminMenuService = adminMenuService;
        this.validacionAdminService = validacionAdminService;
    }

    @GetMapping({"", "/"})
    public String index(@SessionAttribute("usuarioSesion") UsuarioSesionDTO usuarioSesionDTO,
                        Model model) {

        model.addAttribute("usuarioSesion", usuarioSesionDTO);
        model.addAttribute("menuAdmin", adminMenuService.obtenerMenuAdministrador());
        model.addAttribute("validaciones", validacionAdminService.listar());

        return "admin/index";
    }
}