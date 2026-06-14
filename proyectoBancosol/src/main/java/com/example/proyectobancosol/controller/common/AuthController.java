package com.example.proyectobancosol.controller.common;

import com.example.proyectobancosol.dto.request.LoginRequestDTO;
import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.service.common.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/**
 * gestiona login logout y creacion de sesion
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "auth/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {

        if ("loginRequired".equals(error)) {
            model.addAttribute("error", "Debes iniciar sesión para acceder.");
        } else if ("forbidden".equals(error)) {
            model.addAttribute("error", "No tienes permisos para acceder a esta zona.");
        }

        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDTO loginRequestDTO,
                        HttpSession session,
                        Model model) {

        return authService.login(loginRequestDTO)
                .map(usuario -> iniciarSesion(usuario, session))
                .orElseGet(() -> {
                    model.addAttribute("error", "Email o contraseña inválidos.");
                    return "auth/login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();


        return "redirect:/login";
    }

    private String iniciarSesion(Usuario usuario, HttpSession session) {

        String rol = usuario.getIdRol().getNombre();


        session.setAttribute("usuario", usuario);


        UsuarioSesionDTO usuarioSesionDTO = new UsuarioSesionDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombreCompleto(),
                rol
        );

        session.setAttribute("usuarioSesion", usuarioSesionDTO);
        session.setAttribute("idUsuario", usuario.getId());
        session.setAttribute("rol", rol);


        return switch (rol) {
            case "ADMIN" -> "redirect:/admin";
            case "COORDINADOR" -> "redirect:/coordinador/";
            case "RESP_ENTIDAD" -> "redirect:/resp-entidad";
            case "RESP_TIENDA" -> "redirect:/resp-tienda";
            case "CAPITAN" -> "redirect:/capitan";
            default -> "redirect:/login?error=forbidden";
        };
    }
}

