<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Colaborador" %>
<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.UsuarioColaborador" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 17/05/2026
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<Colaborador> colaboradores = (List<Colaborador>) request.getAttribute("colaboradores");
    List<UsuarioColaborador> usuarios = (List<UsuarioColaborador>) request.getAttribute("usuarios");
    Usuario user = (Usuario) session.getAttribute("usuario");
%>
<head>
    <title>Listado de colaboradores</title>
</head>
<body>
<h1>Listado de colaboradores:</h1>

<label>Usuario : <%=user.getNombreCompleto()%> (<%=user.getIdRol().getNombre()%>)</label>
<br><br>

<table border="2">
    <tr>
        <th>COLABORADOR</th>
        <th>DOMICILIO</th>
        <th>LOCALIDAD</th>
        <th>COLABORADORA EN</th>
        <th>COORDINADOR</th>
        <th>CONTACTO PRINCIPAL</th>
        <th>OBSERVACIONES</th>
    </tr>

    <%
        for (Colaborador colaborador : colaboradores) {
    %>
    <tr>
        <td><a href="/coordinador/editarColaborador?id=<%=colaborador.getId()%>"><%=colaborador.getNombreEntidad()%></a></td>
        <td><%=colaborador.getDomicilio()%></td>
        <td><%=colaborador.getLocalidad()%></td>
        <td><%=colaborador.getZonaGeografica()%></td>
        <%
            for (UsuarioColaborador usuario : usuarios) {
                if (colaborador.getId().equals(usuario.getColaborador().getId())) {
        %>
        <td><%=usuario.getUsuario().getNombreCompleto()%></td>
        <%}}%>
        <td><%=colaborador.getContactoNom()%> (<%=colaborador.getContactoTlf()%>)</td>
        <td><%=colaborador.getObservaciones()%></td>
    </tr>
    <%}%>
</table>
<br>
<a href="/coordinador/">Volver</a>
</body>
</html>
