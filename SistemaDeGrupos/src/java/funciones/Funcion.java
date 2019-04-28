/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package funciones;

import Modelo.Usuario;
import java.io.Serializable;

public class Funcion implements Serializable {

    public static String user() {
        return "<input id=\"username\" type=\"text\" size=\"30\" name=\"usuario\" >";
    }

    public static String pass() {
        return "<input id=\"password\"\n type=\"password\" size=\"30\" name=\"password\" >";
    }

    public static String text(String type, String name) {
        return "<input type=\"+" + type + "\" name=\"" + name + "\">";
    }

    public static String boton(String s) {
        return "<input type=\"submit\" id=\"botonesInput\" value=\"" + s + "\">";
    }

    public static String botones() {
        return "<td><input id=\"botonesInput\" type=\"button\" onclick=\"consultarUsuarios()\" value=\"Consultar Usuarios\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"consultarGrupos()\" value=\"Mi grupo\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"crearGrupo()\" value=\"Crear o Ingresar a Grupo\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"cambiarClave()\" value=\"Cambiar Contraseña\"></td>"
                + "<td><input id=\"botonesInput\" type=\"button\" onclick=\"logout()\" value=\"Salir\"></td>";
    }

    public static String descripcion(Usuario u) {
        return u.toString();
    }

    public static String comboBox() {
        return "<select id=\"orden\" name=\"orden\" style=\"border: none;padding: 5px;border-radius: 2px;\">"
                + "<option>SELECCIONAR</option>"
                + "<option>NRC</option>"
                + "<option>Apellidos</option>"
                + "<option>Nombre</option>"
                + "<option>Identificaciones</option>"
                + "</select>";
    }
}
