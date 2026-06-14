<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.*" %>
<%@ page import="com.example.proyectobancosol.dto.response.UsuarioTiendaDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.CadenaResponseDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.UsuarioDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.AsignacionTurnoDTO" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 18/05/2026
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>

<%--
Página JSP que muestra el listado de AsignacionTurno.

Autores:
- David Vilaseca Pareja: 100%

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    boolean esAdmin = user != null && user.getIdRol() != null && "ADMIN".equals(user.getIdRol().getNombre());
    List<AsignacionTurnoDTO> turnos = (List<AsignacionTurnoDTO>) request.getAttribute("turnos");
    List<UsuarioTiendaDTO> relaciones = (List<UsuarioTiendaDTO>) request.getAttribute("relaciones");
    List<CadenaResponseDTO> cadenas = (List<CadenaResponseDTO>) request.getAttribute("cadenas");
    List<UsuarioDTO> capitanes = (List<UsuarioDTO>) request.getAttribute("capitanes");
    Integer cadenaSelected = (Integer) request.getAttribute("cadenaSelected");
    String localidadSelected = (String) request.getAttribute("localidadSelected");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación de Voluntarios</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
<h1>Asignacion de voluntarios</h1>
<label>Usuario: <%=user.getNombreCompleto()%></label><br>
<form action="/coordinador/filtrarAsignacionTurnos" method="post">
    <label>CADENA:</label>
    <select name="cadenaId">
        <option value=""></option>
        <%
            for(CadenaResponseDTO cadena : cadenas) {
        %>
        <option value="<%=cadena.getId()%>" <%=cadenaSelected != null && cadena.getId().equals(cadenaSelected) ? "selected" : ""%>><%=cadena.getNombre()%></option>
        <%}%>
    </select> <br>
    <label>LOCALIDAD</label>
    <input type="text" name="localidad" value="<%=localidadSelected != null ? localidadSelected : ""%>"><br>
    <button type="submit">Filtrar</button><br>
    <a href="/coordinador/asignacionVoluntarios">Limpiar Filtro</a>
</form>
<table border="2">
    <tr>
        <th>TIENDA</th>
        <th>DOMICILIO</th>
        <th>LOCALIDAD</th>
        <th>CAMPAÑA</th>
        <th>CAPITAN</th>
        <th>HORARIO / DIA / FRANJA</th>
        <th>COLABORADOR</th>
        <th>VOLUNTARIO</th>
        <th>OBSERVACION</th>
        <th></th>
    </tr>

    <%
        for (AsignacionTurnoDTO turno : turnos) {
    %>
    <tr>
        <td><%=turno.getTiendaResponseDTO().getNombre()%></td>
        <td><%=turno.getTiendaResponseDTO().getDireccion()%></td>
        <td><%=turno.getTiendaResponseDTO().getLocalidad()%></td>
        <td><%=turno.getCampanaResponseDTO().getTipoCampana()%></td>
        <%
            String nombreCapitan = "";
            for (UsuarioTiendaDTO relacion : relaciones) {
                for (UsuarioDTO capitan : capitanes) {
                    if (relacion.getTiendaId().equals(turno.getTiendaResponseDTO().getId()) && relacion.getUsuarioId().equals(capitan.getId())) {
                        nombreCapitan = capitan.getNombreCompleto();
                        break;
                    }
                }
            }
        %>
        <td><%=nombreCapitan%></td>

        <td><%=turno.getHoraInicio()%>-<%=turno.getHoraFin()%> / <%=turno.getDia()%> / <%=turno.getFranja()%></td>
        <td><%=turno.getColaboradorRequestDTO().getNombreEntidad()%> - <%=turno.getColaboradorRequestDTO().getContactoNom()%> (<%=turno.getColaboradorRequestDTO().getContactoTlf()%>)</td>
        <td><%=turno.getVoluntarioDTO().getNombre()%> (<%=turno.getVoluntarioDTO().getTelefono()%>)</td>
        <td><%=turno.getColaboradorRequestDTO().getObservaciones()%></td>
        <td><a href="/coordinador/asignacionSeleccion?id=<%=turno.getIdAsignacion()%>">Editar</a>
            <% if (esAdmin) { %>
            <form method="post" action="/coordinador/eliminarTurno" style="display:inline">
                <input type="hidden" name="id" value="<%=turno.getIdAsignacion()%>">
                <button type="submit" onclick="return confirm('Seguro que quieres eliminar este turno?')">Eliminar</button>
            </form>
            <% } %>

        </td>
    </tr>
    <%}%>
</table>
    <br>
<div class="mt-3">
    <a href="/coordinador/nuevoTurno" class="btn btn-primary">Añadir Turno</a>
</div>
    <br>
    <br>
<div class="mt-3">
    <%if (user.getIdRol().getId().equals(3)) {%>
    <a href="/coordinador/" class="btn btn-secondary">Volver</a>
    <%} else if (user.getIdRol().getId().equals(1)) {%>
    <a href="/admin/" class="btn btn-secondary">Volver</a>
    <%}%>
</div>
</div>
</body>
</html>
