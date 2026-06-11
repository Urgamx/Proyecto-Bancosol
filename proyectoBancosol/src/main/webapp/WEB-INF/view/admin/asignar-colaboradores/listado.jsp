<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asignacion de colaboradores</title>
</head>
<body>

<h1>Asignacion de colaboradores a coordinadores</h1>

<p><a href="/admin">Volver al panel</a></p>

<c:if test="${not empty mensaje}">
    <p>${mensaje}</p>
</c:if>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Coordinador</th>
        <th>Email</th>
        <th>Colaboradores asignados</th>
        <th>Accion</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="coordinador" items="${coordinadores}">
        <tr>
            <td>${coordinador.idCoordinador}</td>
            <td>${coordinador.nombreCompleto}</td>
            <td>${coordinador.email}</td>
            <td>${coordinador.colaboradoresAsignados}</td>
            <td>
                <a href="/admin/asignar-colaboradores/editar?idCoordinador=${coordinador.idCoordinador}">Editar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/logout">Cerrar sesion</a></p>

</body>
</html>