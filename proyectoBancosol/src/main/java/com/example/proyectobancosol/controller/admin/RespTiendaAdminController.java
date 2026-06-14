/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.UsuarioRequestDTO;
import com.example.proyectobancosol.service.admin.AdminRespTiendaService;
import com.example.proyectobancosol.service.admin.AdminRespTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/resp-tienda")
public class RespTiendaAdminController {

    private final AdminRespTiendaService respTiendaAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("responsables", respTiendaAdminService.listarPorRol("RESP_TIENDA"));
        return "admin/respTienda/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        return formulario(model, new UsuarioRequestDTO(), "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, respTiendaAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("responsable") UsuarioRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = respTiendaAdminService.guardar(request, "RESP_TIENDA");

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Responsable guardado correctamente");
        return "redirect:/admin/resp-tienda";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        respTiendaAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Responsable eliminado correctamente");
        return "redirect:/admin/resp-tienda";
    }

    private String formulario(Model model, UsuarioRequestDTO request, String modo) {
        model.addAttribute("responsable", request);
        model.addAttribute("modo", modo);
        return "admin/respTienda/formulario";
    }
}