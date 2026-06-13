<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cadenas</title>
</head>
<body>

<h1>Cadenas</h1>

<p><a href="/admin/cadenas/nuevo">Nueva cadena</a></p>

<c:if test="${not empty mensaje}">
    <p>${mensaje}</p>
</c:if>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form action="/admin/cadenas/filtrar" method="post">
    <label>Nombre:</label>
    <input type="text" name="nombre" value="${nombreSelected}">
    <button type="submit">Filtrar</button>
</form>

<a href="/admin/cadenas">Limpiar filtro</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Persona contacto</th>
        <th>Telefono contacto</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cadena" items="${cadenas}">
        <tr>
            <td>${cadena.id}</td>
            <td>${cadena.nombre}</td>
            <td>${cadena.personaContacto}</td>
            <td>${cadena.telefonoContacto}</td>
            <td>
                <a href="/admin/cadenas/editar?id=${cadena.id}">Editar</a>
                <form method="post" action="/admin/cadenas/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${cadena.id}">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>