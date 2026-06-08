<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="com.example.proyectobancosol.entity.TiendaCampana" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tiendas Asignadas - Capitán</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Mis Tiendas Asignadas</h1>
    </header>
    
<%
    List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas");
%>
    <table class="table-striped">
        <thead>
            <tr>
                <th>CAMPAÑA</th>
                <th>NOMBRE</th>
                <th>DIRECCIÓN - CÓDIGO POSTAL</th>
                <th>ACCIÓN</th>
            </tr>
        </thead>
        <tbody>
            <%for(Tienda tienda : tiendas){%>
            <tr>
                <td>
                    <%for(TiendaCampana campana : tienda.getTiendasCampana()){%>
                    <%=campana.getCampana().getTipoDeCampana().getNombre()%>
                    <%}%>
                </td>
                <td><%=tienda.getNombre()%></td>
                <td><%=tienda.getDireccion()%> : (<%=tienda.getCodPostal()%>)</td>
                <td><a href="/capitan/asignacion_turnos?id=<%=tienda.getId()%>" class="btn btn-primary btn-sm">Asignación de Turnos</a></td>
            </tr>
            <%}%>
        </tbody>
    </table>
    
    <div class="mt-3">
        <a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
    </div>
</div>
</body>
</html>