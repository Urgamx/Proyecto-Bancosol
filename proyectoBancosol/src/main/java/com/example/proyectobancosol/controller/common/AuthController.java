package com.example.proyectobancosol.controller.common;


import com.example.proyectobancosol.service.common.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.proyectobancosol.dto.request.LoginRequestDTO;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        if ("loginRequired".equals(error)) {
            model.addAttribute("error", "Debes iniciar sesión para acceder.");
        } else if ("forbidden".equals(error)) {
            model.addAttribute("error", "No tienes permisos para acceder a esa zona.");
        }

        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDTO loginRequestDTO,
                        HttpSession session,
                        Model model) {

        return authService.login(loginRequestDTO)
                .map(usuarioSesionDTO -> iniciarSesion(usuarioSesionDTO, session))
                .orElseGet(() -> {
                    model.addAttribute("error", "Email o contraseña inválidos.");
                    return "auth/login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    private String iniciarSesion(UsuarioSesionDTO usuarioSesionDTO, HttpSession session) {

        session.setAttribute("usuarioSesion", usuarioSesionDTO);
        session.setAttribute("idUsuario", usuarioSesionDTO.getIdUsuario());
        session.setAttribute("rol", usuarioSesionDTO.getRol());

        return switch (usuarioSesionDTO.getRol()) {
            case "ADMIN" -> "redirect:/admin";
            case "RESP_ENTIDAD" -> "redirect:/resp-entidad";
            case "COORDINADOR" -> "redirect:/coordinador/";
            case "RESP_TIENDA" -> "redirect:/resp-tienda";
            case "CAPITAN" -> "redirect:/capitan";
            default -> "redirect:/login";
        };
    }
}
