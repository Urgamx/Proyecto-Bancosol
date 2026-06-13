package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/resp-tienda")
@AllArgsConstructor
public class RespTiendaAdminController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("responsables", usuarioRepository.findByRolNombre("RESP_TIENDA"));
        return "admin/respTienda/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("responsable", new Usuario());
        model.addAttribute("modo", "Crear");
        return "admin/respTienda/formulario";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("responsable", usuarioRepository.findById(id).orElse(new Usuario()));
        model.addAttribute("modo", "Editar");
        return "admin/respTienda/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("responsable") Usuario usuario, RedirectAttributes redirectAttributes) {
        // Aquí podrías añadir lógica para asignar el rol "RESP_TIENDA" si es nuevo
        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Responsable guardado correctamente");
        return "redirect:/admin/resp-tienda";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        usuarioRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Responsable eliminado correctamente");
        return "redirect:/admin/resp-tienda";
    }
}