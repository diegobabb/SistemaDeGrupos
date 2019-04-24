
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

public class ServletCrearGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            GestorUsuarios gestor = GestorUsuarios.obtenerInstancia();
            Usuario u = (Usuario) request.getSession(true).getAttribute("usuario");
            String n = request.getParameter("nombre");
            int t = gestor.crearGrupo(n);
            if (t != -1) {
                gestor.enlazar(u, t);
                request.setAttribute("Msg", "Grupo agregado");
            }
            request.getSession(true).setAttribute("allcursos", gestor.allGruposLess(u.getGrupo_id()));
            request.getRequestDispatcher("crearGrupo.jsp").forward(request, response);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException
                | IOException | SQLException ex) {
            Logger.getLogger(ServletCrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
