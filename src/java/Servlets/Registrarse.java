package Servlets;

import Classes.Sesion;
import Classes.Usuario;
import DB.DBAdmin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registrarse extends HttpServlet {

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
        response.sendRedirect("./registrarse.jsp");
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
        String nombre, email, contrasena, repetirContrasena;
        nombre = request.getParameter("nombre");
        email = request.getParameter("email");
        contrasena = request.getParameter("contrasena");
        repetirContrasena = request.getParameter("repetirContrasena");
        if (nombre != null && email != null && contrasena != null && contrasena.equals(repetirContrasena)) {
            // Crear usuario
            Usuario usuario = new Usuario();
            usuario.setNombre_usuario(nombre);
            usuario.setEmail_usuario(email);
            usuario.setContrasena_usuario(contrasena);

            // Registrar usuario
            int estado = DBAdmin.RegistrarUsuario(usuario);
            if (estado > 0) {
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
                // Error al agregar usuario
                response.sendRedirect("../error.jsp");
            }
        } else {
            // Información incompleta
            response.sendRedirect("./registrarse.jsp");
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
