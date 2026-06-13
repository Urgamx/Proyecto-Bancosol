<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Colaboradores</title>
</head>
<body>

<h1>Colaboradores</h1>

<p><a href="/admin/colaboradores/nuevo">Nuevo colaborador</a></p>

<c:if test="${not empty mensaje}">
    <p>${mensaje}</p>
</c:if>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>

<form action="/admin/colaboradores/filtrar" method="post">
    <label>Entidad:</label>
    <input type="text" name="nombre" value="${nombreSelected}">

    <label>Estado:</label>
    <select name="estado">
        <option value=""></option>
        <option value="2" <c:if test="${estadoSelected == 2}">selected</c:if>>Pendiente</option>
        <option value="1" <c:if test="${estadoSelected == 1}">selected</c:if>>Activo</option>
        <option value="0" <c:if test="${estadoSelected == 0}">selected</c:if>>Inactivo</option>
    </select>

    <button type="submit">Filtrar</button>
</form>

<a href="/admin/colaboradores">Limpiar filtro</a>

<h2>Colaboradores pendientes</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Entidad</th>
        <th>Email</th>
        <th>Contacto</th>
        <th>Telefono</th>
        <th>Localidad</th>
        <th>Zona</th>
        <th>Coordinadores</th>
        <th>Voluntarios</th>
        <th>Turnos</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="colaborador" items="${pendientes}">
        <tr>
            <td>${colaborador.id}</td>
            <td>${colaborador.nombreEntidad}</td>
            <td>${colaborador.email}</td>
            <td>${colaborador.contactoNom}</td>
            <td>${colaborador.contactoTlf}</td>
            <td>${colaborador.localidad}</td>
            <td>${colaborador.zonaGeografica}</td>
            <td>${colaborador.coordinadoresAsignados}</td>
            <td>${colaborador.voluntarios}</td>
            <td>${colaborador.turnos}</td>
            <td>
                <a href="/admin/colaboradores/editar?id=${colaborador.id}">Editar</a>
                <form method="post" action="/admin/colaboradores/activar" style="display:inline">
                    <input type="hidden" name="id" value="${colaborador.id}">
                    <button type="submit">Activar</button>
                </form>
                <form method="post" action="/admin/colaboradores/rechazar" style="display:inline">
                    <input type="hidden" name="id" value="${colaborador.id}">
                    <button type="submit">Rechazar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Colaboradores activos</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Entidad</th>
        <th>Email</th>
        <th>Contacto</th>
        <th>Telefono</th>
        <th>Localidad</th>
        <th>Zona</th>
        <th>Coordinadores</th>
        <th>Voluntarios</th>
        <th>Turnos</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="colaborador" items="${activos}">
        <tr>
            <td>${colaborador.id}</td>
            <td>${colaborador.nombreEntidad}</td>
            <td>${colaborador.email}</td>
            <td>${colaborador.contactoNom}</td>
            <td>${colaborador.contactoTlf}</td>
            <td>${colaborador.localidad}</td>
            <td>${colaborador.zonaGeografica}</td>
            <td>${colaborador.coordinadoresAsignados}</td>
            <td>${colaborador.voluntarios}</td>
            <td>${colaborador.turnos}</td>
            <td>
                <a href="/admin/colaboradores/editar?id=${colaborador.id}">Editar</a>
                <form method="post" action="/admin/colaboradores/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${colaborador.id}">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Colaboradores inactivos</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Entidad</th>
        <th>Email</th>
        <th>Contacto</th>
        <th>Telefono</th>
        <th>Localidad</th>
        <th>Zona</th>
        <th>Coordinadores</th>
        <th>Voluntarios</th>
        <th>Turnos</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="colaborador" items="${inactivos}">
        <tr>
            <td>${colaborador.id}</td>
            <td>${colaborador.nombreEntidad}</td>
            <td>${colaborador.email}</td>
            <td>${colaborador.contactoNom}</td>
            <td>${colaborador.contactoTlf}</td>
            <td>${colaborador.localidad}</td>
            <td>${colaborador.zonaGeografica}</td>
            <td>${colaborador.coordinadoresAsignados}</td>
            <td>${colaborador.voluntarios}</td>
            <td>${colaborador.turnos}</td>
            <td>
                <a href="/admin/colaboradores/editar?id=${colaborador.id}">Editar</a>
                <form method="post" action="/admin/colaboradores/activar" style="display:inline">
                    <input type="hidden" name="id" value="${colaborador.id}">
                    <button type="submit">Activar</button>
                </form>
                <form method="post" action="/admin/colaboradores/eliminar" style="display:inline">
                    <input type="hidden" name="id" value="${colaborador.id}">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form method="get" action="/admin">
    <button type="submit">Volver al panel</button>
</form>

</body>
</html>