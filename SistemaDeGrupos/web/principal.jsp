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
                <div id="table">
                    <table>
                        <tr>
                            <td id="titulolc" colspan="2" align="center">TITULO DE LA TABLA</td>
                        </tr>
                        <tbody>
                        </tbody> 
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

