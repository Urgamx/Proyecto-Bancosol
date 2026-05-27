<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Tienda" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles de Tienda - Bancosol</title>
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
        h2 {
            color: #0066cc;
            margin-top: 30px;
        }
        .tienda-info {
            background-color: #e8f4f8;
            padding: 15px;
            margin-bottom: 20px;
            border-left: 4px solid #0066cc;
            border-radius: 3px;
        }
        .tienda-info p {
            margin: 8px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
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
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 3px;
            resize: vertical;
        }
        button {
            background-color: #0066cc;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: #0052a3;
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
        .incident-form {
            background-color: #fff3cd;
            padding: 15px;
            margin-top: 15px;
            border-left: 4px solid #ff9800;
            border-radius: 3px;
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
        <a href="/resp-tienda/">Volver al Panel</a>
    </div>
    
    <h1>Detalles de Tienda</h1>
    
    <div class="tienda-info">
        <p><strong>Tienda:</strong> <%=tienda.getNombre()%></p>
        <p><strong>Direccion:</strong> <%=tienda.getDireccion()%></p>
        <p><strong>Codigo Postal:</strong> <%=tienda.getCodPostal()%></p>
        <p><strong>Cadena:</strong> <%=tienda.getIdCadena().getNombre()%></p>
    </div>

    <h2>Voluntarios Asignados y Turnos</h2>
    
    <%if(turnos != null && turnos.size() > 0){%>
        <table border="1">
            <tr>
                <th>VOLUNTARIO</th>
                <th>CAMPANA</th>
                <th>DIA</th>
                <th>FRANJA</th>
                <th>HORARIO</th>
                <th>INCIDENCIA</th>
                <th>ACCIONES</th>
            </tr>

            <%for(AsignacionTurno turno : turnos){%>
            <tr>
                <td><%=turno.getIdVoluntario().getNombre()%></td>
                <td><%=turno.getIdCampana().getTipoDeCampana().getNombre()%></td>
                <td><%=turno.getDia()%></td>
                <td><%=turno.getFranja()%></td>
                <td><%=turno.getHoraInicio()%> - <%=turno.getHoraFin()%></td>
                <td>
                    <%if(turno.getIncidencia() != null){%>
                        <span style="color: red; font-weight: bold;">✓ SÍ</span><br>
                        <small><%=turno.getIncidencia().getDescripcion()%></small>
                    <%}else{%>
                        <span style="color: green;">Sin incidencias</span>
                    <%}%>
                </td>
                <td>
                    <%if(turno.getIncidencia() == null){%>
                        <a href="/resp-tienda/registrar-incidencia?id=<%=turno.getId()%>&tienda=<%=tienda.getId()%>">Reportar</a>
                    <%}else{%>
                        <small>Incidencia registrada</small>
                    <%}%>
                </td>
            </tr>
            <%}%>
        </table>
    <%}else{%>
        <p style="color: #ff9800;"><strong>No hay voluntarios asignados a esta tienda.</strong></p>
    <%}%>
</div>

</body>
</html>
