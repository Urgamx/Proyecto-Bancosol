<%--
Marta Vegas: 70%
IA: 30%
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head><meta charset="UTF-8"><title>Responsables de Entidad</title></head>
<body>
<h1>Responsables de Entidad</h1>

<form method="get" action="/admin/resp-entidad" style="margin-bottom: 20px;">
    <input type="text" name="filtro" value="${filtro}" placeholder="Buscar por nombre...">
    <button type="submit">Filtrar</button>
    <a href="/admin/resp-entidad">Limpiar</a>
</form>

<p><a href="/admin/resp-entidad/nuevo">Nuevo responsable</a></p>

<c:if test="${not empty mensaje}"><p style="color: green;">${mensaje}</p></c:if>

<table border="1" cellpadding="8" cellspacing="0">
    <thead><tr><th>Id</th><th>Nombre</th><th>Email</th><th>Acciones</th></tr></thead>
    <tbody>
    <c:forEach var="r" items="${responsables}">
        <tr>
            <td>${r.id}</td>
            <td>${r.nombreCompleto}</td>
            <td>${r.email}</td>
            <td>
                <a href="/admin/resp-entidad/editar?id=${r.id}">Editar</a>
                <form method="post" action="/admin/resp-entidad/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${r.id}">
                    <button type="submit" onclick="return confirm('¿Seguro?')">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><a href="/admin">Volver al panel</a>
</body>
</html>