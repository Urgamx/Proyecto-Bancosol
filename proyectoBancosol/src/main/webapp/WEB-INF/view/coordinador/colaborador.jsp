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
    List<UsuarioColaborador> relaciones = (List<UsuarioColaborador>) request.getAttribute("relaciones");
    List<Usuario> coordinadores = (List<Usuario>) request.getAttribute("coordinadores");
    Usuario user = (Usuario) session.getAttribute("usuario");
    String zonaGeo = (String) request.getAttribute("zonaGeo");
    String localidad = (String) request.getAttribute("localidad");
    Integer coordinadorFiltro = (Integer) request.getAttribute("coordinadorSelected");
%>
<head>
    <title>Listado de colaboradores</title>
</head>
<body>
<h1>Listado de colaboradores:</h1>
<form action="/coordinador/filtrarColaborador" method="post">
    <label>Usuario : <%=user.getNombreCompleto()%> (<%=user.getIdRol().getNombre()%>)</label><br>
    <label>ZONA GEOGRAFICA:</label>
    <input type="text" name="zonaGeografica" value="<%=zonaGeo != null ? zonaGeo : ""%>"><br>
    <label>LOCALIDAD:</label>
    <input type="text" name="localidad" value="<%=localidad != null ? localidad : ""%>"><br>
    <label>COORDINADOR:</label>
    <select name="coordinador">
        <option value=""></option>
        <%
            for (Usuario coordinador : coordinadores) {
        %>
        <option value="<%=coordinador.getId()%>" <%=coordinador.getId().equals(coordinadorFiltro) ? "selected" : ""%>><%=coordinador.getNombreCompleto()%></option>
        <%}%>
    </select> <br>
    <button type="submit">Filtrar</button><br>
    <a href="/coordinador/colaborador">Limpiar Filtro</a>
</form>

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
            for (UsuarioColaborador relacion : relaciones) {
                if (colaborador.getId().equals(relacion.getColaborador().getId())) {
        %>
        <td><%=relacion.getUsuario().getNombreCompleto()%></td>
        <%}}%>
        <td><%=colaborador.getContactoNom()%> (<%=colaborador.getContactoTlf()%>)</td>
        <td><%=colaborador.getObservaciones()%></td>
    </tr>
    <%}%>
</table>
<br>
<a href="/coordinador/nuevoColaborador">Añadir</a>
<br><br>
<a href="/coordinador/">Volver</a>
</body>
</html>
