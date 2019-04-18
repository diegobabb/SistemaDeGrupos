/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Modelo;

public class Usuario {

    private String id;
    private String pass;
    public String nombre;
    public String apellido1;
    public String apellido2;

    public Usuario(String id, String pass, String apellido1, String apellido2, String nombre) {
        this.id = id;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return id + ", " + nombre + ' ' + apellido1 + ' ' + apellido2;
    }

    public String toStringHTML(boolean b) {
        if (b) {
            return String.format("<tr><td>%s</td><td>%s %s</td></tr>", nombre, apellido1, apellido2);
        }
        return String.format("<tr><td>%s</td><td>%s</td><td>%s %s</td></tr>", id, nombre, apellido1, apellido2);
    }

// <editor-fold defaultstate="collapsed" desc="GET-SETS">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
//</editor-fold>
}
