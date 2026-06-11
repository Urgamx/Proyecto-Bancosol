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
<!DOCTYPE html>
<html lang="es">
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Colaboradores</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Listado de Colaboradores</h1>
    </header>
    
    <div class="user-info">
        <div class="user-avatar"><%=user.getNombreCompleto().charAt(0)%></div>
        <div class="user-details">
            <h3><%=user.getNombreCompleto()%></h3>
            <p><%=user.getIdRol().getNombre()%></p>
        </div>
    </div>

    <form action="/coordinador/filtrarColaborador" method="post">
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

<table class="table-striped">
    <thead>
        <tr>
            <th>COLABORADOR</th>
            <th>DOMICILIO</th>
            <th>LOCALIDAD</th>
            <th>COLABORADORA EN</th>
            <th>COORDINADOR</th>
            <th>CONTACTO PRINCIPAL</th>
            <th>OBSERVACIONES</th>
        </tr>
    </thead>
    <tbody>

    <%
        for (Colaborador colaborador : colaboradores) {
    %>
    <tr>
        <td><a href="/coordinador/editarColaborador?id=<%=colaborador.getId()%>"><%=colaborador.getNombreEntidad()%></a></td>
        <td><%=colaborador.getDomicilio()%></td>
        <td><%=colaborador.getLocalidad()%></td>
        <td><%=colaborador.getZonaGeografica()%></td>
        <%
            String coordinadorNombre = "";
            for (UsuarioColaborador relacion : relaciones) {
                if (colaborador.getId().equals(relacion.getColaborador().getId())) {
                    coordinadorNombre = relacion.getUsuario().getNombreCompleto();
                    break;
                }
            }
        %>
        <td><%=coordinadorNombre%></td>
        <td><%=colaborador.getContactoNom()%> (<%=colaborador.getContactoTlf()%>)</td>
        <td><%=colaborador.getObservaciones()%></td>
    </tr>
    <%}%>
    </tbody>
</table>

    <div class="mt-3">
        <a href="/coordinador/nuevoColaborador" class="btn btn-primary">Añadir Colaborador</a>
    </div>
    <div class="mt-3">
        <a href="/coordinador/" class="btn btn-secondary">Volver</a>
    </div>
</div>
</body>
</html>
