<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voluntarios - BancoSol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Tienda tienda = (Tienda) request.getAttribute("tienda");
    List<AsignacionTurno> turnos = (List<AsignacionTurno>) request.getAttribute("turnos");
%>

    <div class="mb-3">
        <a href="/resp-entidad/" class="btn btn-secondary">Volver al Panel</a>
    </div>
    
    <header>
        <h1>Voluntarios Asignados - <%=tienda.getNombre()%></h1>
    </header>
    
    <div class="card">
        <div class="card-body">
            <p><strong>Tienda:</strong> <%=tienda.getNombre()%></p>
            <p><strong>Dirección:</strong> <%=tienda.getDireccion()%></p>
        </div>
    </div>

    <%if(turnos != null && turnos.size() > 0){%>
        <table class="table-striped mt-3">
            <thead>
                <tr>
                    <th>VOLUNTARIO</th>
                    <th>CAMPAÑA</th>
                    <th>DÍA</th>
                    <th>FRANJA</th>
                    <th>HORARIO</th>
                    <th>ESTADO</th>
                </tr>
            </thead>
            <tbody>
            <%for(AsignacionTurno turno : turnos){%>
            <tr>
                <td>
                    <a href="/resp-entidad/detalles-voluntario?id=<%=turno.getIdVoluntario().getId()%>&tienda=<%=tienda.getId()%>"><strong><%=turno.getIdVoluntario().getNombre()%></strong></a>
                </td>
                <td><%=turno.getIdCampana().getTipoDeCampana().getNombre()%></td>
                <td><%=turno.getDia()%></td>
                <td><%=turno.getFranja()%></td>
                <td><%=turno.getHoraInicio()%> - <%=turno.getHoraFin()%></td>
                <td>
                    <%if(turno.getIncidencia() != null){%>
                        <span class="text-danger"><strong>INCIDENCIA REGISTRADA</strong></span>
                    <%}else{%>
                        <span class="text-success"><strong>SIN INCIDENCIAS</strong></span>
                    <%}%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    <%}else{%>
        <div class="alert alert-warning mt-3">
            <strong>No hay voluntarios asignados a esta tienda.</strong>
        </div>
    <%}%>

    <div class="mt-3">
        <a href="/resp-entidad/tienda?id=<%=tienda.getId()%>" class="btn btn-primary">Ver Detalles de Turnos</a>
    </div>
</div>

</body>
</html>
