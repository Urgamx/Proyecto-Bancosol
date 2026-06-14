<%@ page import="com.example.proyectobancosol.entity.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 17/05/2026
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%--
Página JSP que muestra el panel de coordinador.

Autores:
- David Vilaseca Pareja: 25%
- IA Generativa: 75%

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomePage - Coordinador</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<% Usuario user = (Usuario) session.getAttribute("usuario"); %>
<div class="container">
    <header>
        <h1>Panel de Coordinador</h1>
    </header>
    
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">Bienvenido</h2>
        </div>
        <div class="card-body">
            <div class="user-info">
                <div class="user-avatar"><%=user.getNombreCompleto().charAt(0)%></div>
                <div class="user-details">
                    <h3><%=user.getNombreCompleto()%></h3>
                    <p>Coordinador</p>
                </div>
            </div>
            
            <h3 class="mt-3">Funciones disponibles:</h3>
            <ul class="list-group mt-2">
                <li class="list-group-item">
                    <a href="colaborador" class="btn btn-primary btn-sm">Lista de Colaboradores</a>
                </li>
                <li class="list-group-item">
                    <a href="asignacionVoluntarios" class="btn btn-secondary btn-sm">Asignación de Voluntarios</a>
                </li>
            </ul>
        </div>
    </div>
    
    <div class="card mt-3">
        <div class="card-body text-center">
            <a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
        </div>
    </div>
</div>
</body>
</html>
