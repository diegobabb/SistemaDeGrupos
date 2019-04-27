/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Modelo;

public class Grupo {

    int id;
    int secuencia;
    String nombre;
    int cupo;
    int activo;

    public Grupo() {
        this(-1, -1, null, -1, -1);
    }

    public Grupo(int id, int secuencia, String nombre, int cupo, int activo) {
        this.id = id;
        this.secuencia = secuencia;
        this.nombre = nombre;
        this.cupo = cupo;
        this.activo = activo;
    }

    public String toStringHTML() {
        return String.format("<tr><th id=\"titulolc\">%s</th></tr>", nombre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

}
