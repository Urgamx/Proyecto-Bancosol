package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.CadenaRequestDTO;
import com.example.proyectobancosol.service.admin.CadenaAdminService;
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
@RequestMapping("/admin/cadenas")
public class CadenaAdminController {

    private final CadenaAdminService cadenaAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("cadenas", cadenaAdminService.listar());
        return "admin/cadenas/listado";
    }

    @PostMapping("/filtrar")
    public String filtrar(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("cadenas", cadenaAdminService.filtrar(nombre));
        model.addAttribute("nombreSelected", nombre);
        return "admin/cadenas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        return formulario(model, new CadenaRequestDTO(), "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, cadenaAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cadena") CadenaRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = cadenaAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Cadena guardada correctamente");
        return "redirect:/admin/cadenas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = cadenaAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Cadena eliminada correctamente" : error);
        return "redirect:/admin/cadenas";
    }

    private String formulario(Model model, CadenaRequestDTO request, String modo) {
        model.addAttribute("cadena", request);
        model.addAttribute("modo", modo);
        return "admin/cadenas/formulario";
    }
}