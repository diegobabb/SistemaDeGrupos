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
    private static final String TAM_GRUPO
            = "SELECT COUNT(*) FROM enlace WHERE grupo_codigo = ?;";
    private static final String DESENLACE
            = "DELETE FROM enlace WHERE estudiante_id = ?;";

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
                Grupo g = new Grupo();
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

    public void abodonarGrupo(Usuario e) throws SQLException {

        try (Connection cnx = db.getConnection(BASE_DATOS, USUARIO_BD, CLAVE_BD);
                PreparedStatement stm = cnx.prepareStatement(DESENLACE)) {
            stm.clearParameters();
            stm.setString(1, e.getId());
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

                Usuario u = new Usuario();
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

}
