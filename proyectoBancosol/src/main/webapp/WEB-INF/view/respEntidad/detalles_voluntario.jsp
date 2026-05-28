<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Voluntario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles del Voluntario - Bancosol</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #0066cc;
            padding-bottom: 15px;
            text-align: center;
        }
        .back-link {
            margin-bottom: 20px;
        }
        a {
            color: #0066cc;
            text-decoration: none;
            padding: 8px 15px;
            background-color: #e8f4f8;
            border-radius: 3px;
        }
        a:hover {
            background-color: #d0e8f0;
        }
        .info-section {
            background-color: #e8f4f8;
            padding: 20px;
            margin-bottom: 20px;
            border-left: 5px solid #0066cc;
            border-radius: 3px;
        }
        .info-section h2 {
            color: #0066cc;
            margin-top: 0;
            border-bottom: 2px solid #0066cc;
            padding-bottom: 10px;
        }
        .info-field {
            margin: 12px 0;
            padding: 10px;
            background-color: white;
            border-radius: 3px;
        }
        .info-field strong {
            display: inline-block;
            width: 120px;
            color: #333;
        }
        .info-value {
            color: #0066cc;
            font-weight: bold;
        }
        .button-group {
            text-align: center;
            margin-top: 30px;
        }
        .btn {
            display: inline-block;
            padding: 10px 25px;
            margin: 5px;
            border-radius: 3px;
            text-decoration: none;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #0066cc;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0052a3;
        }
        .btn-secondary {
            background-color: #999;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #777;
        }
        .colaborador-info {
            background-color: #fff9e6;
            padding: 15px;
            border-left: 5px solid #ff9800;
            border-radius: 3px;
            margin-top: 20px;
        }
        .colaborador-info h3 {
            color: #ff9800;
            margin-top: 0;
        }
    </style>
</head>
<body>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Voluntario voluntario = (Voluntario) request.getAttribute("voluntario");
    Integer idTienda = (Integer) request.getAttribute("idTienda");
%>

<div class="container">
    <div class="back-link">
        <%if(idTienda != null){%>
            <a href="/resp-entidad/tienda?id=<%=idTienda%>">Volver a Detalles de Tienda</a>
        <%}else{%>
            <a href="/resp-entidad/">← Volver al Panel</a>
        <%}%>
    </div>
    
    <h1>Informacion del Voluntario</h1>
    
    <%if(voluntario != null){%>
        <div class="info-section">
            <h2>Datos Personales</h2>
            
            <div class="info-field">
                <strong>ID:</strong> <span class="info-value"><%=voluntario.getId()%></span>
            </div>
            
            <div class="info-field">
                <strong>Nombre:</strong> <span class="info-value"><%=voluntario.getNombre()%></span>
            </div>
            
            <div class="info-field">
                <strong>Email:</strong> <span class="info-value"><%=voluntario.getEmail()%></span>
            </div>
            
            <div class="info-field">
                <strong>Telefono:</strong> <span class="info-value"><%=voluntario.getTelefono()%></span>
            </div>
        </div>

        <%if(voluntario.getIdColaborador() != null){%>
            <div class="colaborador-info">
                <h3>Informacion de la Entidad Colaboradora</h3>
                
                <div class="info-field">
                    <strong>Nombre Entidad:</strong> <span class="info-value"><%=voluntario.getIdColaborador().getNombreEntidad()%></span>
                </div>
                
                <div class="info-field">
                    <strong>Email Entidad:</strong> <span class="info-value"><%=voluntario.getIdColaborador().getEmail()%></span>
                </div>
                
                <div class="info-field">
                    <strong>Telefono Entidad:</strong> <span class="info-value"><%=voluntario.getIdColaborador().getContactoTlf()%></span>
                </div>

                <%if(voluntario.getIdColaborador().getDomicilio() != null && !voluntario.getIdColaborador().getDomicilio().isEmpty()){%>
                    <div class="info-field">
                        <strong>Direccion:</strong> <span class="info-value"><%=voluntario.getIdColaborador().getDomicilio()%></span>
                    </div>
                <%}%>
            </div>
        <%}%>

        <div class="button-group">
            <%if(idTienda != null){%>
                <a href="/resp-entidad/tienda?id=<%=idTienda%>" class="btn btn-secondary">Volver a Tienda</a>
            <%}else{%>
                <a href="/resp-entidad/" class="btn btn-secondary">Volver al Panel</a>
            <%}%>
        </div>
    <%}else{%>
        <div style="color: red; font-size: 16px; text-align: center;">
            <strong>⚠ El voluntario no fue encontrado.</strong>
        </div>
        <div class="button-group">
            <a href="/resp-entidad/" class="btn btn-secondary">Volver al Panel</a>
        </div>
    <%}%>
</div>

</body>
</html>
