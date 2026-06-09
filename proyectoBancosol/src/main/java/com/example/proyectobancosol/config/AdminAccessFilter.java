package com.example.proyectobancosol.config;

import com.example.proyectobancosol.dto.response.UsuarioSesionDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminAccessFilter implements Filter {

    // NUEVO: protege las rutas /admin y /admin/**
    // Lo hacemos con Filter para NO tocar WebConfig.java
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();

        // NUEVO: solo aplicamos control a /admin y /admin/**
        boolean esRutaAdmin = uri.equals(contextPath + "/admin")
                || uri.startsWith(contextPath + "/admin/");

        if (!esRutaAdmin) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("usuarioSesion") == null) {
            httpResponse.sendRedirect(contextPath + "/login?error=loginRequired");
            return;
        }

        UsuarioSesionDTO usuarioSesionDTO =
                (UsuarioSesionDTO) session.getAttribute("usuarioSesion");

        if (!"ADMIN".equals(usuarioSesionDTO.getRol())) {
            httpResponse.sendRedirect(contextPath + "/login?error=forbidden");
            return;
        }

        chain.doFilter(request, response);
    }
}
