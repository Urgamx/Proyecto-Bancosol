package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CampanaRequestDTO;
import com.example.proyectobancosol.service.admin.CampanaAdminService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/admin/campanas")
public class CampanaAdminController {

    private final CampanaAdminService campanaAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("campanas", campanaAdminService.listar());
        return "admin/campanas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        return formulario(model, new CampanaRequestDTO(), "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, campanaAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("campana") CampanaRequestDTO request,
                          @RequestParam(value = "idsCadenas", required = false) List<Integer> idsCadenas,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        request.setIdsCadenas(idsCadenas);
        String error = campanaAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Campana guardada correctamente");
        return "redirect:/admin/campanas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = campanaAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Campana eliminada correctamente" : error);
        return "redirect:/admin/campanas";
    }

    private String formulario(Model model, CampanaRequestDTO request, String modo) {
        model.addAttribute("campana", request);
        model.addAttribute("modo", modo);
        model.addAttribute("tipos", campanaAdminService.listarTipos());
        model.addAttribute("cadenas", campanaAdminService.listarCadenas());
        return "admin/campanas/formulario";
    }
}