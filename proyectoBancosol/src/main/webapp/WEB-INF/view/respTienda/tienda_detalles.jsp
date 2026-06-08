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
    <title>Detalles de Tienda - BancoSol</title>
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
        <a href="/resp-tienda/" class="btn btn-secondary">Volver al Panel</a>
    </div>
    
    <header>
        <h1>Detalles de Tienda</h1>
    </header>
    
    <div class="card">
        <div class="card-body">
            <p><strong>Tienda:</strong> <%=tienda.getNombre()%></p>
            <p><strong>Dirección:</strong> <%=tienda.getDireccion()%></p>
            <p><strong>Código Postal:</strong> <%=tienda.getCodPostal()%></p>
            <p><strong>Cadena:</strong> <%=tienda.getIdCadena().getNombre()%></p>
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
            <%for(AsignacionTurno turno : turnos){%>
            <tr>
                <td><a href="/resp-tienda/detalles-voluntario?id=<%=turno.getIdVoluntario().getId()%>&tienda=<%=tienda.getId()%>"><%=turno.getIdVoluntario().getNombre()%></a></td>
                <td><%=turno.getIdCampana().getTipoDeCampana().getNombre()%></td>
                <td><%=turno.getDia()%></td>
                <td><%=turno.getFranja()%></td>
                <td><%=turno.getHoraInicio()%> - <%=turno.getHoraFin()%></td>
                <td>
                    <%if(turno.getIncidencia() != null){%>
                        <span class="text-danger"><strong>Sí</strong></span><br>
                        <small><%=turno.getIncidencia().getDescripcion()%></small>
                    <%}else{%>
                        <span class="text-success">Sin incidencias</span>
                    <%}%>
                </td>
                <td>
                    <a href="/resp-tienda/detalles-voluntario?id=<%=turno.getIdVoluntario().getId()%>&tienda=<%=tienda.getId()%>" class="btn btn-primary btn-sm">Ver Detalles</a>
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
