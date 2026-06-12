package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.AsignacionTiendasRequestDTO;
import com.example.proyectobancosol.service.admin.AsignacionTiendasAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/asignar-tiendas")
public class AsignacionTiendasAdminController {

    private final AsignacionTiendasAdminService asignacionTiendasAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", asignacionTiendasAdminService.listar());
        return "admin/asignar-tiendas/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idCoordinador") Integer idCoordinador, Model model) {
        return formulario(model, asignacionTiendasAdminService.buscarFormulario(idCoordinador));
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idCoordinador") Integer idCoordinador,
                          @RequestParam(value = "idsTiendas", required = false) List<Integer> idsTiendas,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        AsignacionTiendasRequestDTO request = new AsignacionTiendasRequestDTO(idCoordinador, idsTiendas);
        String error = asignacionTiendasAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request);
        }

        redirectAttributes.addFlashAttribute("mensaje", "Asignacion de tiendas guardada correctamente");
        return "redirect:/admin/coordinadores";
    }

    private String formulario(Model model, AsignacionTiendasRequestDTO request) {
        model.addAttribute("asignacion", request);
        model.addAttribute("coordinador", asignacionTiendasAdminService.buscarCoordinador(request.getIdCoordinador()));
        model.addAttribute("tiendas", asignacionTiendasAdminService.listarTiendas());
        return "admin/asignar-tiendas/formulario";
    }
}