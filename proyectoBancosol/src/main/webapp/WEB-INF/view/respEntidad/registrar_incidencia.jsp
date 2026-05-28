<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Incidencia - Bancosol</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 700px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #ff9800;
            padding-bottom: 10px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 3px;
            font-family: Arial, sans-serif;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
            min-height: 120px;
        }
        .info-box {
            background-color: #e8f4f8;
            padding: 15px;
            margin-bottom: 20px;
            border-left: 4px solid #0066cc;
            border-radius: 3px;
        }
        .info-box p {
            margin: 5px 0;
        }
        .button-group {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-top: 30px;
        }
        button, a.btn {
            padding: 12px 30px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
        }
        .btn-submit {
            background-color: #ff9800;
            color: white;
        }
        .btn-submit:hover {
            background-color: #e68900;
        }
        .btn-cancel {
            background-color: #999;
            color: white;
        }
        .btn-cancel:hover {
            background-color: #777;
        }
        .alert {
            background-color: #fff3cd;
            border: 1px solid #ffc107;
            color: #856404;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 3px;
        }
        .back-link {
            margin-bottom: 20px;
        }
        a {
            color: #0066cc;
            text-decoration: none;
        }
    </style>
</head>
<body>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Integer idAsignacion = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
    Integer idTienda = Integer.parseInt(request.getParameter("tienda") != null ? request.getParameter("tienda") : "0");
%>

<div class="container">
    <div class="back-link">
        <a href="/resp-entidad/tienda?id=<%=idTienda%>">Volver a Detalles</a>
    </div>
    
    <h1>Registrar Incidencia</h1>
    
    <div class="alert">
        <strong>Nota:</strong> Por favor, describe con detalle el problema o incidencia que ocurrio durante el turno.
    </div>

    <form method="POST" action="/resp-entidad/registrarIncidencia">
        <div class="info-box">
            <p><strong>ID de Asignacion:</strong> <%=idAsignacion%></p>
            <p><strong>Usuario:</strong> <%=user.getNombreCompleto()%></p>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripcion de la Incidencia:</label>
            <textarea id="descripcion" name="descripcion" placeholder="Describe detalladamente que paso, la hora, personas involucradas, etc..." required></textarea>
        </div>

        <input type="hidden" name="idAsignacion" value="<%=idAsignacion%>">
        <input type="hidden" name="idTienda" value="<%=idTienda%>">

        <div class="button-group">
            <button type="submit" class="btn-submit">Registrar Incidencia</button>
            <a href="/resp-entidad/tienda?id=<%=idTienda%>" class="btn btn-cancel">Cancelar</a>
        </div>
    </form>
</div>

</body>
</html>
