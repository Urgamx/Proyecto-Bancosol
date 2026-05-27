<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel de Capitán - Bancosol</title>
</head>
<body>
<%
    List<AsignacionTurno> turnos = (List<AsignacionTurno>) request.getAttribute("turnos");
    Tienda tienda = (Tienda) request.getAttribute("tienda");
%>
<header>
    <h1>Asignacion de turnos para la tienda:<%=tienda.getNombre()%></h1>
</header>
<table border="1">
    <tr>
        <th>CAMPANA</th>
        <th>HORARIO / DIA / FRANJA</th>
        <th>VOLUNTARIO</th>
        <th>INCIDENCIAS</th>
    </tr>

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
</table>
</body>
</html>