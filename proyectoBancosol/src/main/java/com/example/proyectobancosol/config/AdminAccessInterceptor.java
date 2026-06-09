package com.example.proyectobancosol.config;

import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/login?error=loginRequired");
            return false;
        }

        UsuarioSesionDTO usuarioSesionDTO =
                (UsuarioSesionDTO) session.getAttribute("usuarioSesion");

        if (!"ADMIN".equals(usuarioSesionDTO.getRol())) {
            response.sendRedirect(request.getContextPath() + "/login?error=forbidden");
            return false;
        }

        return true;
    }
}