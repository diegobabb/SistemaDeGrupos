/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Grupo {

    private String codigo;
    private String nombre;

    public Grupo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Grupo() {
        codigo = "";
        nombre = "";
    }

// <editor-fold defaultstate="collapsed" desc="GET-SETS">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

//</editor-fold>
}
