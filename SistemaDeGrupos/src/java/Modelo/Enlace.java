/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
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
