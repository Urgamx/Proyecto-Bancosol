<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación de Turnos - Capitán</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
<%
    List<AsignacionTurno> turnos = (List<AsignacionTurno>) request.getAttribute("turnos");
    Tienda tienda = (Tienda) request.getAttribute("tienda");
%>
<header>
    <h1>Asignación de Turnos - <%=tienda.getNombre()%></h1>
</header>

<table class="table-striped">
    <thead>
        <tr>
            <th>CAMPAÑA</th>
            <th>HORARIO / DÍA / FRANJA</th>
            <th>VOLUNTARIO</th>
            <th>INCIDENCIAS</th>
        </tr>
    </thead>
    <tbody>

    <%for(AsignacionTurno turno : turnos){%>
    <tr>
        <td><%=turno.getIdCampana().getTipoDeCampana().getNombre()%></td>
        <td><%=turno.getHoraInicio()%>-<%=turno.getHoraFin()%> / <%=turno.getDia()%> / <%=turno.getFranja()%></td>
        <td><%=turno.getIdVoluntario().getNombre()%></td>
        <td>
            <%if(turno.getIncidencia() != null){%>
                <textarea name="incidencias" rows="8" cols="50"> <%=turno.getIncidencia().getDescripcion()%></textarea>
            <%}else{%>
                <textarea name="incidencias" rows="8" cols="50">Sin incidencias</textarea>
            <%}%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>

<div class="mt-3">
    <a href="/capitan/tiendas_asignadas" class="btn btn-secondary">Volver</a>
</div>
</div>
</body>
</html>