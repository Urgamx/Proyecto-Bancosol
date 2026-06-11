package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.service.admin.ValidacionAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/validaciones")
public class ValidacionAdminController {

    private final ValidacionAdminService validacionAdminService;

    public ValidacionAdminController(ValidacionAdminService validacionAdminService) {
        this.validacionAdminService = validacionAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("validaciones", validacionAdminService.listar());
        return "admin/validaciones/listado";
    }
}