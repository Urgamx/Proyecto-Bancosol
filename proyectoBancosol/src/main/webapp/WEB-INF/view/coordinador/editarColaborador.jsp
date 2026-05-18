<%@ page import="com.example.proyectobancosol.entity.Colaborador" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 18/05/2026
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Colaborador colaborador = (Colaborador) request.getAttribute("colaborador");
%>
<head>
    <title>Editar Colaborador</title>
</head>
<body>
<h1>Colaborador Seleccionado:</h1>
<form method="post" action="/coordinador/guardarColaborador">
    <input type="hidden" name="id" value="<%=colaborador.getId()%>">
    <table>
        <tr>
            <th>COLABORADOR</th>
            <td><%=colaborador.getNombreEntidad()%></td>
        </tr>
        <tr>
            <th>DOMICILIO</th>
            <td><%=colaborador.getDomicilio()%></td>
        </tr>
        <tr>
            <th>CP - LOCALIDAD</th>
            <td><%=colaborador.getCodPostal()%> - <%=colaborador.getLocalidad()%></td>
        </tr>
        <tr>
            <th>COLABORA EN</th>
            <td><%=colaborador.getZonaGeografica()%></td>
        </tr>
        <tr>
            <th>CONTACTO</th>
            <td>
                <table>

                    <tr>
                        <th>Nombre:</th>
                        <td>
                            <input type="text" name="contactoNom" value="<%=colaborador.getContactoNom() != null ? colaborador.getContactoNom() : ""%>">
                        </td>
                    </tr>
                    <tr>
                        <th>Teléfono:</th>
                        <td>
                            <input type="text" name="contactoTlf" value="<%=colaborador.getContactoTlf() != null ? colaborador.getContactoTlf() : ""%>">
                        </td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>
                            <input type="email" name="email" value="<%=colaborador.getEmail() != null ? colaborador.getEmail() : ""%>" required>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <button type="submit">Guardar</button>
</form>
</body>
</html>
