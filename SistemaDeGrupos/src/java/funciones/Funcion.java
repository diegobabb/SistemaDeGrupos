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

    public static String mensaje() {
        return "Bafbjf";
    }

    public static String descripcion(Usuario u) {
        return u.toString();
    }
}
