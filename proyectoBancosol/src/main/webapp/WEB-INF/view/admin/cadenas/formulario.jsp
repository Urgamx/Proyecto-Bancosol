<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${modo} cadena</title>
</head>
<body>

<h1>${modo} cadena</h1>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form method="post" action="/admin/cadenas/guardar">
    <input type="hidden" name="id" value="${cadena.id}">

    <p>
        <label>Nombre</label><br>
        <input type="text" name="nombre" value="${cadena.nombre}" required maxlength="150">
    </p>

    <p>
        <label>Persona contacto</label><br>
        <input type="text" name="personaContacto" value="${cadena.personaContacto}" maxlength="150">
    </p>

    <p>
        <label>Telefono contacto</label><br>
        <input type="text" name="telefonoContacto" value="${cadena.telefonoContacto}" maxlength="20">
    </p>

    <button type="submit">Guardar</button>
    <a href="/admin/cadenas">Cancelar</a>
</form>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>