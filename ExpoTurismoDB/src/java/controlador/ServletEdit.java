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
/**
 *
 * @author ayoro
 */
public class ServletEdit extends HttpServlet {
    ClienteDAO cliente;
    int cc;
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
        this.cliente = ClienteDAO.getClienteDAO();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>entrooooo</h1>");
            String nombreNuevo = request.getParameter("nombre");
            System.out.println("nombreN:" + nombreNuevo);
            if (nombreNuevo == "") {
                System.out.println("ENTROOOO");
                int cedula = Integer.parseInt(request.getParameter("busqueda"));
                this.cc = cedula;
                long pos = this.cliente.buscarCliente(cedula);
                String nombre = "";
                String correo = "";
                int telefono = 0;
                String tel = "";

                if (pos == -1) {
                    response.sendRedirect("buscarCliente.jsp");
                } else {
                    char name[] = this.cliente.leerChars(pos + 4);
                    for (int i = 0; i < name.length; i++) {
                        if (name[i] == '\u0000') {
                            break;
                        } else {
                            nombre += name[i];
                        }
                    }

                    char corr[] = this.cliente.leerChars(pos + 44);
                    for (int i = 0; i < corr.length; i++) {
                        if (corr[i] == '\u0000') {
                            break;
                        } else {
                            correo += corr[i];
                        }
                    }
                    telefono = this.cliente.leerEntero(pos + 84);
                    tel = Integer.toString(telefono);
                }
                String ced = "";
                ced = Integer.toString(cedula);

                request.setAttribute("cedula", ced);
                request.setAttribute("nombre", nombre);
                request.setAttribute("email", correo);
                request.setAttribute("telefono", tel);

                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
            } else {
                String emailNuevo = request.getParameter("email");
                int telefonoNuevo = Integer.parseInt(request.getParameter("telefono"));
                this.cliente.editarNombreCliente(cc, nombreNuevo);
                this.cliente.editarEmailCliente(cc, emailNuevo);
                this.cliente.editarTelefonoCliente(cc, telefonoNuevo);
                out.println("<h1>Se ha editado el cliente satisfactoriamente</h1>");
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
