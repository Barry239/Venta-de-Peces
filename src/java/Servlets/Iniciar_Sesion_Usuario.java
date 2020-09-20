package Servlets;

import Classes.Sesion;
import DB.DBAdmin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Iniciar_Sesion_Usuario extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("./iniciar_sesion.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Obtener parametros
        String email, contrasena;
        email = request.getParameter("email");
        contrasena = request.getParameter("contrasena");

        // Autenticar usuario
        boolean autenticado = DBAdmin.AutenticarUsuario(email, contrasena);

        // Usuario autenticado
        if (autenticado) {
            // Crear cookies
            Sesion sesion = new Sesion();
            sesion = DBAdmin.ObtenerSesionUsuario(email);
            Cookie id_usuario = new Cookie("id_usuario", String.valueOf(sesion.getId_usuario()));
            Cookie nombre_usuario = new Cookie("nombre_usuario", sesion.getNombre_usuario());

            // Guardar cookies
            response.addCookie(id_usuario);
            response.addCookie(nombre_usuario);
            response.sendRedirect("./index.jsp");
        } else {
            // Datos erroneos
            response.sendRedirect("./iniciar_sesion.jsp");
        }
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
