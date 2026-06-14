<%--
Marta Vegas: 70%
IA: 30%
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proyectobancosol.dto.response.UsuarioSesionDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.TiendaResponseDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Responsable de Entidad - BancoSol</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Panel de Responsable de Entidad Colaboradora</h1>
    </header>
    
<%
    UsuarioSesionDTO user = (UsuarioSesionDTO) session.getAttribute("usuarioSesion");
    List<TiendaResponseDTO> tiendas = (List<TiendaResponseDTO>) request.getAttribute("tiendas");
%>

    <div class="user-info">
        <div class="user-avatar"><%=user.getNombreCompleto().charAt(0)%></div>
        <div class="user-details">
            <h3><%=user.getNombreCompleto()%></h3>
            <p>Responsable de Entidad</p> 
        </div>
    </div>

    <h2 class="mt-3">Mis Tiendas Asignadas</h2>
    
    <%if(tiendas != null && tiendas.size() > 0){%>
        <table class="table-striped">
            <thead>
                <tr>
                    <th>TIENDA</th>
                    <th>DIRECCIÓN</th>
                    <th>CÓDIGO POSTAL</th>
                    <th>ACCIONES</th>
                </tr>
            </thead>
            <tbody>
            <%for(TiendaResponseDTO tienda : tiendas){%>
            <tr>
                <td><%=tienda.getNombre()%></td>
                <td><%=tienda.getDireccion()%></td>
                <td><%=tienda.getCodPostal()%></td>
                <td>
                    <a href="/resp-entidad/tienda?id=<%=tienda.getId()%>" class="btn btn-primary btn-sm">Ver Detalles</a>
                    <a href="/resp-entidad/voluntarios?id=<%=tienda.getId()%>" class="btn btn-info btn-sm">Ver Voluntarios</a>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    <%}else{%>
        <div class="alert alert-warning">
            <strong>No hay tiendas asignadas.</strong>
        </div>
    <%}%>
    
    <div class="mt-3 text-right">
        <a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
    </div>
</div>

</body>
</html>