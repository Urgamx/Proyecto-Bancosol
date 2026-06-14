package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CoordinadorRequestDTO;
import com.example.proyectobancosol.service.admin.CoordinadorAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * controlador del panel de coordinadores
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coordinadores")
public class CoordinadorAdminController {

    private final CoordinadorAdminService coordinadorAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", coordinadorAdminService.listar());
        return "admin/coordinadores/listado";
    }

    @PostMapping("/filtrar")
    public String filtrar(@RequestParam("nombre") String nombre,
                          @RequestParam(value = "activo", required = false) Integer activo,
                          Model model) {
        model.addAttribute("coordinadores", coordinadorAdminService.filtrar(nombre, activo));
        model.addAttribute("nombreSelected", nombre);
        model.addAttribute("activoSelected", activo);
        return "admin/coordinadores/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        CoordinadorRequestDTO request = new CoordinadorRequestDTO();
        request.setActivo(1);
        return formulario(model, request, "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, coordinadorAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("coordinador") CoordinadorRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = coordinadorAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Coordinador guardado correctamente");
        return "redirect:/admin/coordinadores";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = coordinadorAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Coordinador eliminado correctamente" : error);
        return "redirect:/admin/coordinadores";
    }

    private String formulario(Model model, CoordinadorRequestDTO request, String modo) {
        model.addAttribute("coordinador", request);
        model.addAttribute("modo", modo);
        return "admin/coordinadores/formulario";
    }
}