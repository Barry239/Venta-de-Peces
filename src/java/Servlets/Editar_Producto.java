package Servlets;

import DB.DBAdmin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Editar_Producto extends HttpServlet {

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
        response.sendRedirect("./admin.jsp");
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
        // Obtener parametros
        int id_producto = 0, stock_producto = 0;
        float precio_producto = 0f;
        id_producto = Integer.parseInt(request.getParameter("id_producto"));
        precio_producto = Float.parseFloat(request.getParameter("precio"));
        stock_producto = Integer.parseInt(request.getParameter("stock"));
        
        // Validar parametros
        if (id_producto != 0 && stock_producto != 0 && precio_producto != 0) {
            // Editar producto
            int estado = DBAdmin.EditarProducto(precio_producto, stock_producto, id_producto);
            if (estado > 0) {
                response.sendRedirect("./admin.jsp");
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
