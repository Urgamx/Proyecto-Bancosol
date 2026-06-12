package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.request.AsignacionColaboradoresRequestDTO;
import com.example.proyectobancosol.service.admin.AsignacionColaboradoresAdminService;
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
@RequestMapping("/admin/asignar-colaboradores")
public class AsignacionColaboradoresAdminController {

    private final AsignacionColaboradoresAdminService asignacionColaboradoresAdminService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("coordinadores", asignacionColaboradoresAdminService.listar());
        return "admin/asignar-colaboradores/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idCoordinador") Integer idCoordinador, Model model) {
        return formulario(model, asignacionColaboradoresAdminService.buscarFormulario(idCoordinador));
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idCoordinador") Integer idCoordinador,
                          @RequestParam(value = "idsColaboradores", required = false) List<Integer> idsColaboradores,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        AsignacionColaboradoresRequestDTO request = new AsignacionColaboradoresRequestDTO(idCoordinador, idsColaboradores);
        String error = asignacionColaboradoresAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request);
        }

        redirectAttributes.addFlashAttribute("mensaje", "Asignacion de colaboradores guardada correctamente");
        return "redirect:/admin/asignar-colaboradores";
    }

    private String formulario(Model model, AsignacionColaboradoresRequestDTO request) {
        model.addAttribute("asignacion", request);
        model.addAttribute("coordinador", asignacionColaboradoresAdminService.buscarCoordinador(request.getIdCoordinador()));
        model.addAttribute("colaboradores", asignacionColaboradoresAdminService.listarColaboradores());
        return "admin/asignar-colaboradores/formulario";
    }
}