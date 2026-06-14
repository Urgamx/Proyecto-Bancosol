<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 19/05/2026
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%--
Página JSP que muestra el crear un nuevo Colaborador.

Autores:
- David Vilaseca Pareja: 100%

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Añadir Colaborador</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1>Añadir nuevo colaborador:</h1>
<form method="post" action="/coordinador/guardarColaborador">
    <table>
        <tr>
            <th>COLABORADOR</th>
            <td><input type="text" name="nombreEntidad" required></td>
        </tr>
        <tr>
            <th>DOMICILIO</th>
            <td><input type="text" name="domicilio"></td>
        </tr>
        <tr>
            <th>CP - LOCALIDAD</th>
            <td><input type="text" name="codPostal"> - <input type="text" name="localidad"></td>
        </tr>
        <tr>
            <th>COLABORA EN</th>
            <td><input type="text" name="zonaGeografica"></td>
        </tr>
        <tr>
            <th>CONTACTO</th>
            <td>
                <table>
                    <tr>
                        <th>Nombre:</th>
                        <td>
                            <input type="text" name="contactoNom">
                        </td>
                    </tr>
                    <tr>
                        <th>Teléfono:</th>
                        <td>
                            <input type="text" name="contactoTlf">
                        </td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>
                            <input type="email" name="email" required>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <th>OBSERVACIONES</th>
            <td><textarea name="observaciones" cols="45" rows="10"></textarea></td>
        </tr>
    </table>
    <br>
    <button type="submit" class="btn btn-primary">Guardar</button>
</form>
<a href="/coordinador/colaborador" class="btn btn-secondary">Cancelar</a>
</body>
</html>
