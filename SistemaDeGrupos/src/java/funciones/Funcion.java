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

    public static String mensaje(String e) {
        if (e != null) {
            return e;
        }
        return "";
    }
}
