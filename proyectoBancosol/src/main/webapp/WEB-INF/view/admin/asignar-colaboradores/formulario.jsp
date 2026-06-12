<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asignar colaboradores</title>
</head>
<body>

<h1>Asignar colaboradores</h1>

<p>Coordinador: ${coordinador.nombreCompleto}</p>
<p>Email: ${coordinador.email}</p>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form method="post" action="/admin/asignar-colaboradores/guardar">
    <input type="hidden" name="idCoordinador" value="${asignacion.idCoordinador}">

    <p>Colaboradores disponibles</p>

    <c:forEach var="colaborador" items="${colaboradores}">
        <c:set var="marcada" value="false" />

        <c:forEach var="idColaborador" items="${asignacion.idsColaboradores}">
            <c:if test="${idColaborador == colaborador.id}">
                <c:set var="marcada" value="true" />
            </c:if>
        </c:forEach>

        <p>
            <label>
                <input type="checkbox" name="idsColaboradores" value="${colaborador.id}" <c:if test="${marcada}">checked</c:if>>
                    ${colaborador.nombreEntidad} - ${colaborador.email} - ${colaborador.localidad}
            </label>
        </p>
    </c:forEach>

    <button type="submit">Guardar</button>
    <a href="/admin/coordinadores">Cancelar</a>
</form>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>