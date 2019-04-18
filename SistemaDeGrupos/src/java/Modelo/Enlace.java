/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Modelo;

public class Enlace {

    private Usuario u;
    private Grupo g;

    public Enlace(Usuario u, Grupo g) {
        this.u = u;
        this.g = g;
    }
// <editor-fold defaultstate="collapsed" desc="GET-SETS">

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public Grupo getG() {
        return g;
    }

    public void setG(Grupo g) {
        this.g = g;
    }
    //</editor-fold>

}
