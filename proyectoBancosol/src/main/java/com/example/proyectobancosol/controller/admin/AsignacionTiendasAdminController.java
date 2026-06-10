package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.AsignacionTiendasRequestDTO;
import com.example.proyectobancosol.service.admin.AsignacionTiendasAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/asignar-tiendas")
public class AsignacionTiendasAdminController {

    private final AsignacionTiendasAdminService asignacionTiendasAdminService;

    public AsignacionTiendasAdminController(AsignacionTiendasAdminService asignacionTiendasAdminService) {
        this.asignacionTiendasAdminService = asignacionTiendasAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", asignacionTiendasAdminService.listar());
        return "admin/asignar-tiendas/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idCoordinador") Integer idCoordinador, Model model) {
        cargarFormulario(model, asignacionTiendasAdminService.buscarFormulario(idCoordinador));
        return "admin/asignar-tiendas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idCoordinador") Integer idCoordinador,
                          @RequestParam(value = "idsTiendas", required = false) List<Integer> idsTiendas,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        AsignacionTiendasRequestDTO asignacionTiendasRequestDTO = new AsignacionTiendasRequestDTO(idCoordinador, idsTiendas);

        String error = asignacionTiendasAdminService.guardar(asignacionTiendasRequestDTO);

        if (error != null) {
            cargarFormulario(model, asignacionTiendasRequestDTO);
            model.addAttribute("error", error);
            return "admin/asignar-tiendas/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Asignacion de tiendas guardada correctamente");
        return "redirect:/admin/asignar-tiendas";
    }

    private void cargarFormulario(Model model, AsignacionTiendasRequestDTO asignacionTiendasRequestDTO) {
        model.addAttribute("asignacion", asignacionTiendasRequestDTO);
        model.addAttribute("coordinador", asignacionTiendasAdminService.buscarCoordinador(asignacionTiendasRequestDTO.getIdCoordinador()));
        model.addAttribute("tiendas", asignacionTiendasAdminService.listarTiendas());
    }
}