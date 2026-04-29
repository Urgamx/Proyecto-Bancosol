package com.example.proyectobancosol.controller.common;

import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.service.common.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model){
        return authService.login(email, password)
                .map(usuario -> iniciarSesion(usuario, session))
                .orElseGet(() -> {
                    model.addAttribute("error", "Email o password invalidos");
                    return "auth/login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    private String iniciarSesion(Usuario usuario, HttpSession session) {
        session.setAttribute("usuario", usuario);
        String rol = usuario.getIdRol().getNombre();

        return switch (rol) {
            case "ADMIN" -> "redirect:/admin";
            case "RESP_ENTIDAD" -> "redirect:/resp-entidad";
            case "COORDINADOR" -> "redirect:/coordinador";
            case "RESP_TIENDA" -> "redirect:/resp-tienda";
            default -> "redirect:/login";
        };
    }
}
