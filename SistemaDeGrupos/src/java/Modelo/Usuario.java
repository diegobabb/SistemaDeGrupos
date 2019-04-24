/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Usuario {

    private String id;
    private int nrc;
    private String apellidos;
    private String nombre;
    private int secuencia;
    private Date ultimo_acceso;
    private int grupo_id;
    private final int MAX_INACTIVIDAD = 30;

    public Usuario(String id, int nrc, String apellidos, String nombre, int secuencia, Date ultimo_acceso, int grupo_id) {
        this.id = id;
        this.nrc = nrc;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.secuencia = secuencia;
        this.ultimo_acceso = ultimo_acceso;
        this.grupo_id = grupo_id;
    }

    public Usuario() {
        this(null, -1, null, null, -1, null, -1);
    }

    @Override
    public String toString() {
        return id + ", " + nombre + ' ' + apellidos;
    }

    private long calculo() {
        long dfM = Math.abs(Calendar.getInstance().getTimeInMillis() - ultimo_acceso.getTime());
        return TimeUnit.MINUTES.convert(dfM, TimeUnit.MILLISECONDS);
    }

    public String toStringHTML(boolean b) {
        long g = calculo();
        String s = ((g < MAX_INACTIVIDAD) ? "<img src=\"https://img.icons8.com/color/25/000000/ok.png\">" : "<img src=\"https://img.icons8.com/color/25/000000/cancel.png\">");
        if (b) {
            return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", nombre, apellidos, s);
        }
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", id, nombre, apellidos, s);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public Date getUltimo_acceso() {
        return ultimo_acceso;
    }

    public void setUltimo_acceso(Date ultimo_acceso) {
        if (ultimo_acceso != null) {
            this.ultimo_acceso = ultimo_acceso;
        }else{
            this.ultimo_acceso = new Date();
        }
    }

    public int getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }
}
