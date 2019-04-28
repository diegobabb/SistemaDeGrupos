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
import org.json.JSONObject;

public class ServletAgregarGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            JSONObject r = new JSONObject();
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                GestorUsuarios g = GestorUsuarios.obtenerInstancia();
                Usuario u = (Usuario) sesion.getAttribute("usuario");
                g.updateUltimoAcceso(u);
                int curso = Integer.parseInt(request.getParameter("grupo"));
                if (g.getCupo(curso) < 5) {
                    g.enlazar(u, curso);
                    g.desincrementarCupo(u.getGrupo_id());
                    g.deleteGrupo();
                    u.setGrupo_id(curso);
                    g.incrementarCupo(curso);
                }
                r.put("miscursos", g.allGruposLess(u));
            } else {
                request.setAttribute("error", "Su sesión ha expirado por inactividad.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            out.println(r);
        } catch (SQLException | InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(ServletAgregarGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
