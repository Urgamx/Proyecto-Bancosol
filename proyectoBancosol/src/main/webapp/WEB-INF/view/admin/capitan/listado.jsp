<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Capitanes</title>
</head>
<body>

<h1>Capitanes</h1>

<p><a href="/admin">Volver al panel</a></p>
<p><a href="/admin/capitanes/nuevo">Nuevo Capitan</a></p>

<c:if test="${not empty mensaje}">
    <p>${mensaje}</p>
</c:if>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<h2>Capitanes</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Email</th>
        <th>Estado</th>
        <th>Campanas</th>
        <th>Tiendas</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="capitan" items="${capitanes}">
        <tr>
            <td>${capitan.id}</td>
            <td>${capitan.nombreCompleto}</td>
            <td>${capitan.email}</td>
            <td>
                <c:choose>
                    <c:when test="${capitan.activo == 1}">
                        Activo
                    </c:when>
                    <c:otherwise>
                        Inactivo
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:forEach var="campana" items="${capitan.nombresCampanas}" varStatus="status">
                    ${campana}${not status.last ? ', ' : ''}
                </c:forEach>
                <c:if test="${empty capitan.nombresCampanas}">
                    Ninguna
                </c:if>
            </td>
            <td>
                <c:forEach var="tienda" items="${capitan.nombresTiendas}" varStatus="status">
                    ${tienda}${not status.last ? ', ' : ''}
                </c:forEach>
                <c:if test="${empty capitan.nombresTiendas}">
                    Ninguna
                </c:if>
            </td>
            <td>
                <a href="/admin/capitanes/editar?id=${capitan.id}">Editar</a>
                <form method="post" action="/admin/capitanes/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${capitan.id}">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty capitanes}">
        <tr>
            <td colspan="7">No hay capitanes registrados en el sistema.</td>
        </tr>
    </c:if>
    </tbody>
</table>

</body>
</html>