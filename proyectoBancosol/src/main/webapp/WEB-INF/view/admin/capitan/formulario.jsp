<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${modo} Capitan</title>
</head>
<body>

<h1>${modo} Capitan</h1>

<p><a href="/admin/capitanes">Volver al listado</a></p>

<c:if test="${not empty error}">
    <p><strong>Error: ${error}</strong></p>
</c:if>

<form method="post" action="/admin/capitanes/guardar">

    <input type="hidden" name="id" value="${capitan.id}">

    <div>
        <label for="nombreCompleto">Nombre Completo:</label>
        <input type="text" id="nombreCompleto" name="nombreCompleto" value="${capitan.nombreCompleto}">
    </div>

    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${capitan.email}">
    </div>

    <div>
        <label for="password">Contrasena:</label>
        <input type="password" id="password" name="password">
        <c:if test="${modo == 'Editar'}">
            <small>(Dejar en blanco si no se desea cambiar)</small>
        </c:if>
    </div>

    <div>
        <label for="activo">Estado:</label>
        <select id="activo" name="activo">
            <option value="1" <c:if test="${capitan.activo == 1}">selected</c:if>>Activo</option>
            <option value="0" <c:if test="${capitan.activo == 0}">selected</c:if>>Inactivo</option>
        </select>
    </div>

    <div>
        <label>Campanas Asignadas:</label>
        <br>
        <c:forEach var="campana" items="${todasCampanas}">
            <input type="checkbox"
                   name="idCampanasSeleccionadas"
                   value="${campana.id}"
                   class="checkbox-campana"
                   onchange="filtrarTiendas()"
            <c:forEach var="idAsignado" items="${capitan.idCampanasSeleccionadas}">
                   <c:if test="${campana.id == idAsignado}">checked</c:if>
            </c:forEach>
            >
            ${campana.tipoDeCampana.nombre}
            <br>
        </c:forEach>
        <c:if test="${empty todasCampanas}">
            <p>No existen campanas creadas en el sistema.</p>
        </c:if>
    </div>

    <div>
        <label>Tiendas Gestionadas:</label>
        <br>
        <c:forEach var="tienda" items="${todasTiendas}">
            <div class="contenedor-tienda" data-campana="${tienda.idCadena.id}">
                <input type="checkbox"
                       name="idTiendasSeleccionadas"
                       value="${tienda.id}"
                <c:forEach var="idAsignado" items="${capitan.idTiendasSeleccionadas}">
                       <c:if test="${tienda.id == idAsignado}">checked</c:if>
                </c:forEach>
                >
                    ${tienda.nombre}
            </div>
        </c:forEach>
        <c:if test="${empty todasTiendas}">
            <p>No existen tiendas creadas en el sistema.</p>
        </c:if>
    </div>

    <br>
    <div>
        <button type="submit">Guardar Capitan</button>
    </div>

</form>

<script>
    function filtrarTiendas() {
        const campanasMarcadas = Array.from(document.querySelectorAll('.checkbox-campana:checked'))
            .map(cb => cb.value);

        const tiendas = document.querySelectorAll('.contenedor-tienda');

        tiendas.forEach(tienda => {
            const idCampanaTienda = tienda.getAttribute('data-campana');

            if (campanasMarcadas.length === 0) {
                tienda.style.display = 'block';
            } else if (campanasMarcadas.includes(idCampanaTienda)) {
                tienda.style.display = 'block';
            } else {
                tienda.style.display = 'none';
                tienda.querySelector('input').checked = false;
            }
        });
    }

    window.onload = filtrarTiendas;
</script>

</body>
</html>