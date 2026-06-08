<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.AsignacionTurno" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Incidencia - BancoSol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container" style="max-width: 600px;">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Integer idAsignacion = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
    Integer idTienda = Integer.parseInt(request.getParameter("tienda") != null ? request.getParameter("tienda") : "0");
%>

    <div class="mb-3">
        <a href="/resp-entidad/tienda?id=<%=idTienda%>" class="btn btn-secondary">Volver a Detalles</a>
    </div>
    
    <header>
        <h1>Registrar Incidencia</h1>
    </header>
    
    <div class="alert alert-warning">
        <strong>Nota:</strong> Por favor, describe con detalle el problema o incidencia que ocurrió durante el turno.
    </div>

    <div class="card">
        <div class="card-body">
            <p><strong>ID de Asignación:</strong> <%=idAsignacion%></p>
            <p><strong>Usuario:</strong> <%=user.getNombreCompleto()%></p>
        </div>
    </div>

    <form method="POST" action="/resp-entidad/registrarIncidencia" class="mt-3">
        <div class="form-group">
            <label for="descripcion">Descripción de la Incidencia:</label>
            <textarea id="descripcion" name="descripcion" placeholder="Describe detalladamente qué pasó, la hora, personas involucradas, etc..." required></textarea>
        </div>

        <input type="hidden" name="idAsignacion" value="<%=idAsignacion%>">
        <input type="hidden" name="idTienda" value="<%=idTienda%>">

        <div class="mt-3" style="display: flex; gap: 10px; justify-content: center;">
            <button type="submit" class="btn btn-warning">Registrar Incidencia</button>
            <a href="/resp-entidad/tienda?id=<%=idTienda%>" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

</body>
</html>
