<!DOCTYPE html>
<html>
<head>
    <title>Login - BancoSol</title>
</head>
<body>
<h1>Acceso</h1>

<form method="post" action="/login">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Contrasena" required>
    <button type="submit">Entrar</button>
</form>

<p>${error}</p>
</body>
</html>