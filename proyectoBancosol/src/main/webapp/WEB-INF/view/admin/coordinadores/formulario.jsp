<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>${modo} coordinador</title>
</head>
<body>

<h1>${modo} coordinador</h1>

<c:if test="${not empty error}">
  <p>${error}</p>
</c:if>

<form method="post" action="/admin/coordinadores/guardar">
  <input type="hidden" name="id" value="${coordinador.id}">

  <p>
    <label>Nombre</label><br>
    <input type="text" name="nombreCompleto" value="${coordinador.nombreCompleto}" required maxlength="150">
  </p>

  <p>
    <label>Email</label><br>
    <input type="email" name="email" value="${coordinador.email}" required maxlength="150">
  </p>

  <p>
    <label>Contrasena</label><br>
    <input type="password" name="password" value="">
  </p>

  <p>
    <label>Estado</label><br>
    <select name="activo">
      <option value="1" <c:if test="${coordinador.activo == 1}">selected</c:if>>Activo</option>
      <option value="0" <c:if test="${coordinador.activo != 1}">selected</c:if>>Inactivo</option>
    </select>
  </p>

  <button type="submit">Guardar</button>
  <a href="/admin/coordinadores">Cancelar</a>
</form>

<form method="get" action="/admin">
  <button type="submit">Volver al panel</button>
</form>

</body>
</html>