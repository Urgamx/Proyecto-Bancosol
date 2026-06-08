<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proyectobancosol.entity.Usuario" %>
<%@ page import="com.example.proyectobancosol.entity.Voluntario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Voluntario - BancoSol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    Voluntario voluntario = (Voluntario) request.getAttribute("voluntario");
    Integer idTienda = (Integer) request.getAttribute("idTienda");
%>

    <div class="mb-3">
        <%if(idTienda != null){%>
            <a href="/resp-tienda/tienda?id=<%=idTienda%>" class="btn btn-secondary">Volver a Detalles de Tienda</a>
        <%}else{%>
            <a href="/resp-tienda/" class="btn btn-secondary">Volver al Panel</a>
        <%}%>
    </div>
    
    <header>
        <h1>Información del Voluntario</h1>
    </header>
    
    <%if(voluntario != null){%>
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Datos Personales</h2>
            </div>
            <div class="card-body">
                <div class="mb-2">
                    <strong>Nombre:</strong> <span class="text-primary"><%=voluntario.getNombre()%></span>
                </div>
                
                <div class="mb-2">
                    <strong>Email:</strong> <span class="text-primary"><%=voluntario.getEmail()%></span>
                </div>
                
                <div class="mb-2">
                    <strong>Teléfono:</strong> <span class="text-primary"><%=voluntario.getTelefono()%></span>
                </div>
            </div>
        </div>

        <%if(voluntario.getIdColaborador() != null){%>
            <div class="card mt-3">
                <div class="card-header" style="background: linear-gradient(135deg, #ff9800 0%, #e68900 100%);">
                    <h2 class="card-title">Información de la Entidad Colaboradora</h2>
                </div>
                <div class="card-body">
                    <div class="mb-2">
                        <strong>Nombre Entidad:</strong> <span class="text-primary"><%=voluntario.getIdColaborador().getNombreEntidad()%></span>
                    </div>
                    
                    <div class="mb-2">
                        <strong>Email Entidad:</strong> <span class="text-primary"><%=voluntario.getIdColaborador().getEmail()%></span>
                    </div>
                    
                    <div class="mb-2">
                        <strong>Teléfono Entidad:</strong> <span class="text-primary"><%=voluntario.getIdColaborador().getContactoTlf()%></span>
                    </div>

                    <%if(voluntario.getIdColaborador().getDomicilio() != null && !voluntario.getIdColaborador().getDomicilio().isEmpty()){%>
                        <div class="mb-2">
                            <strong>Dirección:</strong> <span class="text-primary"><%=voluntario.getIdColaborador().getDomicilio()%></span>
                        </div>
                    <%}%>
                </div>
            </div>
        <%}%>
    <%}else{%>
        <div class="alert alert-danger">
            <strong>El voluntario no fue encontrado.</strong>
        </div>
    <%}%>
</div>

</body>
</html>
