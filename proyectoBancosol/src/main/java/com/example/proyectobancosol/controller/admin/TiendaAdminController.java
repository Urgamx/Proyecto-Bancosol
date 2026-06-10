package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.service.admin.TiendaAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/tiendas")
public class TiendaAdminController {

    private final TiendaAdminService tiendaAdminService;

    public TiendaAdminController(TiendaAdminService tiendaAdminService) {
        this.tiendaAdminService = tiendaAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("tiendas", tiendaAdminService.listar());
        return "admin/tiendas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        cargarFormulario(model, new TiendaRequestDTO(), "Crear");
        return "admin/tiendas/formulario";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        cargarFormulario(model, tiendaAdminService.buscarFormulario(id), "Editar");
        return "admin/tiendas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tienda") TiendaRequestDTO tiendaRequestDTO,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = tiendaAdminService.guardar(tiendaRequestDTO);

        if (error != null) {
            cargarFormulario(model, tiendaRequestDTO, tiendaRequestDTO.getId() == null ? "Crear" : "Editar");
            model.addAttribute("error", error);
            return "admin/tiendas/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Tienda guardada correctamente");
        return "redirect:/admin/tiendas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        String error = tiendaAdminService.eliminar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Tienda eliminada correctamente");
        }

        return "redirect:/admin/tiendas";
    }

    private void cargarFormulario(Model model, TiendaRequestDTO tiendaRequestDTO, String modo) {
        model.addAttribute("tienda", tiendaRequestDTO);
        model.addAttribute("modo", modo);
        model.addAttribute("cadenas", tiendaAdminService.listarCadenas());
    }
}