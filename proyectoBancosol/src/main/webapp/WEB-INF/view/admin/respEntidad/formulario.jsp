<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head><meta charset="UTF-8"><title>${modo} Responsable</title></head>
<body>
    <h1>${modo} Responsable de Entidad</h1>
    <form method="post" action="/admin/resp-entidad/guardar">
        <input type="hidden" name="id" value="${responsable.id}">
        <p><label>Nombre:</label><br><input type="text" name="nombreCompleto" value="${responsable.nombreCompleto}" required></p>
        <p><label>Email:</label><br><input type="email" name="email" value="${responsable.email}" required></p>
        <p><label>Estado:</label><br>
           <select name="activo">
               <option value="1" <c:if test="${responsable.activo == 1}">selected</c:if>>Activo</option>
               <option value="0" <c:if test="${responsable.activo != 1}">selected</c:if>>Inactivo</option>
           </select></p>
        <button type="submit">Guardar</button>
        <a href="/admin/resp-entidad">Cancelar</a>
    </form>
</body>
</html>