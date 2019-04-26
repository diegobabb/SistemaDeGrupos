
/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
 */
package servlet;

import Gestor.GestorUsuarios;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletCrearGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            GestorUsuarios gestor = GestorUsuarios.obtenerInstancia();
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                Usuario u = (Usuario) sesion.getAttribute("usuario");
                String n = request.getParameter("nombre");
                if (gestor.getGrupo(n)) {
                    int t = gestor.crearGrupo(n);
                    gestor.enlazar(u, t);
                    request.setAttribute("Msg", "Grupo agregado");
                } else {
                    request.setAttribute("Msg", "Existe un grupo con ese nombre");
                }
            } else {
                request.setAttribute("error", "Su sesión ha expirado por inactividad.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            //  request.getSession(true).setAttribute("allcursos", gestor.allGruposLess(u.getGrupo_id()));
            request.getRequestDispatcher("crearGrupo.jsp").forward(request, response);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException
                | IOException | SQLException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
