package com.example.proyectobancosol.controller.admin;

import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.entity.Usuario;
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

        List<Usuario> usuarios = this.usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);

        return "admin/usuarios/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id,
                         Model model) {

        Usuario usuario = this.usuarioService.findById(id);
        List<Rol> roles = this.rolAdminService.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);

        return "admin/usuarios/formulario";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        List<Rol> roles = this.rolAdminService.findAll();
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

        Usuario usuario = null;

        if (id != null) {
            usuario = usuarioService.findById(id);
        } else {
            usuario = new Usuario();
        }

        usuario.setNombreCompleto(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setIdRol(this.rolAdminService.findById(rolId));
        usuario.setActivo(estado);

        usuarioService.save(usuario);

        return "redirect:/admin/usuarios/";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id)
    {
        Usuario usuario = usuarioService.findById(id);
        this.usuarioColaboradorService.deleteByUsuarioId(id);
        this.usuarioTiendaService.deleteByUsuarioId(id);

        this.usuarioService.delete(usuario);

        return "redirect:/admin/usuarios/";
    }
}
