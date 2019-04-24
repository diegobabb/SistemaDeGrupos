/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package Gestor;

import Modelo.Grupo;
import Modelo.Usuario;
import cr.ac.database.managers.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class GestorUsuarios {

    private static final String BASE_DATOS = "eif209_1901_p01";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "root";
    private DBManager db = null;//se inculye la biblioteca JBLib como Proyecto
    private static GestorUsuarios instancia = null;

    private static final String INSERT_USUARIO
            = "INSERT INTO estudiante (`id`, `nrc`, `apellidos`, `nombre`, `secuencia`, `clave`, `ultimo_acceso`) VALUES(?, ?, ?, ?, ?, ?, ?);";

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

    private static final String SELECT_GRUPO
            = "SELECT nombre FROM eif209_1901_p01.grupo WHERE nombre = ?;";

    public String selectGrupo(String nombre) {
        if (nombre != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(SELECT_GRUPO)) {
                stm.setString(1, nombre);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    String n = rs.getString(1);
                    return n;
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return null;
    }

    private static final String ENLAZAR
            = "UPDATE `eif209_1901_p01`.`estudiante` SET `grupo_id` = ? WHERE (`id` = ?);";

    public void enlazar(Usuario u, String c) throws SQLException {
        if (c != null && u != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(ENLAZAR)) {
                stm.clearParameters();
                stm.setString(1, u.getId());
                stm.setInt(2, Integer.parseInt(c));
                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "No se puede agregar al Grupo: '%s'", u));
                }
            }
        }
    }

    private static final String INSERT_GRUPO
            = "INSERT INTO `eif209_1901_p01`.`grupo` (`id`, `secuencia`, `nombre`, `cupo`) VALUES (?, ?, ?, ?);";

    public boolean crearGrupo(String nombre) throws SQLException {
        if (nombre != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(INSERT_GRUPO)) {
                stm.clearParameters();
                stm.setInt(1, 0);
                stm.setInt(2, 0);
                stm.setString(3, nombre);
                stm.setInt(4, 1);
                stm.setInt(5, 1);
                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "No se puede agregar al Grupo: '%s'", nombre));
                }
                return true;
            } catch (Exception e) {
                System.out.println("Excepcion crearGrupo" + e.getMessage());
            }
        }
        return false;
    }

    private static final String SELECT_GRUPOS
            = "SELECT id, nombre FROM eif209_1901_p01.grupo;";

    public String selectGrupos() throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SELECT_GRUPOS)) {
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
                int id = rs.getInt(1);
                String n = rs.getString(2);
                g.setId(id);
                g.setNombre(n);
                r.append(String.format("<td id=\"cursos\"><table onclick=\"agregar('%d')\">", id));
                r.append(String.format("<caption>GRUPO %d</caption>", cont++));
                r.append("<thead>");
                r.append(g.toStringHTML());
                r.append("</thead>");
                r.append("<tbody>");
                r.append(estudiantes_x_grupo(id, cnx));
                r.append("</tbody></table></td>");
            }
            return r.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static final String DESENLAZAR
            = "UPDATE `eif209_1901_p01`.`estudiante` SET `grupo_id` = NULL WHERE (`id` = ?);";

    public void abodonarGrupo(String e) throws SQLException {
        if (e != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(DESENLAZAR)) {
                stm.clearParameters();
                stm.setString(1, e);
                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "No se puede agregar al Grupo: '%s'", e));
                }
            }
        }
    }

    private static final String SELECT_USUARIO
            = "SELECT id, nrc, apellidos, nombre, secuencia, ultimo_acceso, grupo_id FROM eif209_1901_p01.estudiante WHERE id = ? AND clave = ?;";

    public Usuario selectUsuario(String id, String Clave) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_USUARIO)) {
            stm.clearParameters();
            stm.setString(1, id);
            stm.setString(2, Clave);
            try (ResultSet rs = stm.executeQuery()) {

                Usuario u;
                if (rs.next()) {
                    String i = rs.getString(1);
                    int nrc = rs.getInt(2);
                    String a = rs.getString(3);
                    String n = rs.getString(4);
                    int s = rs.getInt(5);
                    Date d = rs.getDate(6);
                    int g = rs.getInt(7);
                    u = new Usuario(i, nrc, a, n, s, d, g);
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

    private static final String ESTUDIANTE_X_GRUPOS
            = "SELECT apellidos, nombre FROM estudiante WHERE grupo_id = ?;";

    private String estudiantes_x_grupo(int cc, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(ESTUDIANTE_X_GRUPOS)) {
            stm.clearParameters();
            stm.setInt(1, cc);
            try (ResultSet rs = stm.executeQuery()) {
                Usuario u = new Usuario();
                StringBuilder r = new StringBuilder();
                while (rs.next()) {
                    String n = rs.getString(1);
                    String a = rs.getString(2);
                    u.setNombre(n);
                    u.setApellidos(a);
                    r.append(u.toStringHTML(true));
                }
                return r.toString();
            } catch (Exception e) {
                return e.getMessage();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static final String SELECT_USUARIOS
            = "SELECT apellidos, nombre, ultimo_acceso FROM estudiante;";

    public String selectUsuarios() {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SELECT_USUARIOS)) {
            Usuario u = new Usuario();
            StringBuilder r = new StringBuilder();
            while (rs.next()) {
                String a = rs.getString(1);
                String n = rs.getString(2);
                Date d = rs.getDate(3);
                u.setNombre(n);
                u.setApellidos(a);
                u.setUltimo_acceso(d);
                r.append(u.toStringHTML(false));
            }
            return r.toString();
        } catch (Exception e) {
           return e.getMessage();
        }
    }

    private static final String SELECT_GRUPO_WHERE_ESTUDIANTE
            = "SELECT id, nombre FROM eif209_1901_p01.grupo WHERE id = ?;";

    public String selectGrupoWhereEstudiante(int pid) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_GRUPO_WHERE_ESTUDIANTE)) {
            stm.clearParameters();
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            Grupo g = new Grupo();
            StringBuilder r = new StringBuilder();
            int cont = 1;
            if (rs.next()) {
                int id = rs.getInt(1);
                String n = rs.getString(2);
                g.setId(id);
                g.setNombre(n);
                r.append(String.format("<td id=\"cursos\"><table onclick=\"agregar('%d')\">", id));
                r.append(String.format("<caption>GRUPO %d</caption>", cont++));
                r.append("<thead>");
                r.append(g.toStringHTML());
                r.append("</thead>");
                r.append("<tbody>");
                r.append(estudiantes_x_grupo(id, cnx));
                r.append("</tbody></table></td>");
            }
            return r.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static final String SELECT_GRUPO_LESS
            = "SELECT id, nombre FROM eif209_1901_p01.grupo WHERE id <> ?;";

    public String allGruposLess(int pid) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_GRUPO_LESS)) {
            stm.clearParameters();
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
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
                int id = rs.getInt(1);
                String n = rs.getString(2);
                g.setId(id);
                g.setNombre(n);
                r.append(String.format("<td id=\"cursos\"><table onclick=\"agregar('%d')\">", id));
                r.append(String.format("<caption>GRUPO %d</caption>", cont++));
                r.append("<thead>");
                r.append(g.toStringHTML());
                r.append("</thead>");
                r.append("<tbody>");
                r.append(estudiantes_x_grupo(id, cnx));
                r.append("</tbody></table></td>");
            }
            return r.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
