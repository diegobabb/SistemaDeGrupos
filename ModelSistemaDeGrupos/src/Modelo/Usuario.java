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
