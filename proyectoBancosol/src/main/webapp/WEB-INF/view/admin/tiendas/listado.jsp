<%--
listado de tiendas en panel admin
Autores:
- Jesus Moreno: 25%
- IA: 75%
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tiendas</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1>Tiendas</h1>

<p><a href="/admin/tiendas/nuevo">Nueva tienda</a></p>

<c:if test="${not empty mensaje}">
    <p>${mensaje}</p>
</c:if>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form action="/admin/tiendas/filtrar" method="post">
    <label>Nombre:</label>
    <input type="text" name="nombre" value="${nombreSelected}">

    <label>Cadena:</label>
    <select name="idCadena">
        <option value=""></option>
        <c:forEach var="cadena" items="${cadenas}">
            <option value="${cadena.id}" <c:if test="${idCadenaSelected == cadena.id}">selected</c:if>>
                    ${cadena.nombre}
            </option>
        </c:forEach>
    </select>

    <button type="submit">Filtrar</button>
</form>

<a href="/admin/tiendas">Limpiar filtro</a>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Cadena</th>
        <th>Nombre</th>
        <th>Direccion</th>
        <th>Localidad</th>
        <th>Codigo postal</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tienda" items="${tiendas}">
        <tr>
            <td>${tienda.id}</td>
            <td>${tienda.cadena}</td>
            <td>${tienda.nombre}</td>
            <td>${tienda.direccion}</td>
            <td>${tienda.localidad}</td>
            <td>${tienda.codPostal}</td>
            <td>
                <a href="/admin/tiendas/editar?id=${tienda.id}">Editar</a>
                <form method="post" action="/admin/tiendas/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${tienda.id}">
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