<!DOCTYPE html>
<html>
    <head>
        <title>Practica de Examen</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="Css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <header>
            </header>
            <div id="contents">
                <div id="table">
                    <form action="ServletLogIn" method="POST">
                        <>
                        <table>
                            <thead>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" align="center">
                                        <img src="https://img.icons8.com/doodle/100/000000/conference-call.png">
                                    </td>
                                </tr>
                                <tr>
                                    <th id="titulo" colspan="2">
                                        Sistema de Grupos
                                    </th>
                                </tr>
                                <tr>
                                    <td id="text">
                                        Usuario
                                    </td>
                                    <td>
                                        <input id="username" type="text"
                                               size="30" name="usuario" autocomplete="off">
                                    </td>
                                </tr>
                                <tr>
                                    <td id="text">
                                        Contraseña
                                    </td>
                                    <td>
                                        <input id="password"
                                               type="password" size="30" name="password" >
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" id="error">
                                        <%
                                            String error = (String) request.getAttribute("error");
                                            if (error != null) {%>
                                        <%=error%>
                                        <%}%>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input id="boton" type="submit" value="Ingresar">
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

