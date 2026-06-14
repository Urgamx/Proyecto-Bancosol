<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${modo} Responsable</title>
</head>
<body>

<h1>${modo} Responsable de Tienda</h1>

<form method="post" action="/admin/resp-tienda/guardar">
    <input type="hidden" name="id" value="${responsable.id}">

    <p>
        <label>Nombre Completo</label><br>
        <input type="text" name="nombre" value="${responsable.nombre}" required>
    </p>

    <p>
        <label>Email</label><br>
        <input type="email" name="email" value="${responsable.email}" required>
    </p>

    <p>
        <label>Contraseña</label><br>
        <input type="password" name="password" placeholder="Dejar en blanco para no cambiar">
    </p>

    <button type="submit">Guardar</button>
    <a href="/admin/resp-tienda">Cancelar</a>
</form>

</body>
</html>