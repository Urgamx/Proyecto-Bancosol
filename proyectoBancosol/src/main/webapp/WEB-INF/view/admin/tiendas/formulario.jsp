<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${modo} tienda</title>
</head>
<body>

<h1>${modo} tienda</h1>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form method="post" action="/admin/tiendas/guardar">
    <input type="hidden" name="id" value="${tienda.id}">

    <p>
        <label>Cadena</label><br>
        <select name="idCadena" required>
            <option value="">Seleccione una opcion</option>
            <c:forEach var="cadena" items="${cadenas}">
                <option value="${cadena.id}" <c:if test="${tienda.idCadena == cadena.id}">selected</c:if>>
                        ${cadena.nombre}
                </option>
            </c:forEach>
        </select>
    </p>

    <p>
        <label>Nombre</label><br>
        <input type="text" name="nombre" value="${tienda.nombre}" required maxlength="150">
    </p>

    <p>
        <label>Direccion</label><br>
        <input type="text" name="direccion" value="${tienda.direccion}" required maxlength="255">
    </p>

    <p>
        <label>Codigo postal</label><br>
        <input type="text" name="codPostal" value="${tienda.codPostal}" required maxlength="5">
    </p>

    <button type="submit">Guardar</button>
    <a href="/admin/tiendas">Cancelar</a>
</form>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>