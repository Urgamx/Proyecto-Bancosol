<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Asignacion de tiendas</title>
</head>
<body>

<h1>Asignacion de tiendas a coordinadores</h1>

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
    <th>Tiendas asignadas</th>
    <th>Accion</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="coordinador" items="${coordinadores}">
    <tr>
      <td>${coordinador.idCoordinador}</td>
      <td>${coordinador.nombreCompleto}</td>
      <td>${coordinador.email}</td>
      <td>${coordinador.tiendasAsignadas}</td>
      <td>
        <a href="/admin/asignar-tiendas/editar?idCoordinador=${coordinador.idCoordinador}">Editar</a>
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