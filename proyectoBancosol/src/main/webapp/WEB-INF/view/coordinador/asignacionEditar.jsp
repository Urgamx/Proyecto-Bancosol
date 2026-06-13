<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.dto.response.ColaboradorResponseDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.VoluntarioDTO" %>
<%@ page import="com.example.proyectobancosol.dto.request.ColaboradorRequestDTO" %>
<%@ page import="com.example.proyectobancosol.dto.response.AsignacionTurnoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    AsignacionTurnoDTO turno = (AsignacionTurnoDTO) request.getAttribute("turno");
    List<ColaboradorResponseDTO> colaboradores = (List<ColaboradorResponseDTO>) request.getAttribute("colaboradores");
    ColaboradorRequestDTO colaboradorSelected = (ColaboradorRequestDTO) request.getAttribute("colaboradorSelected");
    List<VoluntarioDTO> voluntarios = (List<VoluntarioDTO>) request.getAttribute("voluntarios");
    String formAction = (colaboradorSelected == null) ? "/coordinador/seleccionar" : "/coordinador/guardarEditar";
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Colaborador</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
<h1>Editar Turno</h1>

<form method="post" action="<%= formAction %>">
    <input type="hidden" name="id" value="<%=turno.getIdAsignacion()%>">
    <input type="hidden" name="colaboradorId" value="<%= colaboradorSelected != null ? colaboradorSelected.getId() : "" %>">
    <table>
        <tr>
            <td>TIENDA</td>
            <th><%= turno.getTiendaResponseDTO().getNombre() %></th>
        </tr>
        <tr>
            <td>DOMICILIO</td>
            <th><%= turno.getTiendaResponseDTO().getDireccion() %></th>
        </tr>

        <tr>
            <td>COLABORADOR</td>
            <th>
                    <% if (colaboradorSelected == null) { %>
                <select name="colaborador">
                    <%
                        for (ColaboradorResponseDTO colaborador : colaboradores) {
                            String selected = "";
                            if (turno.getColaboradorRequestDTO().getId().equals(colaborador.getId())) {
                                selected = "selected";
                            }
                    %>
                    <option value="<%= colaborador.getId() %>" <%= selected %>><%= colaborador.getNombreEntidad() %></option>
                    <% } %>
                </select>
                    <% } else { %>
            <%= colaboradorSelected.getNombreEntidad() %>
            <% } %>
            </th>
        </tr>

        <% if (colaboradorSelected == null) { %>
        <tr>
            <td>
                <button type="submit">Seleccionar</button>
            </td>
        </tr>
        <% } else { %>
        <tr>
            <td>VOLUNTARIOS</td>
            <td>
                <select name="voluntario">
                    <%
                        for (VoluntarioDTO voluntario : voluntarios) {
                            String selected = "";
                            if (turno.getVoluntarioDTO().getId().equals(voluntario.getId())) {
                                selected = "selected";
                            }
                    %>
                    <option value="<%= voluntario.getId() %>" <%= selected %>><%= voluntario.getNombre() %></option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <td>COMIENZO TURNO</td>
            <td>
                <input type="text" name="comienzo" value="<%=turno.getHoraInicio()%>">
            </td>
        </tr>
        <tr>
            <td>FIN TURNO</td>
            <td>
                <input type="text" name="fin" value="<%=turno.getHoraFin()%>">
            </td>
        </tr>
        <tr>
            <td>DIA</td>
            <td>
                <input type="text" name="dia" value="<%=turno.getDia()%>">
            </td>
        </tr>
        <tr>
            <td>FRANJA</td>
            <td>
                <input type="text" name="franja" value="<%=turno.getFranja()%>">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Guardar</button>
            </td>
        </tr>
        <% } %>
    </table>
</form>

    <div class="mt-3">
        <a href="/coordinador/asignacionVoluntarios" class="btn btn-secondary">Volver</a>
    </div>

</div>
</body>
</html>