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
        <script src="Script/script.js" type="text/javascript"></script>
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
                    <div>
                        <strong><%=request.getSession(true).getAttribute("usuario").toString()%></strong>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <td><input id="botonesInput" type="button" onclick="consultarUsuarios()" value="Consultar Usuarios"></td>
                                <td><input id="botonesInput" type="button" onclick="consultarGrupos()" value="Consultar Grupos"></td>
                                <td><input id="botonesInput" type="button" onclick="crearGrupo()" value="Crear Grupo"></td>
                                <td><input id="botonesInput" type="button" onclick="logout()" value="Salir"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div>
                    <table id="principal">
                        <tbody>
                            <tr>
                                <th id="cursos" colspan="2">Mis cursos</th>
                            </tr>
                            <tr>
                                <td id="cursos">
                                    <table >
                                        <caption>GRUPO 1</caption>
                                        <thead>
                                            <tr>
                                                <th  id="titulolc" aling="center">
                                                    POKEMONES
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody id="CurNoMat">
                                            <tr>
                                                <td>
                                                    DIEGO
                                                </td>
                                                <td>
                                                    BABB
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    NAOMI
                                                </td>
                                                <td>
                                                    ROJAS
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    ENIBETH
                                                </td>
                                                <td>
                                                    SANCHEZ
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    JOSUE
                                                </td>
                                                <td>
                                                    RAMIREZ
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                                <td id="cursos">
                                    <table >
                                        <caption>GRUPO 1</caption>
                                        <thead>
                                            <tr>
                                                <th  id="titulolc">
                                                    DIGIMONES
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody id="CurMat">
                                            <tr>
                                                <td>
                                                    DIEGO
                                                </td>
                                                <td>
                                                    BABB
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    NAOMI
                                                </td>
                                                <td>
                                                    ROJAS
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    ENIBETH
                                                </td>
                                                <td>
                                                    SANCHEZ
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    JOSUE
                                                </td>
                                                <td>
                                                    RAMIREZ
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

