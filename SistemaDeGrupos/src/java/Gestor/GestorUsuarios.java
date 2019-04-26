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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static final String UPDATE_ULTIMO_ACCESO
            = "UPDATE `eif209_1901_p01`.`estudiante` SET `ultimo_acceso` = NOW() WHERE (`id` = ?);";

    public boolean updateUltimoAcceso(Usuario u) {
        if (null != u) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(UPDATE_ULTIMO_ACCESO)) {
                stm.clearParameters();
                stm.setString(1, u.getId());
                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "Error al altualizar ultimo acceso de", u));
                }
            } catch (SQLException ex) {
                Logger.getLogger(GestorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    private static final String UPDATE_GRUPOID
            = "UPDATE  eif209_1901_p01 .`estudiante` SET `grupo_id`=null WHERE `grupo_id`= ?;";
    private static final String DELETE_GRUPO
            = "DELETE  FROM eif209_1901_p01 .`grupo` WHERE `id`=?";

    public void updateGrupoId(int c) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(UPDATE_GRUPOID)) {
            stm.clearParameters();
            stm.setInt(1, c);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede updateGrupoId: '%d'", c));
            }
        }
    }

    public void deleteGrupo(int c) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(DELETE_GRUPO)) {
            stm.clearParameters();
            stm.setInt(1, c);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede deleteGrupo: '%d'", c));
            }
        }
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

    public void enlazar(Usuario u, int c) throws SQLException {
        if (c != -1 && u != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(ENLAZAR)) {
                stm.clearParameters();
                stm.setInt(1, c);
                stm.setString(2, u.getId());

                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "No se puede agregar al Grupo: '%s'", u));

                }
            }

        }

    }
    private static final String UPDATE_CUPO
            = "UPDATE eif209_1901_p01 .`grupo` SET `cupo` = cupo + 1 WHERE (`id` = ?);";

    public void incrementarCupo(int c) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(UPDATE_CUPO)) {
            stm.clearParameters();
            stm.setInt(1, c);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede incrementar: '%d'", c));
            }
        }
    }
    private static final String DESINCREMENTAR_CUPO
            = "update eif209_1901_p01 .`grupo` SET `cupo` = cupo - 1 WHERE (`id` = ?);";

    public void desincrementarCupo(int c) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(DESINCREMENTAR_CUPO)) {
            stm.clearParameters();
            stm.setInt(1, c);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede incrementar: '%d'", c));
            }
        }
    }

    private static final String SELECT_CUPO
            = "SELECT `cupo` FROM eif209_1901_p01 .`grupo` WHERE (`id` = ?);";

    public int getCupo(int c) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_CUPO)) {
            stm.clearParameters();
            stm.setInt(1, c);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int cupo = rs.getInt(1);
                    return cupo;
                }
            } catch (Exception e) {
                System.out.println("Excepcion RESUT SET getCupo" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Excepcion  PreparedStatement getCupo" + e.getMessage());
        }
        return -1;
    }

    private static final String SET_CLAVE
            = "UPDATE eif209_1901_p01 .`estudiante` SET `clave`= ? WHERE id = ?;";

    public void cambiarClave(Usuario u, String clave) throws SQLException {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(DESINCREMENTAR_CUPO)) {
            stm.clearParameters();
            stm.setString(1, u.getId());
            stm.setString(2, clave);
            if (stm.executeUpdate() != 1) {
                throw new SQLException(String.format(
                        "No se puede Cambiar clave: '%s'", clave));
            }
        }
    }

    private static final String MAX_ID_GRUPO
            = "SELECT MAX(id) AS id FROM grupo;";

    public int ultimoID(Connection cnx) {
        try (Statement stm = cnx.createStatement();
                ResultSet rst = stm.executeQuery(MAX_ID_GRUPO)) {
            int t = -1;
            if (rst.next()) {
                t = (rst.getInt(1) + 1);
            }
            return t;
        } catch (SQLException ex) {
            return -1;
        }
    }

    private static final String INSERT_GRUPO
            = "INSERT INTO `eif209_1901_p01`.`grupo` (`id`, `secuencia`, `nombre`, `cupo`) VALUES (?, ?, ?, ?);";

    public int crearGrupo(String nombre) throws SQLException {
        if (nombre != null) {
            try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                    PreparedStatement stm = cnx.prepareStatement(INSERT_GRUPO)) {
                int t = ultimoID(cnx);
                stm.clearParameters();
                stm.setInt(1, t);
                stm.setInt(2, t);
                stm.setString(3, nombre);
                stm.setInt(4, 1);
                if (stm.executeUpdate() != 1) {
                    throw new SQLException(String.format(
                            "No se puede agregar al Grupo: '%s'", nombre));
                }
                return t;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }
    private static final String SELECT_NOMBRE_GRUPO
            = "SELECT nombre FROM eif209_1901_p01.grupo WHERE nombre = ?;";

    public boolean getGrupo(String nomb) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_NOMBRE_GRUPO)) {
            stm.clearParameters();
            stm.setString(1, nomb);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String cupo = rs.getString(1);
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Excepcion RESUT SET getCupo" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Excepcion  PreparedStatement getCupo" + e.getMessage());
        }
        return true;
    }
    private static final String SELECT_GRUPOID
            = "SELECT grupo_id FROM eif209_1901_p01.estudiante WHERE id = ?;";

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
                    Date d = rs.getTimestamp(6);
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
            = "SELECT id, apellidos, nombre, ultimo_acceso FROM estudiante WHERE grupo_id = ?;";

    private String estudiantes_x_grupo(int cc, Connection cnx) {
        try (PreparedStatement stm = cnx.prepareStatement(ESTUDIANTE_X_GRUPOS)) {
            stm.clearParameters();
            stm.setInt(1, cc);
            try (ResultSet rs = stm.executeQuery()) {
                Usuario u = new Usuario();
                StringBuilder r = new StringBuilder();
                while (rs.next()) {
                    String i = rs.getString(1);
                    String n = rs.getString(2);
                    String a = rs.getString(3);
                    Date d = rs.getTimestamp(4);
                    u.setUltimo_acceso(d);
                    u.setId(i);
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
            = "SELECT id, apellidos, nombre, ultimo_acceso FROM estudiante;";

    public String selectUsuarios() {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(SELECT_USUARIOS)) {
            Usuario u = new Usuario();
            StringBuilder r = new StringBuilder();
            while (rs.next()) {
                String id = rs.getString(1);
                String a = rs.getString(2);
                String n = rs.getString(3);
                Date d = rs.getTimestamp(4);
                u.setId(id);
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

    private static final String SELECT_estudiante
            = "SELECT grupo_id FROM eif209_1901_p01.estudiante WHERE id = ?;";

    public int selectEst(Usuario u) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_estudiante)) {
            stm.setString(1, u.getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int n = rs.getInt(1);
                return n;
            }
        } catch (Exception e) {
            System.out.println("Excepcion RESUT SET SelectUsuario" + e.getMessage());
        }

        return -1;
    }
    private static final String SELECT_GRUPO_WHERE_ESTUDIANTE
            = "SELECT id, nombre FROM eif209_1901_p01.grupo WHERE id = ?;";

    public String selectGrupoWhereEstudiante(int c) {
        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(SELECT_GRUPO_WHERE_ESTUDIANTE)) {
            stm.clearParameters();
            stm.setInt(1, c);
            ResultSet rs = stm.executeQuery();
            Grupo g = new Grupo();
            StringBuilder r = new StringBuilder();
            int cont = 1;
            if (rs.next()) {
                int id = rs.getInt(1);
                String n = rs.getString(2);
                g.setId(id);
                g.setNombre(n);
                r.append(String.format("<td id=\"cursos\"><table onclick=\"eliminar('%d')\">", id));
                r.append(String.format("<caption>GRUPO %d</caption>", cont++));
                r.append("<thead>");
                r.append(g.toStringHTML());
                r.append("</thead>");
                r.append("<tbody>");
                r.append(estudiantes_x_grupo(id, cnx));
                r.append("</tbody></table></td>");
                r.append("<tfooter>");
                r.append(String.format(
                        "<tr><td><input id=\"botonesInput\" type=\"button\" onclick=\"eliminaGrupo('%d')\" value=\"Eliminar\"></td></tr>", id));
                r.append("</tfooter>");
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
