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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Server", urlPatterns = {"/Server", "/LogOut"})
public class Server extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/Server":
                this.LogIn(request, response);
                break;
            case "/LogOut":
                this.LogOut(request, response);
                break;

        }
    }

    private void LogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try {
            Usuario user = GestorUsuarios.obtenerInstancia().selectUsuario(
                    request.getParameter("usuario"), request.getParameter("password"));
            if (user != null) {
                System.out.println("%n ------- ESTUDIANTE : -------" + user);
                request.getSession(true).setAttribute("usuario", user);
                request.getSession(true).setAttribute("cursos", GestorUsuarios.obtenerInstancia().grupos_x_estudiante(user.getId()));
                request.getRequestDispatcher("/principal.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Usuario invalido");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.printf(" ------------------------"
                    + "--------------------------------------------Servicio Excepción: '%s'%n", ex.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        request.getRequestDispatcher("/Server").forward(request, response);
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
