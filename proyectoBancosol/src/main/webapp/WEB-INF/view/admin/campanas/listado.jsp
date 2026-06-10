<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Campanas</title>
</head>
<body>

<h1>Campanas</h1>

<p><a href="/admin">Volver al panel</a></p>
<p><a href="/admin/campanas/nuevo">Nueva campana</a></p>

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
    <th>Tipo</th>
    <th>Fecha</th>
    <th>Estado</th>
    <th>Cadenas participantes</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="campana" items="${campanas}">
    <tr>
      <td>${campana.id}</td>
      <td>${campana.tipoCampana}</td>
      <td>${campana.fecha}</td>
      <td>${campana.activo}</td>
      <td>${campana.cadenasParticipantes}</td>
      <td>
        <a href="/admin/campanas/editar?id=${campana.id}">Editar</a>
        <form method="post" action="/admin/campanas/eliminar" style="display:inline">
          <input type="hidden" name="id" value="${campana.id}">
          <button type="submit">Eliminar</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<p><a href="/logout">Cerrar sesion</a></p>

</body>
</html>