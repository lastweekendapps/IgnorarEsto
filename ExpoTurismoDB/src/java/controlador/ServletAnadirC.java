/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ClienteDAO;
import modelo.ClienteVO;
/**
 *
 * @author ayoro
 */
public class ServletAnadirC extends HttpServlet {
    private ClienteDAO cliente;
    private ClienteVO cvo;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            this.cliente = ClienteDAO.getClienteDAO();
            
            /* TODO output your page here. You may use following sample code. */
            
            int cedula = Integer.parseInt(request.getParameter("cedula"));
            String nombre = request.getParameter("nombre");
            String destino = request.getParameter("destino");
            int acompanantes = Integer.parseInt(request.getParameter("acompanantes"));
            String email = request.getParameter("email");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            
            boolean existe = this.cliente.idExistente(cedula);
            boolean registrado = this.cliente.usuarioRegistrado(cedula);
            
            
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAnadirC</title>");            
            out.println("</head>");
            out.println("<body>");
            if (!existe) {
                this.cvo = new ClienteVO(cedula, nombre, email, telefono);
                this.cliente.crearArchivo(cvo);
                out.println("<h1>Se ha agregado a " + nombre +" con ID "+ cedula +", email:  "+ email +" y teléfono "+ telefono +"</h1>\"");
            }else if(existe && !registrado){
                long pos = this.cliente.recuperarRegistro(cedula);
                
                boolean name = this.cliente.editarNombreCliente(cedula, nombre);
                boolean correo = this.cliente.editarEmailCliente(cedula, email);
                boolean tel = this.cliente.editarTelefonoCliente(cedula, telefono);
                
                if (name && correo && tel) {
                    out.println("<h1>Se ha agregado a " + nombre +" con ID "+ cedula +", email:  "+ email +" y teléfono "+ telefono +"</h1>\"");
                }else{
                    out.println("<h1>No se pudo agregar al cliente</h1>");
                }
            }else if(existe && registrado){
                out.println("<h1>El cliente ya existe. Por favor intente de nuevo</h1>");
                response.sendRedirect("nuevoCliente.jsp");
            }
                    
            out.println("</body>");
            out.println("</html>");
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
