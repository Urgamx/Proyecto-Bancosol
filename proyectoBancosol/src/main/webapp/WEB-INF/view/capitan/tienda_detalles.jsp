<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proyectobancosol.dto.response.TiendaResponseDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.AsignacionTurnoDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles de Tienda - BancoSol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <%
        TiendaResponseDTO tienda = (TiendaResponseDTO) request.getAttribute("tienda");
        List<AsignacionTurnoDTO> turnos = (List<AsignacionTurnoDTO>) request.getAttribute("turnos");
    %>

    <div class="mb-3">
        <a href="/capitan/" class="btn btn-secondary">Volver al Panel</a>
    </div>

    <header>
        <h1>Detalles de Tienda</h1>
    </header>

    <div class="card">
        <div class="card-body">
            <p><strong>Tienda:</strong> <%=tienda.getNombre()%></p>
            <p><strong>Dirección:</strong> <%=tienda.getDireccion()%></p>
            <p><strong>Código Postal:</strong> <%=tienda.getCodPostal()%></p>
            <p><strong>Cadena:</strong> <%=tienda.getCadena()%></p>
        </div>
    </div>

    <h2 class="mt-3">Voluntarios Asignados y Turnos</h2>

    <%if(turnos != null && turnos.size() > 0){%>
    <table class="table-striped">
        <thead>
        <tr>
            <th>VOLUNTARIO</th>
            <th>CAMPAÑA</th>
            <th>DÍA</th>
            <th>FRANJA</th>
            <th>HORARIO</th>
            <th>INCIDENCIA</th>
            <th>ACCIONES</th>
        </tr>
        </thead>
        <tbody>
        <%for(AsignacionTurnoDTO turno : turnos){%>
        <tr>
            <td><a href="/capitan/detalles-voluntario?id=<%=turno.getVoluntarioDTO().getId()%>&tienda=<%=tienda.getId()%>"><%=turno.getVoluntarioDTO().getNombre()%></a></td>
            <td><%=turno.getCampanaResponseDTO().getTipoCampana()%></td>
            <td><%=turno.getDia()%></td>
            <td><%=turno.getFranja()%></td>
            <td><%=turno.getHoraInicio()%> - <%=turno.getHoraFin()%></td>
            <td>
                <%if(turno.getIncidenciaDTO() != null){%>
                <span class="text-danger"><strong>Sí</strong></span><br>
                <small><%=turno.getIncidenciaDTO().getDescripcion()%></small>
                <%}else{%>
                <span class="text-success">Sin incidencias</span>
                <%}%>
            </td>
            <td>
                <a href="/capitan/detalles-voluntario?id=<%=turno.getVoluntarioDTO().getId()%>&tienda=<%=tienda.getId()%>" class="btn btn-primary btn-sm">Ver Detalles</a>
                <%if(turno.getIncidenciaDTO() == null){%>
                <a href="/capitan/registrar-incidencia?id=<%=turno.getIdAsignacion()%>&tienda=<%=tienda.getId()%>" class="btn btn-warning btn-sm">Reportar</a>
                <%}%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <%}else{%>
    <div class="alert alert-warning">
        <strong>No hay voluntarios asignados a esta tienda.</strong>
    </div>
    <%}%>
</div>

</body>
</html>