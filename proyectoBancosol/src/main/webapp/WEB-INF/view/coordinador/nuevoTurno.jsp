<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.dto.request.ColaboradorRequestDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.*" %>
<%@ page import="com.example.proyectobancosol.dto.request.TiendaRequestDTO" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 09/06/2026
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%--
Página JSP que muestra el crear unnuevo AsignacionTurno

Autores:
- David Vilaseca Pareja: 85%
- IA Generativa: 15%

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
  List<ColaboradorResponseDTO> colaboradores = (List<ColaboradorResponseDTO>) request.getAttribute("colaboradores");
  ColaboradorRequestDTO colaboradorSelected = (ColaboradorRequestDTO) request.getAttribute("colaboradorSelected");
  List<VoluntarioDTO> voluntarios = (List<VoluntarioDTO>) request.getAttribute("voluntarios");
  List<TiendaResponseDTO> tiendas = (List<TiendaResponseDTO>) request.getAttribute("tiendas");
  List<UsuarioDTO> capitanes = (List<UsuarioDTO>) request.getAttribute("capitanes");
  List<CampanaResponseDTO> campanas = (List<CampanaResponseDTO>) request.getAttribute("campanas");
  TiendaRequestDTO tiendaSelected = (TiendaRequestDTO) request.getAttribute("tiendaSelected");
  String formAction = (colaboradorSelected == null) ? "/coordinador/seleccionarNuevo" : "/coordinador/guardarTurnoNuevo";
%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Nuevo Turno</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
<h1>Nuevo Turno</h1>
<%if (colaboradorSelected == null){%>
<h2>Selecciona un colaborador:</h2>
<%} else {%>
<h2>Rellena los datos:</h2>
<%}%>
<form method="post" action="<%= formAction %>">
  <input type="hidden" name="colaboradorId" value="<%= colaboradorSelected != null ? colaboradorSelected.getId() : "" %>">
  <input type="hidden" name="tiendaId" value="<%= tiendaSelected != null ? tiendaSelected.getId() : "" %>">
  <table>
    <tr>
      <td>COLABORADOR</td>
      <th>
        <% if (colaboradorSelected == null) { %>
        <select name="colaborador">
          <%
            for (ColaboradorResponseDTO colaborador : colaboradores) {

          %>
          <option value="<%= colaborador.getId() %>"><%= colaborador.getNombreEntidad() %></option>
          <% } %>
        </select>
        <% } else { %>
        <%= colaboradorSelected.getNombreEntidad() %>
        <% } %>
      </th>
    </tr>
    <% if (tiendaSelected == null) { %>
    <tr>
      <td>TIENDA</td>
      <th>
        <select name="tienda">
          <%
            for (TiendaResponseDTO tienda : tiendas) {

          %>
          <option value="<%= tienda.getId() %>"><%= tienda.getNombre() %></option>
          <% } %>
        </select>
      </th>
    </tr>
    <% } %>

    <% if (colaboradorSelected == null) { %>
    <tr>
      <td>
        <button type="submit">Seleccionar</button>
      </td>
    </tr>
    <% } else { %>
    <tr>
      <td>TIENDA</td>
      <th><%=tiendaSelected.getNombre()%></th>
    </tr>
    <tr>
      <td>DOMICILIO</td>
      <th><%=tiendaSelected.getDireccion()%></th>
    </tr>
    <tr>
      <td>CAMPAÑAS</td>
      <td>
        <select name="campana">
          <%
            for (CampanaResponseDTO campana : campanas) {
          %>
          <option value="<%= campana.getId() %>" ><%= campana.getTipoCampana() %></option>
          <% } %>
        </select>
      </td>
    </tr>
    <tr>
      <td>CAPITANES</td>
      <td>
        <select name="capitan">
          <%
            for (UsuarioDTO capitan : capitanes) {
          %>
          <option value="<%= capitan.getId() %>" ><%= capitan.getNombreCompleto() %></option>
          <% } %>
        </select>
      </td>
    </tr>
    <tr>
      <td>VOLUNTARIOS</td>
      <td>
        <select name="voluntario">
          <%
            for (VoluntarioDTO voluntario : voluntarios) {
          %>
          <option value="<%= voluntario.getId() %>" ><%= voluntario.getNombre() %></option>
          <% } %>
        </select>
      </td>
    </tr>
    <tr>
      <td>COMIENZO TURNO</td>
      <td>
        <input type="text" name="comienzo" value="">
      </td>
    </tr>
    <tr>
      <td>FIN TURNO</td>
      <td>
        <input type="text" name="fin" value="">
      </td>
    </tr>
    <tr>
      <td>DIA</td>
      <td>
        <input type="text" name="dia" value="">
      </td>
    </tr>
    <tr>
      <td>FRANJA</td>
      <td>
        <input type="text" name="franja" value="">
      </td>
    </tr>
    <tr>
      <td>
        <div class="mt-3">
          <button type="submit" class="btn-primary">Guardar</button>
        </div>
      </td>
    </tr>
    <% } %>
  </table>
</form>
<div class="mt-3">
  <a href="/coordinador/" class="btn btn-secondary">Volver</a>
</div>
</div>
</body>
</html>
