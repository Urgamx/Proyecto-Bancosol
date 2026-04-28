<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Bancosol - Responsable de Tienda</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <div class="alert alert-info">
        <strong>Rol: Responsable de Tienda</strong> - Permiso: Solo visualización de datos
    </div>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Listado de Tiendas Asignadas</h2>
        <a href="/responsable/voluntarios" class="btn btn-primary">Ver Mis Voluntarios</a>
    </div>

    <table class="table table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Código Postal</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lista}" var="tienda">
                <tr>
                    <td>${tienda.idTienda}</td>
                    <td>${tienda.nombre}</td>
                    <td>${tienda.direccion}</td>
                    <td>${tienda.codPostal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>