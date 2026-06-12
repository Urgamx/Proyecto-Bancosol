<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Coordinadores</title>
</head>
<body>

<h1>Coordinadores</h1>

<p><a href="/admin/coordinadores/nuevo">Nuevo coordinador</a></p>

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
    <th>Nombre</th>
    <th>Email</th>
    <th>Estado</th>
    <th>Tiendas</th>
    <th>Colaboradores</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="coordinador" items="${coordinadores}">
    <tr>
      <td>${coordinador.id}</td>
      <td>${coordinador.nombreCompleto}</td>
      <td>${coordinador.email}</td>
      <td>${coordinador.activo}</td>
      <td>${coordinador.tiendasAsignadas}</td>
      <td>${coordinador.colaboradoresAsignados}</td>
      <td>
        <a href="/admin/coordinadores/editar?id=${coordinador.id}">Editar</a>
        <a href="/admin/asignar-tiendas/editar?idCoordinador=${coordinador.id}">Editar tiendas</a>
        <a href="/admin/asignar-colaboradores/editar?idCoordinador=${coordinador.id}">Editar colaboradores</a>
        <form method="post" action="/admin/coordinadores/eliminar" style="display:inline">
          <input type="hidden" name="id" value="${coordinador.id}">
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