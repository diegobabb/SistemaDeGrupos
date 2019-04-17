/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import Modelo.Enlace;
import Modelo.Grupo;
import Modelo.Usuario;
import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GestorUsuarios {

    private static final String BASE_DATOS = "basededatos";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    private DBManager db = null;//se inculye la biblioteca JBLib como Proyecto
    private static GestorUsuarios instancia = null;

    private static final String INSERT_USUARIO
            = "INSERT INTO estudiante "
            + "(id, apellido1, apellido2, nombre)"
            + " VALUES(?, ? , ?, ?);";
    private static final String INSERT_GRUPO
            = "INSERT INTO grupo (codigo, nombre) VALUES"
            + "(?, ?);";
    private static final String ENLAZAR
            = "INSERT INTO enlace (estudiante_id, grupo_codigo) "
            + " VALUES(?,?);";
    private static final String SELECT_GRUPOS
            = "SELECT nombre, codigo"
            + " FROM curso;";
    private static final String SELECT_USUARIO
            = "SELECT  id, clave, apellido1, apellido2, nombre"
            + " FROM basededatos.estudiante"
            + " WHERE id = ? AND clave = ?;";
    private static final String SELECT_ALL_USUARIOS
            = "SELECT  id, apellido1, apellido2, nombre"
            + " FROM basededatos.estudiante;";
    private static final String TAM_GRUPO
            = "SELECT COUNT(*) FROM enlace WHERE grupo_codigo = ?;";
    private static final String DESENLACE
            //DELETE FROM basededatos.enlace WHERE estudiante_id = '111' and grupo_codigo = '2';
            = "DELETE FROM basededatos.enlace WHERE estudiante_id = ? and grupo_codigo = ?;";
    private static final String GRUPOS_X_ESTUDIANTE
            = "SELECT grupo.nombre, grupo.codigo FROM grupo, enlace where enlace.estudiante_id = ? and grupo.codigo = enlace.grupo_codigo;";
    private static final String ESTUDIANTE_X_GRUPOS
            = "SELECT estudiante.apellido1, estudiante.apellido2, estudiante.nombre FROM estudiante, enlace where ? = enlace.grupo_codigo and enlace.estudiante_id = estudiante.id;";

    private GestorUsuarios()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorUsuarios obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public int tamGrupo(String cod) {
        int cant = 0;
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(TAM_GRUPO)) {
            //stm.clearParameters();
            stm.setString(1, cod);

            try (ResultSet rs = stm.executeQuery()) {
                cant = rs.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println("Excepcion RESUT SET TAM GRUPO" + e.getMessage());
        }
        return cant;

    }

    public void enlazar(Enlace e) throws SQLException {

        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(ENLAZAR)) {
            stm.clearParameters();
            stm.setString(1, e.getU().getId());
            stm.setString(2, e.getG().getCodigo());
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede agregar al Grupo: '%s'", e));
            }

        }
    }

    public ArrayList<Grupo> allGrupos(String cod) throws SQLException {

        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SELECT_GRUPOS)) {
            ArrayList<Grupo> Grupos = new ArrayList<>();
            while (rs.next()) {
                Grupo g;
                String nombre = rs.getString("nombre");
                g = new Grupo(cod, nombre);
                Grupos.add(g);
            }
            return Grupos;
        } catch (Exception e) {
            System.out.println("Excepcion AllGrupos" + e.getMessage());
        }

        return null;
    }

    public void abodonarGrupo(String e, String g) throws SQLException {

        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(DESENLACE)) {
            stm.clearParameters();
            stm.setString(1, e);
            stm.setString(2, g);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede agregar al Grupo: '%s'", e));
            }
        }
    }

    public Usuario selectUsuario(String id, String Clave) throws SQLException {

        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_USUARIO)) {
            //stm.clearParameters();

            stm.setString(1, id);
            stm.setString(2, Clave);

            try (ResultSet rs = stm.executeQuery()) {

                Usuario u;
                if (rs.next()) {
                    String idd = rs.getString("id");
                    String pass = rs.getString("clave");
                    String ap1 = rs.getString("apellido2");
                    String ap2 = rs.getString("apellido2");
                    String nombre = rs.getString("nombre");
                    u = new Usuario(idd, pass, ap1, ap2, nombre);
                    return u;
                }
            } catch (Exception e) {
                System.out.println("Excepcion RESUT SET SelectUsuario" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Excepcion  PreparedStatement SelectUsuario" + e.getMessage());
        }

        return null;
    }

    public String grupos_x_estudiante(String id) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(GRUPOS_X_ESTUDIANTE)) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                Grupo g = new Grupo();
                StringBuilder r = new StringBuilder();
                int fila = 0;
                boolean cerrar = true;
                int cont = 1;
                while (rs.next()) {
                    if (fila % 2 == 0) {
                        if (cerrar) {
                            r.append("<tr>");
                        } else {
                            r.append("</tr>");
                        }
                        cerrar = !cerrar;
                    }
                    fila++;
                    String nn = rs.getString("nombre");
                    String cc = rs.getString("codigo");
                    g.setNombre(nn);
                    g.setCodigo(cc);
                    r.append(String.format("<td id=\"cursos\"><table onclick=\"eliminar('%s')\">", cc));
                    r.append(String.format("<caption>GRUPO %d</caption>", cont++));
                    r.append("<thead>");
                    r.append(g.toStringHTML());
                    r.append("</thead>");
                    r.append("<tbody>");
                    r.append(estudiantes_x_grupo(cc, cnx));
                    r.append("</tbody></table></td>");
                }
                return r.toString();
            } catch (Exception e) {
                return e.getMessage();
            }

        } catch (Exception e) {
            System.out.println("Excepcion  PreparedStatement SelectUsuario" + e.getMessage());
        }

        return null;
    }

    private String estudiantes_x_grupo(String cc, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(ESTUDIANTE_X_GRUPOS)) {
            stm.clearParameters();
            stm.setString(1, cc);
            try (ResultSet rs = stm.executeQuery()) {
                Usuario u = new Usuario();
                StringBuilder r = new StringBuilder();
                while (rs.next()) {
                    String a1 = rs.getString("apellido1");
                    String a2 = rs.getString("apellido2");
                    String n = rs.getString("nombre");
                    u.setNombre(n);
                    u.setApellido1(a1);
                    u.setApellido2(a2);
                    r.append(u.toStringHTML(true));
                }
                return r.toString();
            } catch (Exception e) {
                System.out.println("Excepcion RESUT SET SelectUsuario" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Excepcion  PreparedStatement SelectUsuario" + e.getMessage());
        }

        return null;
    }

    public String allUsuarios() {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SELECT_ALL_USUARIOS)) {
            Usuario u = new Usuario();
            StringBuilder r = new StringBuilder();
            while (rs.next()) {
                String id = rs.getString("id");
                String a1 = rs.getString("apellido1");
                String a2 = rs.getString("apellido2");
                String n = rs.getString("nombre");
                u.setNombre(n);
                u.setApellido1(a1);
                u.setApellido2(a2);
                u.setId(id);
                r.append(u.toStringHTML(false));
            }
            return r.toString();
        } catch (Exception e) {
            System.out.println("Excepcion RESUT SET SelectUsuario" + e.getMessage());
        }
        return null;
    }

}
