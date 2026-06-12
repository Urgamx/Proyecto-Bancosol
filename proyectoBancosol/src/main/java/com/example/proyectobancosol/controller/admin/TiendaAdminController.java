package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.service.admin.TiendaAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/tiendas")
public class TiendaAdminController {

    private final TiendaAdminService tiendaAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("tiendas", tiendaAdminService.listar());
        return "admin/tiendas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        return formulario(model, new TiendaRequestDTO(), "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, tiendaAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tienda") TiendaRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = tiendaAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Tienda guardada correctamente");
        return "redirect:/admin/tiendas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = tiendaAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Tienda eliminada correctamente" : error);
        return "redirect:/admin/tiendas";
    }

    private String formulario(Model model, TiendaRequestDTO request, String modo) {
        model.addAttribute("tienda", request);
        model.addAttribute("modo", modo);
        model.addAttribute("cadenas", tiendaAdminService.listarCadenas());
        return "admin/tiendas/formulario";
    }
}