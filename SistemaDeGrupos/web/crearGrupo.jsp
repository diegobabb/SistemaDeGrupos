<%--
    Document   : listaCursos
    Created on : 31/03/2019, 04:15:32 PM
    Author     : Diego Babb
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/requestJSON.js" type="text/javascript"></script>
        <script src="Script/Pscript.js" type="text/javascript"></script>
        <link href="Css/stylePrincipal.css" rel="stylesheet" type="text/css"/>
        <title>Examen</title>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <%
            if (request.getSession(true).getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
    </head>
    <body>
        <div id="wrapper">
            <div id="contents">
                <div id="divicion">
                    <div style="margin-bottom: 15px;">
                        <strong><%=request.getSession(true).getAttribute("usuario").toString()%></strong>
                    </div>
                    <div>
                        <form  id="botones" name="botones" method="POST">
                            <table>
                                <tr>
                                    <td><input id="botonesInput" type="button" onclick="consultarUsuarios()" value="Consultar Usuarios"></td>
                                    <td><input id="botonesInput" type="button" onclick="consultarGrupos()" value="Consultar Grupos"></td>
                                    <td><input id="botonesInput" type="button" value="Crear Grupo"></td>
                                    <td><input id="botonesInput" type="button" onclick="logout()" value="Salir"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div id="centrar">
                    <form action="ServletCrearGrupo" method="POST">
                        <table>
                            <tr>
                                <td>
                                    Nombre de grupo
                                </td>
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
                            <bold>
                                <% String msg = (String) request.getAttribute("Msg");
                                    if (msg != null) {%>
                                <%= msg%>
                                <%}%>
                            </bold>
                            </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

