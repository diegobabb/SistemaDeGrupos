<%--
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Gestor.GestorUsuarios"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/requestJSON.js" type="text/javascript"></script>
        <script src="Script/funciones.js" type="text/javascript"></script>
        <link href="Css/stylePrincipal.css" rel="stylesheet" type="text/css"/>
        <title>Sistema de Grupos</title>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>

    </head>
    <body>
        <div id="wrapper">
            <div id="contents">
                <div id="divicion">
                    <div style="margin-bottom: 15px;">
                        <%
                            if (request.getSession(false).getAttribute("usuario") != null) {
                                Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");%>
                        <strong><%= u.toString()%></strong>
                        <% }  %>
                    </div>
                    <div>
                        <form  id="botones" name="botones" method="POST">
                            <table>
                                <tr>
                                    <td><input id="botonesInput" type="button" onclick="consultarUsuarios()" value="Consultar Usuarios"></td>
                                    <td><input id="botonesInput" type="button" onclick="consultarGrupos()" value="Mi grupo"></td>
                                    <td><input id="botonesInput" type="button" onclick="crearGrupo()" value="Crear o Ingresar a Grupo"></td>
                                    <td><input id="botonesInput" type="button" onclick="cambiarClave()" value="Cambiar Contraseña"></td>
                                    <td><input id="botonesInput" type="button" onclick="logout()" value="Salir"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div id="divicion">
                    <form action="ServletCrearGrupo" method="POST">
                        <table>
                            <tr>
                                <td><strong>Agregar nuevo grupo</strong></td>
                            </tr>
                            <tr>
                                <td>Nombre de grupo</td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" name="nombre">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" id="botonesInput" onclick="crearGrupo()" value="Agregar">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <% String msg = (String) request.getAttribute("Msg");
                                    if (msg != null) {%>
                                    <strong><%= msg%></strong>
                                    <%}%>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div>
                    <table id="principal">
                        <tbody id="miscursos">
                            <%if (request.getSession(false).getAttribute("usuario") == null) {
                                    response.sendRedirect("index.jsp");
                                } else { %>
                            <% GestorUsuarios g = GestorUsuarios.obtenerInstancia();%>
                            <%= g.allGruposLess((Usuario) request.getSession(false).getAttribute("usuario"))%>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

