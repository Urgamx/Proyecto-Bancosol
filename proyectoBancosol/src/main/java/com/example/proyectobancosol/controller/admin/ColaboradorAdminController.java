package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.service.admin.ColaboradorAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/colaboradores")
public class ColaboradorAdminController {

    private final ColaboradorAdminService colaboradorAdminService;

    public ColaboradorAdminController(ColaboradorAdminService colaboradorAdminService) {
        this.colaboradorAdminService = colaboradorAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("pendientes", colaboradorAdminService.listarPendientes());
        model.addAttribute("activos", colaboradorAdminService.listarActivos());
        return "admin/colaboradores/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        ColaboradorRequestDTO colaboradorRequestDTO = new ColaboradorRequestDTO();
        colaboradorRequestDTO.setEstado(2);

        model.addAttribute("colaborador", colaboradorRequestDTO);
        model.addAttribute("modo", "Crear");

        return "admin/colaboradores/formulario";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("colaborador", colaboradorAdminService.buscarFormulario(id));
        model.addAttribute("modo", "Editar");

        return "admin/colaboradores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("colaborador") ColaboradorRequestDTO colaboradorRequestDTO,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.guardar(colaboradorRequestDTO);

        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("colaborador", colaboradorRequestDTO);
            model.addAttribute("modo", colaboradorRequestDTO.getId() == null ? "Crear" : "Editar");
            return "admin/colaboradores/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Colaborador guardado correctamente");
        return "redirect:/admin/colaboradores";
    }

    @PostMapping("/rechazar")
    public String rechazar(@RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.rechazar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Colaborador rechazado correctamente");
        }

        return "redirect:/admin/colaboradores";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id,
                           RedirectAttributes redirectAttributes) {
        String error = colaboradorAdminService.eliminar(id);

        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Colaborador eliminado correctamente");
        }

        return "redirect:/admin/colaboradores";
    }
}