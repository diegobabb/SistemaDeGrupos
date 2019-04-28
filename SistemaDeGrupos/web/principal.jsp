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
<%@taglib prefix="Etiquetas" uri="/WEB-INF/tlds/Etiquetas" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/requestJSON.js" type="text/javascript"></script>
        <script src="Script/funciones.js" type="text/javascript"></script>
        <link href="Css/stylePrincipal.css" rel="stylesheet" type="text/css"/>
        <title>Sistema de Grupos</title>
        <% response.setHeader("cache-control", "no-cache, no-store, must-revalidate"); %>
        <jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"></jsp:useBean>
        <%
            if (request.getSession(false).getAttribute("usuario") == null) {
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
                <div style="text-align: center;">Has click en un grupo para salirte</div>
                <div>
                    <table id="principal">
                        <tbody id="miscursos">
                            <%=request.getSession(true).getAttribute("cursos")%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

