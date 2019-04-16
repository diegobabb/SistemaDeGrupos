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

    private int codigo;
    private String nombre;
    private ArrayList<Usuario> estudiantes;

    public Grupo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        estudiantes = new ArrayList<>();
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

    public int cant() {
        int c = 0;
        for (Usuario u : estudiantes) {
            c++;
        }
        return c;
    }

    public boolean agregar(Usuario e) {
        if (cant() <= 5) {
            estudiantes.add(e);
            return true;
        }
        return false;
    }

}
