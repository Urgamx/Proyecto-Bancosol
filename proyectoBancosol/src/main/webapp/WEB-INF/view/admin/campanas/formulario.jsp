<%--
Formulario de creacion y edicion de campañas.

Autores:
- Jesus Moreno Carmona: 25%
- IA: 75%
--%>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${modo} campana</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1>${modo} campana</h1>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form method="post" action="/admin/campanas/guardar">
    <input type="hidden" name="id" value="${campana.id}">

    <p>
        <label>Tipo de campana</label><br>
        <select name="idTipoCampana" required>
            <option value="">Seleccione una opcion</option>
            <c:forEach var="tipo" items="${tipos}">
                <option value="${tipo.id}" <c:if test="${campana.idTipoCampana == tipo.id}">selected</c:if>>
                        ${tipo.nombre}
                </option>
            </c:forEach>
        </select>
    </p>

    <p>
        <label>Fecha</label><br>
        <input type="date" name="fechaFormulario" value="${campana.fechaFormulario}" required>
    </p>

    <p>
        <label>Estado</label><br>
        <select name="activo">
            <option value="1" <c:if test="${campana.activo == 1}">selected</c:if>>Activa</option>
            <option value="0" <c:if test="${campana.activo != 1}">selected</c:if>>Inactiva</option>
        </select>
    </p>

    <p>Cadenas participantes</p>

    <c:forEach var="cadena" items="${cadenas}">
        <c:set var="marcada" value="false" />

        <c:forEach var="idCadena" items="${campana.idsCadenas}">
            <c:if test="${idCadena == cadena.id}">
                <c:set var="marcada" value="true" />
            </c:if>
        </c:forEach>

        <p>
            <label>
                <input type="checkbox" name="idsCadenas" value="${cadena.id}" <c:if test="${marcada}">checked</c:if>>
                    ${cadena.nombre}
            </label>
        </p>
    </c:forEach>

    <button type="submit">Guardar</button>
    <a href="/admin/campanas">Cancelar</a>
</form>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>