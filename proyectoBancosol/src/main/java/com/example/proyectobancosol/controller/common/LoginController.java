package com.example.proyectobancosol.controller.common;

import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.entity.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "auth/login"; // <--- ACTUALIZADO: Ahora apunta a la carpeta auth
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password);

        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);

            // IMPORTANTE: Mira en tu tabla ROL qué ID tiene "Responsable de Tienda"
            // Si en tu SQL el rol 4 es el responsable, lo dejamos así:
            if (usuario.getIdRol() == 4) {
                return "redirect:/responsable/tiendas";
            }

            return "redirect:/";

        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
            return "auth/login"; // <--- ACTUALIZADO
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}