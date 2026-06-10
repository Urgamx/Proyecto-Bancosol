package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.service.admin.CoordinadorAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/coordinadores")
public class CoordinadorAdminController {

    private final CoordinadorAdminService coordinadorAdminService;

    public CoordinadorAdminController(CoordinadorAdminService coordinadorAdminService) {
        this.coordinadorAdminService = coordinadorAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", coordinadorAdminService.listar());
        return "admin/coordinadores/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        CoordinadorRequestDTO coordinadorRequestDTO = new CoordinadorRequestDTO();
        coordinadorRequestDTO.setActivo(1);

        model.addAttribute("coordinador", coordinadorRequestDTO);
        model.addAttribute("modo", "Crear");

        return "admin/coordinadores/formulario";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("coordinador", coordinadorAdminService.buscarFormulario(id));
        model.addAttribute("modo", "Editar");

        return "admin/coordinadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("coordinador") CoordinadorRequestDTO coordinadorRequestDTO,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = coordinadorAdminService.guardar(coordinadorRequestDTO);

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("coordinador", coordinadorRequestDTO);
            model.addAttribute("modo", coordinadorRequestDTO.getId() == null ? "Crear" : "Editar");
            return "admin/coordinadores/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Coordinador guardado correctamente");
        return "redirect:/admin/coordinadores";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        String error = coordinadorAdminService.eliminar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Coordinador eliminado correctamente");
        }

        return "redirect:/admin/coordinadores";
    }
}