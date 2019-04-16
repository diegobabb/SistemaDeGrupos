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
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
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
                <div id='divicion'>
                    EIF209 Programación 4 - Examen Parcial
                    1er ciclo 2019
                </div>
                <div id='divicion'>
                    <form id="botones" name="botones" me    thod="POST">
                        <table>
                            <tr>
                                <td><input id="botonesInput" type="button" onclick="logout()" value="Salir"></td>
                                <td><input id="botonesInput" type="button" onclick="datos()" value="Datos personales"></td>
                                <td><input id="botonesInput" type="button" onclick="cursos()" value="Matricular"></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div id="table">
                    <table>
                        <tr>
                            <td id="titulolc" colspan="2" align="center">Seleccione en la lista correspondiente para matricular o eliminar los cursos requeridos</td>
                        </tr>
                        <tbody>
                            <tr>
                                <td>
                                    <table id="cursos">
                                        <caption>Cursos Disponibles</caption>
                                        <thead>
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Nombre</th>
                                                <th>Creditos</th>
                                            </tr>
                                        </thead>
                                        <tbody id="CurNoMat">
                                            <%=request.getSession(true).getAttribute("CursosNoMatriculados")%>
                                        </tbody>
                                    </table>
                                </td>
                                <td>
                                    <table id="cursos">
                                        <caption>Cursos Matriculados</caption>
                                        <thead>
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Nombre</th>
                                                <th>Creditos</th>
                                            </tr>
                                        </thead>
                                        <tbody id="CurMat">
                                            <%=request.getSession(true).getAttribute("CursosSiMatriculados")%>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id='divicion'>
                    <div>
                        <p>Diego Babb, 116960863</p>
                    </div>
                    <div>
                        <%DateFormat FMT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String f = FMT.format(Calendar.getInstance().getTime());%>
                        <%=f%>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

