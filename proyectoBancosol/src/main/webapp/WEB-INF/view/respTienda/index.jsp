<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Responsable de Tienda - Bancosol</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #0066cc;
            padding-bottom: 10px;
        }
        .user-info {
            background-color: #e8f4f8;
            padding: 10px;
            margin-bottom: 20px;
            border-left: 4px solid #0066cc;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #0066cc;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        a {
            color: #0066cc;
            text-decoration: none;
            padding: 5px 10px;
            background-color: #e8f4f8;
            border-radius: 3px;
        }
        a:hover {
            background-color: #d0e8f0;
        }
        .logout {
            float: right;
            background-color: #cc3333;
            color: white;
            padding: 8px 15px;
            border-radius: 3px;
        }
        .logout:hover {
            background-color: #bb0000;
        }
    </style>
</head>
<body>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas");
%>

<div class="container">
    <h1>Panel de Responsable de Tienda</h1>
    <a href="/logout" class="logout">Cerrar Sesion</a>
    
    <div class="user-info">
        <strong>Usuario:</strong> <%=user.getNombreCompleto()%> <br>
        <strong>Rol:</strong> <%=user.getIdRol().getNombre()%>
    </div>

    <h2>Mi Tienda Asignada</h2>
    
    <%if(tiendas != null && tiendas.size() > 0){%>
        <table border="1">
            <tr>
                <th>TIENDA</th>
                <th>DIRECCION</th>
                <th>CODIGO POSTAL</th>
                <th>ACCIONES</th>
            </tr>
            <%for(Tienda tienda : tiendas){%>
            <tr>
                <td><%=tienda.getNombre()%></td>
                <td><%=tienda.getDireccion()%></td>
                <td><%=tienda.getCodPostal()%></td>
                <td>
                    <a href="/resp-tienda/tienda?id=<%=tienda.getId()%>">Ver Detalles</a> |
                    <a href="/resp-tienda/voluntarios?id=<%=tienda.getId()%>">Ver Voluntarios</a>
                </td>
            </tr>
            <%}%>
        </table>
    <%}else{%>
        <p style="color: red;"><strong>No hay tiendas asignadas.</strong></p>
    <%}%>
</div>

</body>
</html>
