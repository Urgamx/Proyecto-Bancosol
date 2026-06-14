<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.dto.response.UsuarioDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.RolResponseDTO" %><%--


<%--
Página JSP que muestra el formulario de usuario.

Autores:
- David Vilaseca Pareja: 100%

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    List<RolResponseDTO> roles = (List<RolResponseDTO>) request.getAttribute("roles");
    String nombreSelected = (String) request.getAttribute("nombreSelected") ;
    Integer rolSelected = (Integer) request.getAttribute("rolSelected");
    String[] nombres = {"ADMIN", "RESP_ENTIDAD", "COORDINADOR", "RESP_TIENDA", "CAPITAN"};
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Usuarios</title>
    <link rel="stylesheet" href="/css/styles.css">
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

    <form action="/admin/usuarios/filtrar" method="post">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%=nombreSelected != null ? nombreSelected : ""%>"><br>
        <label>Rol:</label>
        <select name="rolId">
            <option value=""></option>
            <%for (RolResponseDTO rol : roles) {%>
            <option value="<%=rol.getId()%>" <%=rolSelected != null && rolSelected.equals(rol.getId()) ? "selected" : ""%>><%=rol.getNombre()%>
            <%}%>
        </select><br>
        <button type="submit">Filtrar</button>
    </form>
    <a href="/admin/usuarios">Limpiar Filtro</a>
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
            for (UsuarioDTO usuario : usuarios) {
        %>
        <tr>
            <td><%=usuario.getId()%></td>
            <td><%=usuario.getNombreCompleto()%></td>
            <td><%=usuario.getEmail()%></td>
            <td><input type="password" value="<%=usuario.getPassword()%>" readonly style="border:none; background:transparent;"></td>
            <td><%=nombres[usuario.getIdRol()-1]%></td>
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
    <br>
    <br>
    <div class="mt-3">
        <a href="/admin/usuarios/nuevo" class="btn btn-primary">Añadir Usuario</a>
    </div>
    <br>
    <br>
    <div class="mt-3">
        <a href="/admin/" class="btn btn-secondary">Volver</a>
    </div>
</div>
</body>
</html>
