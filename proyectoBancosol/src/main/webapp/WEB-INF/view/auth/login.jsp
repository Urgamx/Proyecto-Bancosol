<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - BancoSol</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body class="login-page">
    <div class="login-container">
        <div class="login-header">
            <h1>BancoSol</h1>
            <p>Gestion de Voluntarios</p>
        </div>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form method="post" action="/login">
            <div class="form-group">
                <label for="email">Correo Electronico</label>
                <input 
                    type="email" 
                    id="email"
                    name="email" 
                    placeholder="tu@email.com" 
                    required
                    autofocus>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input 
                    type="password" 
                    id="password"
                    name="password" 
                    placeholder="Tu password" 
                    required>
            </div>

            <button type="submit" class="submit-btn">Iniciar Sesion</button>
        </form>

        <div class="login-footer">
            <p>© 2026 BancoSol. Todos los derechos reservados.</p>
        </div>
    </div>
</body>
</html>