package Servlets;

import Classes.Carrito;
import DB.DBAdmin;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Comprar_Carrito extends HttpServlet {

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
        response.sendRedirect("./carrito.jsp");
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
        int id_usuario = 0;
        String domicilio;
        domicilio= request.getParameter("domicilio");
        
        // Obtener cookies
        Cookie ck[] = request.getCookies();
        for (Cookie ck1 : ck) {
            if (ck1.getName().equals("id_usuario")) {
                id_usuario = Integer.parseInt(ck1.getValue());
            }
        }
        
        // Parametros correctos
        if (domicilio != null && id_usuario != 0) {
            // Obtener carrito del usuario
            List<Carrito> carrito = DBAdmin.ObtenerCarrito(id_usuario);
            
            if (carrito.size() > 0) {
                int estado1, estado2;
                
                // Vaciar carrito
                for (Carrito item: carrito) {
                    estado1 = DBAdmin.ComprarItemCarritoUsuario(domicilio, id_usuario, item.getId_producto(), item.getCantidad_carrito());
                    if (estado1 > 0) {
                        estado2 = DBAdmin.EliminarDelCarrito(item.getId_carrito());
                        if (estado2 < 0) {
                            response.sendRedirect("../error.jsp");
                            break;
                        }
                    } else {
                        response.sendRedirect("../error.jsp");
                        break;
                    }
                }
                
                // Carrito vaciado
                response.sendRedirect("./carrito.jsp");
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
