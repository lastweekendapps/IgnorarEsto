/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UsuarioDAO;

/**
 *
 * @author Nicolas
 */
public class BorrarUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int id;

            try {

                id = Integer.parseInt(request.getParameter("id"));

            } catch (Exception e) {
                request.setAttribute("Mensaje1", "no es numero");
                id = 0;

            }
            
            UsuarioDAO dao = new UsuarioDAO();
            
            int i  = dao.borrarUsuario(id,request.getParameter("nombre"), request.getParameter("nombre"));
            
            if(i==0){
                
                request.setAttribute("Mensaje","no existe");
            }
            if(i==1){
                request.setAttribute("Mensaje","no coinciden");
            }
            if(i==2){
                request.setAttribute("Mensaje","contraseña incorrecta");
            }
            if(i==3){
                request.setAttribute("Mensaje","ok");
            }
            
            RequestDispatcher dispacher = request.getRequestDispatcher("borrarUsuario.jsp");
            dispacher.forward(request, response);
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
