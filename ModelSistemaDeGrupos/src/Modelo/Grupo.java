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

    private String nombre;
    private ArrayList<Usuario> estudiantes;

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Usuario> estudiantes) {
        this.estudiantes = estudiantes;
    }

}
