<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Rol" %>
<%@ page import="com.example.proyectobancosol.dto.response.RolResponseDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 12/06/2026
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
    List<RolResponseDTO> roles = (List<RolResponseDTO>) request.getAttribute("roles");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Usuarios</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
<h1>Editar Usuario:</h1>
<form action="/admin/usuarios/guardar" method="post">
   <input type="hidden" name="id" value="<%=usuario != null ? usuario.getId() : ""%>">

    <table border="1">
        <tr>
            <td><label>Nombre</label></td>
            <td><input type="text" name="nombre" value="<%=usuario != null ? usuario.getNombreCompleto() : ""%>" required></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="email" name="email" value="<%=usuario != null ? usuario.getEmail() : ""%>" required></td>
        </tr>
        <tr>
            <td><label>Contraseña</label></td>
            <td><input type="text" name="password" value="<%=usuario != null ? usuario.getPassword() : ""%>" required></td>
        </tr>
        <tr>
            <td><label>Rol</label></td>
            <td>
                <select name="rol">
                    <% for (RolResponseDTO rol : roles) {%>
                        <option value="<%=rol.getId()%>" <%=usuario != null && usuario.getIdRol().equals(rol.getId()) ? "selected" : ""%>><%=rol.getNombre()%></option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td><label>Estado</label></td>
            <td>
                <select name="estado">
                    <option value="1" <%=usuario != null && usuario.getActivo().equals(1) ? "selected" : ""%>>Activo</option>
                    <option value="2" <%=usuario != null && usuario.getActivo().equals(2) ? "selected" : ""%>>Inactivo</option>
                </select>
            </td>

        </tr>
    </table>


    <div class="mt-3">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
    <div class="mt-3">
        <a href="/admin/usuarios/" class="btn btn-secondary">Volver</a>
    </div>
</form>
</div>
</body>
</html>
