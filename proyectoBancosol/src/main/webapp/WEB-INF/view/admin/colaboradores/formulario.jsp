<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>${modo} colaborador</title>
</head>
<body>

<h1>${modo} colaborador</h1>

<c:if test="${not empty error}">
  <p>${error}</p>
</c:if>

<form method="post" action="/admin/colaboradores/guardar">
  <input type="hidden" name="id" value="${colaborador.id}">

  <p>
    <label>Entidad</label><br>
    <input type="text" name="nombreEntidad" value="${colaborador.nombreEntidad}" required maxlength="150">
  </p>

  <p>
    <label>Email</label><br>
    <input type="email" name="email" value="${colaborador.email}" required maxlength="100">
  </p>

  <p>
    <label>Contacto</label><br>
    <input type="text" name="contactoNom" value="${colaborador.contactoNom}" maxlength="255">
  </p>

  <p>
    <label>Telefono</label><br>
    <input type="text" name="contactoTlf" value="${colaborador.contactoTlf}" maxlength="20">
  </p>

  <p>
    <label>Domicilio</label><br>
    <input type="text" name="domicilio" value="${colaborador.domicilio}" maxlength="255">
  </p>

  <p>
    <label>Localidad</label><br>
    <input type="text" name="localidad" value="${colaborador.localidad}" maxlength="255">
  </p>

  <p>
    <label>Zona geografica</label><br>
    <input type="text" name="zonaGeografica" value="${colaborador.zonaGeografica}" maxlength="255">
  </p>

  <p>
    <label>Codigo postal</label><br>
    <input type="text" name="codPostal" value="${colaborador.codPostal}" maxlength="255">
  </p>

  <p>
    <label>Observaciones</label><br>
    <textarea name="observaciones" maxlength="255" rows="5" cols="50">${colaborador.observaciones}</textarea>
  </p>

  <p>
    <label>Estado</label><br>
    <select name="estado" required>
      <option value="1" <c:if test="${colaborador.estado == 1}">selected</c:if>>Activo</option>
      <option value="2" <c:if test="${colaborador.estado == 2}">selected</c:if>>Pendiente</option>
      <option value="0" <c:if test="${colaborador.estado == 0}">selected</c:if>>Inactivo</option>
    </select>
  </p>

  <button type="submit">Guardar</button>
  <a href="/admin/colaboradores">Cancelar</a>
</form>

</body>
</html>