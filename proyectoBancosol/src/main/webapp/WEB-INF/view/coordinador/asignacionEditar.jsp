<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="com.example.proyectobancosol.entity.Colaborador" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Voluntario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    AsignacionTurno turno = (AsignacionTurno) request.getAttribute("turno");
    List<Colaborador> colaboradores = (List<Colaborador>) request.getAttribute("colaboradores");
    Colaborador colaboradorSelected = (Colaborador) request.getAttribute("colaboradorSelected");
    List<Voluntario> voluntarios = (List<Voluntario>) request.getAttribute("voluntarios");
    String formAction = (colaboradorSelected == null) ? "/coordinador/seleccionar" : "/coordinador/guardarEditar";
%>
<html>
<head>
    <title>Seleccionar Colaborador</title>
</head>
<body>
<h1>Editar Turno</h1>

<form method="post" action="<%= formAction %>">
    <input type="hidden" name="id" value="<%=turno.getId()%>">
    <input type="hidden" name="colaboradorId" value="<%= colaboradorSelected != null ? colaboradorSelected.getId() : "" %>">
    <table>
        <tr>
            <td>TIENDA</td>
            <th><%= turno.getIdTienda().getNombre() %></th>
        </tr>
        <tr>
            <td>DOMICILIO</td>
            <th><%= turno.getIdTienda().getDireccion() %></th>
        </tr>

        <tr>
            <td>COLABORADOR</td>
            <th>
                    <% if (colaboradorSelected == null) { %>
                <select name="colaborador">
                    <%
                        for (Colaborador colaborador : colaboradores) {
                            String selected = "";
                            if (turno.getIdColaborador().getId().equals(colaborador.getId())) {
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
                        for (Voluntario voluntario : voluntarios) {
                            String selected = "";
                            if (turno.getIdVoluntario().getId().equals(voluntario.getId())) {
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

<a href="/coordinador/asignacionVoluntarios">Volver</a>

</body>
</html>