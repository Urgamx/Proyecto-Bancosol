<%@ page import="com.example.proyectobancosol.entity.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 17/05/2026
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>
<head>
    <title>HomePage</title>
</head>
<body>
<h1>Homepage de Coordinador</h1>

<label>Usuario : <%=user.getNombreCompleto()%></label><br><br>
<a href="colaborador">Lista de Colaboradores</a> <br>
<a href="asignacionVoluntarios">Asignación de Voluntarios</a>
</body>
</html>
