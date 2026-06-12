package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.service.admin.CadenaAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cadenas")
public class CadenaAdminController {

    private final CadenaAdminService cadenaAdminService;

    public CadenaAdminController(CadenaAdminService cadenaAdminService) {
        this.cadenaAdminService = cadenaAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
                         Model model) {
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("cadenas", cadenaAdminService.listar());
        return "admin/cadenas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
                        Model model) {
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("cadena", new CadenaRequestDTO());
        model.addAttribute("modo", "Crear");
        return "admin/cadenas/formulario";
    }

    @GetMapping("/editar")
    public String editar(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
                         @RequestParam("id") Integer id,
                         Model model) {
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("cadena", cadenaAdminService.buscarFormulario(id));
        model.addAttribute("modo", "Editar");
        return "admin/cadenas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
                          @ModelAttribute("cadena") CadenaRequestDTO cadenaRequestDTO,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (usuario == null) {
            return "redirect:/login";
        }

        String error = cadenaAdminService.guardar(cadenaRequestDTO);

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("cadena", cadenaRequestDTO);
            model.addAttribute("modo", cadenaRequestDTO.getId() == null ? "Crear" : "Editar");
            return "admin/cadenas/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Cadena guardada correctamente");
        return "redirect:/admin/cadenas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
                           @RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        if (usuario == null) {
            return "redirect:/login";
        }

        String error = cadenaAdminService.eliminar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Cadena eliminada correctamente");
        }

        return "redirect:/admin/cadenas";
    }
}