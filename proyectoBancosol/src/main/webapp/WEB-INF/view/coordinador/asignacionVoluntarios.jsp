<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 18/05/2026
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    List<AsignacionTurno> turnos = (List<AsignacionTurno>) request.getAttribute("turnos");
    List<UsuarioTienda> relaciones = (List<UsuarioTienda>) request.getAttribute("relaciones");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación de Voluntarios</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>

<h1>Asignacion de voluntarios</h1>
<label>Usuario: <%=user.getNombreCompleto()%></label>
<br>
<table border="2">
    <tr>
        <th>TIENDA</th>
        <th>DOMICILIO</th>
        <th>CAPITAN</th>
        <th>HORARIO / DIA / FRANJA</th>
        <th>COLABORADOR</th>
        <th>VOLUNTARIO</th>
        <th>OBSERVACION</th>
        <th></th>
    </tr>

    <%
        for (AsignacionTurno turno : turnos) {
    %>
    <tr>
        <td><%=turno.getIdTienda().getNombre()%></td>
        <td><%=turno.getIdTienda().getDireccion()%></td>
        <%
            for (UsuarioTienda relacion : relaciones) {
                if (relacion.getTienda().getId().equals(turno.getIdTienda().getId())) {
        %>
        <td><%=relacion.getUsuario().getNombreCompleto()%></td>
        <%}}%>

        <td><%=turno.getHoraInicio()%>-<%=turno.getHoraFin()%> / <%=turno.getDia()%> / <%=turno.getFranja()%></td>
        <td><%=turno.getIdColaborador().getNombreEntidad()%> - <%=turno.getIdColaborador().getContactoNom()%> (<%=turno.getIdColaborador().getContactoTlf()%>)</td>
        <td><%=turno.getIdVoluntario().getNombre()%> (<%=turno.getIdVoluntario().getTelefono()%>)</td>
        <td><%=turno.getIdColaborador().getObservaciones()%></td>
        <td><a href="/coordinador/asignacionSeleccion?id=<%=turno.getId()%>">Editar</a></td>
    </tr>
    <%}%>
</table>
<div class="mt-3">
    <a href="/coordinador/nuevoTurno" class="btn btn-primary">Añadir Turno</a>
</div>
<div class="mt-3">
    <a href="/coordinador/" class="btn btn-secondary">Volver</a>
</div>
</body>
</html>
