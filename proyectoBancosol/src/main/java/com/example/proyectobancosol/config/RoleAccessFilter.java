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

/**
 * filtro para el inicio de sesion, asi le manda a su panel segun el rol
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

@Component
public class RoleAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();

        String rolNecesario = obtenerRolNecesario(uri, contextPath);


        if (rolNecesario == null) {
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

        String rolUsuario = usuarioSesionDTO.getRol();


        if ("ADMIN".equals(rolUsuario)) {
            chain.doFilter(request, response);
            return;
        }


        if (!rolNecesario.equals(rolUsuario)) {
            httpResponse.sendRedirect(contextPath + "/login?error=forbidden");
            return;
        }

        chain.doFilter(request, response);
    }


    private String obtenerRolNecesario(String uri, String contextPath) {

        if (uri.equals(contextPath + "/admin") || uri.startsWith(contextPath + "/admin/")) {
            return "ADMIN";
        }

        if (uri.equals(contextPath + "/coordinador") || uri.startsWith(contextPath + "/coordinador/")) {
            return "COORDINADOR";
        }

        if (uri.equals(contextPath + "/resp-entidad") || uri.startsWith(contextPath + "/resp-entidad/")) {
            return "RESP_ENTIDAD";
        }

        if (uri.equals(contextPath + "/resp-tienda") || uri.startsWith(contextPath + "/resp-tienda/")) {
            return "RESP_TIENDA";
        }

        if (uri.equals(contextPath + "/capitan") || uri.startsWith(contextPath + "/capitan/")) {
            return "CAPITAN";
        }

        return null;
    }
}