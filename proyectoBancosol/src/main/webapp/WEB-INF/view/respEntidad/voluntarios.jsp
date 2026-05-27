<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voluntarios - Bancosol</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
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
        .tienda-info {
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
        .back-link {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Tienda tienda = (Tienda) request.getAttribute("tienda");
    List<AsignacionTurno> turnos = (List<AsignacionTurno>) request.getAttribute("turnos");
%>

<div class="container">
    <div class="back-link">
        <a href="/resp-entidad/">Volver al Panel</a>
    </div>
    
    <h1>Voluntarios Asignados - <%=tienda.getNombre()%></h1>
    
    <div class="tienda-info">
        <strong>Tienda:</strong> <%=tienda.getNombre()%> | 
        <strong>Direccion:</strong> <%=tienda.getDireccion()%>
    </div>

    <%if(turnos != null && turnos.size() > 0){%>
        <table border="1">
            <tr>
                <th>VOLUNTARIO</th>
                <th>CAMPAÑA</th>
                <th>DÍA</th>
                <th>FRANJA</th>
                <th>HORARIO</th>
                <th>ESTADO</th>
            </tr>

            <%for(AsignacionTurno turno : turnos){%>
            <tr>
                <td>
                    <strong><%=turno.getIdVoluntario().getNombre()%></strong>
                </td>
                <td><%=turno.getIdCampana().getTipoDeCampana().getNombre()%></td>
                <td><%=turno.getDia()%></td>
                <td><%=turno.getFranja()%></td>
                <td><%=turno.getHoraInicio()%> - <%=turno.getHoraFin()%></td>
                <td>
                    <%if(turno.getIncidencia() != null){%>
                        <span style="color: red; font-weight: bold;">INCIDENCIA REGISTRADA</span>
                    <%}else{%>
                        <span style="color: green; font-weight: bold;">SIN INCIDENCIAS</span>
                    <%}%>
                </td>
            </tr>
            <%}%>
        </table>
    <%}else{%>
        <p style="color: #ff9800; font-size: 16px;"><strong>No hay voluntarios asignados a esta tienda.</strong></p>
    <%}%>

    <div style="margin-top: 30px;">
        <a href="/resp-entidad/tienda?id=<%=tienda.getId()%>">Ver Detalles de Turnos</a>
    </div>
</div>

</body>
</html>
