<%@ page import="com.example.proyectobancosol.entity.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 18/05/2026
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>
<head>
    <title>Asignacion Voluntarios</title>
</head>
<body>

<h1>Asignacion de voluntarios</h1>
<label>Usuario: <%=user.getNombreCompleto()%></label>
<br>
<table border="2">
    <tr>
        <th>TIENDA</th>
        <th>DOMICILIO</th>
        <th>LOCALIDAD</th>
        <th>CAPITAN</th>
        <th>VIERNES MAÑANA</th>
        <th>VIERNES TARDE</th>
        <th>SABADO MAÑANA</th>
        <th>SABADO TARDE</th>
        <th>OBSERVACION</th>
    </tr>
</table>
</body>
</html>
