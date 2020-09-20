package Servlets;

import DB.DBAdmin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Agregar_Producto extends HttpServlet {

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
        response.sendRedirect("./productos.jsp");
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
        int cantidad_carrito, id_usuario = 0, id_producto;
        cantidad_carrito = Integer.parseInt(request.getParameter("cantidad"));
        id_producto = Integer.parseInt(request.getParameter("id_producto"));
        
        // Obtener cookies
        Cookie ck[] = request.getCookies();
        for (Cookie ck1 : ck) {
            if (ck1.getName().equals("id_usuario")) {
                id_usuario = Integer.parseInt(ck1.getValue());
            }
        }
        
        if (id_usuario != 0 && cantidad_carrito > 0) {
            // Agregar al carrito
            int estado = DBAdmin.AgregarAlCarrito(id_usuario, id_producto, cantidad_carrito);
            if (estado > 0) {
                response.sendRedirect("./productos.jsp");
            } else {
                response.sendRedirect("../error.jsp");
            }
        } else {
            response.sendRedirect("../error.jsp");
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
