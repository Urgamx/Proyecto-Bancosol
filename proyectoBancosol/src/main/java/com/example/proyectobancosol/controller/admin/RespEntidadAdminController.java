/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dao.RolRepository;
import com.example.proyectobancosol.dto.request.UsuarioRequestDTO;
import com.example.proyectobancosol.service.admin.AdminRespEntidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/resp-entidad")
public class RespEntidadAdminController {

    private final AdminRespEntidadService adminRespEntidadService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        return formulario(model, new UsuarioRequestDTO(), "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, adminRespEntidadService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("responsable") UsuarioRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = adminRespEntidadService.guardar(request, "RESP_ENTIDAD");

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Responsable guardado correctamente");
        return "redirect:/admin/resp-entidad";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        adminRespEntidadService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Responsable eliminado correctamente");
        return "redirect:/admin/resp-entidad";
    }

    @GetMapping({"", "/"})
    public String listar(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        model.addAttribute("responsables", adminRespEntidadService.listarPorRolYNombre("RESP_ENTIDAD", filtro));
        model.addAttribute("filtro", filtro);
        return "admin/respEntidad/listado";
    }

    private String formulario(Model model, UsuarioRequestDTO request, String modo) {
        model.addAttribute("responsable", request);
        model.addAttribute("modo", modo);
        return "admin/respEntidad/formulario";
    }
}