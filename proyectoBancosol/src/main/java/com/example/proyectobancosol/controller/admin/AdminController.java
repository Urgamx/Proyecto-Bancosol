package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.service.admin.AdminMenuService;
import com.example.proyectobancosol.service.admin.ValidacionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * controlador del panel de admin
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminMenuService adminMenuService;
    private final ValidacionAdminService validacionAdminService;

    @GetMapping({"", "/"})
    public String index(@SessionAttribute("usuarioSesion") UsuarioSesionDTO usuarioSesionDTO, Model model) {
        model.addAttribute("usuarioSesion", usuarioSesionDTO);
        model.addAttribute("menuAdmin", adminMenuService.obtenerMenuAdministrador());
        model.addAttribute("validaciones", validacionAdminService.listar());
        return "admin/index";
    }
}