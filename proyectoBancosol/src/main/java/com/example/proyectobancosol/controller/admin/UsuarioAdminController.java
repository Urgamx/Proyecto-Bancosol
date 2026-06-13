package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.dto.response.RolResponseDTO;
import com.example.proyectobancosol.dto.response.UsuarioDTO;
import com.example.proyectobancosol.service.admin.RolAdminService;
import com.example.proyectobancosol.service.coordinador.UsuarioColaboradorService;
import com.example.proyectobancosol.service.coordinador.UsuarioService;
import com.example.proyectobancosol.service.coordinador.UsuarioTiendaService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/usuarios")
public class UsuarioAdminController {

    private final UsuarioService usuarioService;
    private final RolAdminService rolAdminService;
    private final UsuarioColaboradorService usuarioColaboradorService;
    private final UsuarioTiendaService usuarioTiendaService;

    @GetMapping({"","/"})
    public String listar(Model model,
                         HttpSession session){

        List<UsuarioDTO> usuarios = this.usuarioService.findAll();
        List<RolResponseDTO> roles = this.rolAdminService.findAll();

        model.addAttribute("roles", roles);
        model.addAttribute("usuarios", usuarios);

        return "admin/usuarios/listado";
    }

    @PostMapping("/filtrar")
    public String filtrar(@RequestParam(value = "rolId", required = false) Integer rolId,
                          @RequestParam("nombre") String nombre,
                          Model model) {

        List<UsuarioDTO> usuarios = null;
        if (rolId == null) {
            usuarios = this.usuarioService.findByNombre(nombre);
        } else {
            usuarios = this.usuarioService.findByNombreRol(rolId,nombre);
        }
        List<RolResponseDTO> roles = this.rolAdminService.findAll();

        model.addAttribute("roles", roles);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("nombreSelected", nombre);
        model.addAttribute("rolSelected", rolId);

        return "admin/usuarios/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id,
                         Model model) {

        UsuarioDTO usuario = this.usuarioService.findById(id);
        List<RolResponseDTO> roles = this.rolAdminService.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);

        return "admin/usuarios/formulario";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        List<RolResponseDTO> roles = this.rolAdminService.findAll();
        model.addAttribute("roles", roles);

        return "admin/usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(value = "id" ,required = false) Integer id,
                          @RequestParam("nombre") String nombre,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("rol") Integer rolId,
                          @RequestParam("estado") Integer estado)
    {

        UsuarioDTO usuario = new UsuarioDTO();

        if (id != null) {
            usuario.setId(id);
        }

        usuario.setNombreCompleto(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setIdRol(rolId);
        usuario.setActivo(estado);

        usuarioService.save(usuario);

        return "redirect:/admin/usuarios/";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id)
    {
        UsuarioDTO usuario = usuarioService.findById(id);
        this.usuarioColaboradorService.deleteByUsuarioId(id);
        this.usuarioTiendaService.deleteByUsuarioId(id);

        this.usuarioService.delete(usuario);

        return "redirect:/admin/usuarios/";
    }
}
