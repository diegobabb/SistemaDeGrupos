<%--
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="Etiquetas" uri="/WEB-INF/tlds/Etiquetas" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/requestJSON.js" type="text/javascript"></script>
        <script src="Script/funciones.js" type="text/javascript"></script>
        <link href="Css/stylePrincipal.css" rel="stylesheet" type="text/css"/>
        <title>Sistema de Grupos</title>
        <jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"></jsp:useBean>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <%
            if (request.getSession(true).getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                usuario = (Usuario) request.getSession(false).getAttribute("usuario");
            }

        %>
    </head>
    <body>
        <div id="wrapper">
            <div id="contents">
                <div id="divicion">
                    <div style="margin-bottom: 15px;">
                        <strong>${Etiquetas:descripcion(usuario)}</strong>
                    </div>
                    <div>
                        <form  id="botones" name="botones" method="POST">
                            <table>
                                <tr>
                                    ${Etiquetas:botones()}
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div id="divicion">
                    <form action="ServletCambiarClave" method="POST">
                        <table>
                            <thead>
                                <tr>
                                    <th>Ingresar nueva contraseña</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Nueva contraseña</td>
                                </tr>
                                <tr>
                                    <td>
                                        ${Etiquetas:text("password","clave")}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        ${Etiquetas:boton("Cambiar")}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>
                                            <% String msg = (String) request.getAttribute("Msg");
                                                if (msg != null) {%>
                                            <%= msg%>
                                            <%}%>
                                        </strong>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
