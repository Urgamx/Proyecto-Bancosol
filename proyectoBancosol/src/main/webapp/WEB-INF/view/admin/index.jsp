<%--
Panel de admin con un listado de cruds posibles y tareas pendientes.

Autores:
- Jesus Moreno Carmona: 25%
- IA Generativa: 75%
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Administrador - Bancosol</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1>Panel Administrador</h1>

<p>Bienvenido, ${usuarioSesion.nombreCompleto}</p>
<p>Rol activo: ${usuarioSesion.rol}</p>

<hr>

<h2>Menu de administracion</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Modulo</th>
        <th>Descripcion</th>
        <th>Ir al panel</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="item" items="${menuAdmin}">
        <tr>
            <td>${item.titulo}</td>
            <td>${item.descripcion}</td>
            <td>
                <c:choose>
                    <c:when test="${item.url != '#'}">
                        <a href="${item.url}">Editar</a>
                    </c:when>
                    <c:otherwise>
                        Pendiente
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>

<h2>Tareas pendientes de revision</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Modulo</th>
        <th>Tarea</th>
        <th>Total</th>
        <th>Detalle</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="validacion" items="${validaciones}">
        <tr>
            <td>${validacion.modulo}</td>
            <td>${validacion.validacion}</td>
            <td>${validacion.total}</td>
            <td>${validacion.detalle}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>

<p>
    <a href="/logout">Cerrar sesion</a>
</p>

</body>
</html>