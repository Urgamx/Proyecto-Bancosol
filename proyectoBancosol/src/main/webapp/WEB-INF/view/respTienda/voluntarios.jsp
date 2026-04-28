<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Bancosol - Listado de Voluntarios</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Supervisión de Voluntarios</h2>
        <a href="/responsable/tiendas" class="btn btn-outline-secondary">Volver a Tiendas</a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>ID Colaborador</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voluntarios}" var="v">
                        <tr>
                            <td>${v.idVoluntario}</td>
                            <td>${v.nombre}</td>
                            <td>${v.email}</td>
                            <td>${v.telefono}</td>
                            <td>${v.idColaborador}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>