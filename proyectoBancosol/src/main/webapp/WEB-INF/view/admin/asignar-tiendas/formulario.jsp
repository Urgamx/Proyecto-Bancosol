<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asignar tiendas</title>
</head>
<body>

<h1>Asignar tiendas</h1>

<p>Coordinador: ${coordinador.nombreCompleto}</p>
<p>Email: ${coordinador.email}</p>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form method="post" action="/admin/asignar-tiendas/guardar">
    <input type="hidden" name="idCoordinador" value="${asignacion.idCoordinador}">

    <p>Tiendas disponibles</p>

    <c:forEach var="tienda" items="${tiendas}">
        <c:set var="marcada" value="false" />

        <c:forEach var="idTienda" items="${asignacion.idsTiendas}">
            <c:if test="${idTienda == tienda.id}">
                <c:set var="marcada" value="true" />
            </c:if>
        </c:forEach>

        <p>
            <label>
                <input type="checkbox" name="idsTiendas" value="${tienda.id}" <c:if test="${marcada}">checked</c:if>>
                    ${tienda.cadena} - ${tienda.nombre} - ${tienda.direccion}
            </label>
        </p>
    </c:forEach>

    <button type="submit">Guardar</button>
    <a href="/admin/asignar-tiendas">Cancelar</a>
</form>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>