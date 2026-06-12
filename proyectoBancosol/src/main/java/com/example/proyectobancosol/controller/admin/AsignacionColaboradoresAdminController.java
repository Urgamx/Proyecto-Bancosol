package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.AsignacionColaboradoresRequestDTO;
import com.example.proyectobancosol.service.admin.AsignacionColaboradoresAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/asignar-colaboradores")
public class AsignacionColaboradoresAdminController {

    private final AsignacionColaboradoresAdminService asignacionColaboradoresAdminService;

    public AsignacionColaboradoresAdminController(AsignacionColaboradoresAdminService asignacionColaboradoresAdminService) {
        this.asignacionColaboradoresAdminService = asignacionColaboradoresAdminService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", asignacionColaboradoresAdminService.listar());
        return "admin/asignar-colaboradores/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idCoordinador") Integer idCoordinador, Model model) {
        cargarFormulario(model, asignacionColaboradoresAdminService.buscarFormulario(idCoordinador));
        return "admin/asignar-colaboradores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idCoordinador") Integer idCoordinador,
                          @RequestParam(value = "idsColaboradores", required = false) List<Integer> idsColaboradores,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO = new AsignacionColaboradoresRequestDTO(idCoordinador, idsColaboradores);

        String error = asignacionColaboradoresAdminService.guardar(asignacionColaboradoresRequestDTO);

        if (error != null) {
            cargarFormulario(model, asignacionColaboradoresRequestDTO);
            model.addAttribute("error", error);
            return "admin/asignar-colaboradores/formulario";
        }

        redirectAttributes.addFlashAttribute("mensaje", "Asignacion de colaboradores guardada correctamente");
        return "redirect:/admin/asignar-colaboradores";
    }

    private void cargarFormulario(Model model, AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO) {
        model.addAttribute("asignacion", asignacionColaboradoresRequestDTO);
        model.addAttribute("coordinador", asignacionColaboradoresAdminService.buscarCoordinador(asignacionColaboradoresRequestDTO.getIdCoordinador()));
        model.addAttribute("colaboradores", asignacionColaboradoresAdminService.listarColaboradores());
    }
}