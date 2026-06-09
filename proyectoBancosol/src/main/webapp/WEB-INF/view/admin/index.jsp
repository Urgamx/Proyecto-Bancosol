<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administrador - Bancosol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Panel Administrador</h1>
    </header>

    <div class="card">
        <div class="card-header">
            <h2 class="card-title">Funciones de Administrador</h2>
        </div>
        <div class="card-body">
            <%-- CAMBIO: muestra el usuario de sesión recibido desde AdminController --%>
            <p>Bienvenido, ${usuarioSesion.nombreCompleto}.</p>
            <p>Rol activo: ${usuarioSesion.rol}</p>
            <p>Desde aquí puede gestionar los elementos del sistema.</p>
        </div>
    </div>

    <div class="card mt-3">
        <div class="card-body text-center">
            <a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
        </div>
    </div>
</div>
</body>
</html>