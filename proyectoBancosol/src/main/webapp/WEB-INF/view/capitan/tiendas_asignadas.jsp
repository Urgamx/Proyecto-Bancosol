<%@ page import="java.util.List" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="com.example.proyectobancosol.entity.TiendaCampana" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel de Capitán - Bancosol</title>
</head>
<body>

<%
    List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas");
%>

<header>
    <h1>Mis Tiendas Asignadas: </h1>
</header>
    <table border="1">
        <tr>
            <th>CAMPANA</th>
            <th>NOMBRE</th>
            <th>DIRECCION : (CODIGO POSTAL)</th>
            <th></th>
        </tr>
            <%for(Tienda tienda : tiendas){%>
            <tr>
                <td>
                    <%for(TiendaCampana campana : tienda.getTiendasCampana()){%>
                    <%=campana.getCampana().getTipoDeCampana().getNombre()%>
                    <%}%>
                </td>
                <td><%=tienda.getNombre()%></td>
                <td><%=tienda.getDireccion()%> : (<%=tienda.getCodPostal()%>)</td>
                <td><a href="/capitan/asignacion_turnos?id=<%=tienda.getId()%>"> Asignacion de turnos</a></td>
            </tr>
        <%}%>
    </table>
</body>
</html>