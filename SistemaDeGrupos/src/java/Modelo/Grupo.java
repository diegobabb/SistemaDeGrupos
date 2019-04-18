/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Modelo;

public class Grupo {

    private int codigo;
    private String nombre;

    public Grupo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Grupo() {
        codigo = 0;
        nombre = "";
    }

    public String toStringHTML() {
        return String.format("<tr><th id=\"titulolc\">%s</th></tr>", nombre);
    }

// <editor-fold defaultstate="collapsed" desc="GET-SETS">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
//</editor-fold>
}
