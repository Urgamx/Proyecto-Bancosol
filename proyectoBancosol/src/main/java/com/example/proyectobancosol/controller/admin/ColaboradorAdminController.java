package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.service.admin.ColaboradorAdminService;
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
@RequestMapping("/admin/colaboradores")
public class ColaboradorAdminController {

    private final ColaboradorAdminService colaboradorAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("pendientes", colaboradorAdminService.listarPendientes());
        model.addAttribute("activos", colaboradorAdminService.listarActivos());
        model.addAttribute("inactivos", colaboradorAdminService.listarInactivos());
        return "admin/colaboradores/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        ColaboradorRequestDTO request = new ColaboradorRequestDTO();
        request.setEstado(2);
        return formulario(model, request, "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, colaboradorAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("colaborador") ColaboradorRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Colaborador guardado correctamente");
        return "redirect:/admin/colaboradores";
    }

    @PostMapping("/activar")
    public String activar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.activar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Colaborador activado correctamente" : error);
        return "redirect:/admin/colaboradores";
    }

    @PostMapping("/rechazar")
    public String rechazar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.rechazar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Colaborador rechazado correctamente" : error);
        return "redirect:/admin/colaboradores";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Colaborador eliminado correctamente" : error);
        return "redirect:/admin/colaboradores";
    }

    private String formulario(Model model, ColaboradorRequestDTO request, String modo) {
        model.addAttribute("colaborador", request);
        model.addAttribute("modo", modo);
        return "admin/colaboradores/formulario";
    }
}