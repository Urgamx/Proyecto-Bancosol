package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.service.admin.ValidacionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controlador del listado de validaciones
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/validaciones")
public class ValidacionAdminController {

    private final ValidacionAdminService validacionAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("validaciones", validacionAdminService.listar());
        return "admin/validaciones/listado";
    }
}