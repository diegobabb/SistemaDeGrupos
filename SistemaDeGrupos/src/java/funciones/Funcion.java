/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package funciones;

import java.io.Serializable;

public class Funcion implements Serializable {

    public static String user() {
        return "<input id=\"username\" type=\"text\" size=\"30\" name=\"usuario\" >";
    }

    public static String pass() {
        return "<input id=\"password\"\n type=\"password\" size=\"30\" name=\"password\" >";
    }

    public static String botones() {
        return "<td><input id=\"botonesInput\" type=\"button\" onclick=\"consultarUsuarios()\" value=\"Consultar Usuarios\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"consultarGrupos()\" value=\"Mi grupo\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"crearGrupo()\" value=\"Crear o Ingresar a Grupo\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"cambiarClave()\" value=\"Cambiar Contraseña\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"logout()\" value=\"Salir\"></td>";
    }
}
