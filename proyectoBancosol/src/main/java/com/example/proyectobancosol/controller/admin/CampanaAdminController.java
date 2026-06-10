package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CampanaRequestDTO;
import com.example.proyectobancosol.service.admin.CampanaAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/campanas")
public class CampanaAdminController {

    private final CampanaAdminService campanaAdminService;

    public CampanaAdminController(CampanaAdminService campanaAdminService) {
        this.campanaAdminService = campanaAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("campanas", campanaAdminService.listar());
        return "admin/campanas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        cargarFormulario(model, new CampanaRequestDTO(), "Crear");
        return "admin/campanas/formulario";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        cargarFormulario(model, campanaAdminService.buscarFormulario(id), "Editar");
        return "admin/campanas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("campana") CampanaRequestDTO campanaRequestDTO,
                          @RequestParam(value = "idsCadenas", required = false) List<Integer> idsCadenas,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        campanaRequestDTO.setIdsCadenas(idsCadenas);

        String error = campanaAdminService.guardar(campanaRequestDTO);

        if (error != null) {
            cargarFormulario(model, campanaRequestDTO, campanaRequestDTO.getId() == null ? "Crear" : "Editar");
            model.addAttribute("error", error);
            return "admin/campanas/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Campana guardada correctamente");
        return "redirect:/admin/campanas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        String error = campanaAdminService.eliminar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Campana eliminada correctamente");
        }

        return "redirect:/admin/campanas";
    }

    private void cargarFormulario(Model model, CampanaRequestDTO campanaRequestDTO, String modo) {
        model.addAttribute("campana", campanaRequestDTO);
        model.addAttribute("modo", modo);
        model.addAttribute("tipos", campanaAdminService.listarTipos());
        model.addAttribute("cadenas", campanaAdminService.listarCadenas());
    }
}