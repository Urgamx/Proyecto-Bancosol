package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dao.CampanaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dto.request.CapitanRequestDTO;
import com.example.proyectobancosol.service.admin.CampanaAdminService;
import com.example.proyectobancosol.service.admin.CapitanAdminService;
import com.example.proyectobancosol.service.admin.TiendaAdminService;
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
@RequestMapping("/admin/capitanes")
public class CapitanAdminController {

    private final CapitanAdminService capitanAdminService;
    private final CampanaAdminService  campanaAdminService;
    private final TiendaAdminService  tiendaAdminService;

    @GetMapping({"", "/"})
    public String listar(@RequestParam(value = "texto", required = false) String texto,
                         @RequestParam(value = "idCampana", required = false) Integer idCampana,
                         @RequestParam(value = "idTienda", required = false) Integer idTienda,
                         Model model) {

        model.addAttribute("capitanes", capitanAdminService.listarConFiltros(texto, idCampana, idTienda));

        model.addAttribute("texto", texto);
        model.addAttribute("idCampanaSeleccionada", idCampana);
        model.addAttribute("idTiendaSeleccionada", idTienda);

        model.addAttribute("todasCampanas", campanaAdminService.listar());
        model.addAttribute("todasTiendas", tiendaAdminService.listar());

        return "admin/capitan/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        CapitanRequestDTO request = new CapitanRequestDTO();
        request.setActivo(1);
        return formulario(model, request, "Crear");
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        return formulario(model, capitanAdminService.buscarFormulario(id), "Editar");
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("capitan") CapitanRequestDTO request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        String error = capitanAdminService.guardar(request);

        if (error != null) {
            model.addAttribute("error", error);
            return formulario(model, request, request.getId() == null ? "Crear" : "Editar");
        }

        redirectAttributes.addFlashAttribute("mensaje", "Capitán guardado correctamente");
        return "redirect:/admin/capitanes";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        String error = capitanAdminService.eliminar(id);
        redirectAttributes.addFlashAttribute(error == null ? "mensaje" : "error", error == null ? "Capitán eliminado correctamente" : error);
        return "redirect:/admin/capitanes";
    }

    private String formulario(Model model, CapitanRequestDTO request, String modo) {
        model.addAttribute("capitan", request);
        model.addAttribute("modo", modo);
        model.addAttribute("todasCampanas", campanaAdminService.listar());
        model.addAttribute("todasTiendas", tiendaAdminService.listar());
        return "admin/capitan/formulario";
    }
}