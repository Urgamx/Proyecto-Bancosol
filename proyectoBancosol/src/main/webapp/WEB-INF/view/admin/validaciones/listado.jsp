<%--
Interfaz basica de validaciones basicas

Autores:
- Jesus Moreno Carmona: 25%
- IA Generativa: 75%
--%>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Validaciones basicas</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1>Validaciones basicas</h1>

<table border="1" cellpadding="8" cellspacing="0">
  <thead>
  <tr>
    <th>Modulo</th>
    <th>Validacion</th>
    <th>Total</th>
    <th>Estado</th>
    <th>Detalle</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="validacion" items="${validaciones}">
    <tr>
      <td>${validacion.modulo}</td>
      <td>${validacion.validacion}</td>
      <td>${validacion.total}</td>
      <td>${validacion.estado}</td>
      <td>${validacion.detalle}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<form method="get" action="/admin">
  <button type="submit">Volver al panel</button>
</form>

</body>
</html>