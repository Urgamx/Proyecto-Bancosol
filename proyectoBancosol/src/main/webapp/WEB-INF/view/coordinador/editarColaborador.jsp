<%@ page import="com.example.proyectobancosol.dto.request.ColaboradorRequestDTO" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 18/05/2026
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>

<%--
Página JSP que muestra el editar de colaborador.

Autores:
- David Vilaseca Pareja: 85%
- IA Generativa: 15%

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<%
    ColaboradorRequestDTO colaborador = (ColaboradorRequestDTO) request.getAttribute("colaborador");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Colaborador</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Editar Colaborador</h1>
    </header>
    
    <div class="card">
        <div class="card-header">
            <h2 class="card-title"><%=colaborador.getNombreEntidad()%></h2>
        </div>
        <div class="card-body">
            <form method="post" action="/coordinador/guardarColaborador">
                <input type="hidden" name="id" value="<%=colaborador.getId()%>">

                <input type="hidden" name="nombreEntidad" value="<%=colaborador.getNombreEntidad()%>">
                <input type="hidden" name="domicilio" value="<%=colaborador.getDomicilio()%>">
                <input type="hidden" name="localidad" value="<%=colaborador.getLocalidad()%>">
                <input type="hidden" name="zonaGeografica" value="<%=colaborador.getZonaGeografica()%>">
                <input type="hidden" name="observaciones" value="<%=colaborador.getObservaciones()%>">
                <input type="hidden" name="codPostal" value="<%=colaborador.getCodPostal()%>">
                
                <div class="form-group">
                    <label><strong>Domicilio:</strong></label>
                    <p><%=colaborador.getDomicilio()%></p>
                </div>
                
                <div class="form-group">
                    <label><strong>Código Postal - Localidad:</strong></label>
                    <p><%=colaborador.getCodPostal()%> - <%=colaborador.getLocalidad()%></p>
                </div>
                
                <div class="form-group">
                    <label><strong>Colabora en:</strong></label>
                    <p><%=colaborador.getZonaGeografica()%></p>
                </div>
                
                <h3 class="mt-3">Datos de Contacto</h3>
                
                <div class="form-group">
                    <label for="contactoNom">Nombre:</label>
                    <input type="text" id="contactoNom" name="contactoNom" value="<%=colaborador.getContactoNom() != null ? colaborador.getContactoNom() : ""%>" required>
                </div>
                
                <div class="form-group">
                    <label for="contactoTlf">Teléfono:</label>
                    <input type="text" id="contactoTlf" name="contactoTlf" value="<%=colaborador.getContactoTlf() != null ? colaborador.getContactoTlf() : ""%>" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%=colaborador.getEmail() != null ? colaborador.getEmail() : ""%>" required>
                </div>
                <br>
                <div class="mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    <a href="/coordinador/colaborador" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
