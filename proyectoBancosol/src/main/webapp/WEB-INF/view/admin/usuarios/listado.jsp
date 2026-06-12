<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 12/06/2026
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Usuarios</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <div class="user-info">
        <div class="user-avatar"><%=user.getNombreCompleto().charAt(0)%></div>
        <div class="user-details">
            <h3><%=user.getNombreCompleto()%></h3>
            <p><%=user.getIdRol().getNombre()%></p>
        </div>
    </div>

    <table border="1" class="table-striped">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>EMAIL</th>
            <th>CONTRASEÑA</th>
            <th>ROL</th>
            <th>ESTADO</th>
            <th>ACCIONES</th>

        </tr>

        <%
            for (Usuario usuario : usuarios) {
        %>
        <tr>
            <td><%=usuario.getId()%></td>
            <td><%=usuario.getNombreCompleto()%></td>
            <td><%=usuario.getEmail()%></td>
            <td><%=usuario.getPassword()%></td>
            <td><%=usuario.getIdRol().getNombre()%></td>
            <td><%=usuario.getActivo() == 1 ? "Activo" : "Inactivo"%></td>
            <td>
                <a href="/admin/usuarios/editar?id=<%=usuario.getId()%>">Editar</a>
                <form method="post" action="/admin/usuarios/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="<%=usuario.getId()%>">
                    <button type="submit">Eliminar</button>
                </form>
            </td>

        </tr>
        <%}%>
    </table>

    <div class="mt-3">
        <a href="/admin/usuarios/nuevo" class="btn btn-primary">Añadir Colaborador</a>
    </div>
    <div class="mt-3">
        <a href="/admin/" class="btn btn-secondary">Volver</a>
    </div>
</div>
</body>
</html>
