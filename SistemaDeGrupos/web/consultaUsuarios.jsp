<%--
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@taglib prefix="Etiquetas" uri="/WEB-INF/tlds/Etiquetas" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            if (request.getSession(false).getAttribute("usuario") == null) {
                response.sendRedirect("index.jsp");
            } else {
                usuario = (Usuario) request.getSession(false).getAttribute("usuario");
            }

        %>
    </head>
    <body onload="init()">
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
                    <table>
                        <tr>
                            <td>
                                <strong>Ordenar por</strong>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <select id="orden" name="orden" style="border: none;padding: 5px;border-radius: 2px;">
                                    <option>SELECCIONAR</option>
                                    <option>NRC</option>
                                    <option>Apellidos</option>
                                    <option>Nombre</option>
                                    <option>Identificaciones</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <table id="principal">
                        <tbody id="usuarios">
                            <%=request.getSession(true).getAttribute("usuarios")%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

