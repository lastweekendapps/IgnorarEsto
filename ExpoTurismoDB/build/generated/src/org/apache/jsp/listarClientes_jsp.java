package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class listarClientes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                background-image: url(Dubai.jpg);\n");
      out.write("                background-repeat: no-repeat;\n");
      out.write("                background-size: cover;\n");
      out.write("                margin: 0;\n");
      out.write("                padding: 0;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .logo{\n");
      out.write("                text-align: right;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .title{\n");
      out.write("                text-align: center;\n");
      out.write("                color: #ffffff;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .subtitle{\n");
      out.write("                color: #000000;\n");
      out.write("                margin-left: 370px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .form{\n");
      out.write("                text-align: center;\n");
      out.write("                margin-left: 370px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .editar{\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .buscar{\n");
      out.write("                width: 490px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .confC{\n");
      out.write("                margin-left: 90px;\n");
      out.write("                padding-left: 50px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .tabla {\n");
      out.write("                font-family: arial, sans-serif;\n");
      out.write("                border-collapse: collapse;\n");
      out.write("                width: 60%;\n");
      out.write("                margin-left: 290px;\n");
      out.write("            }\n");
      out.write("            .tabla td, th {\n");
      out.write("                border: 5px solid #dddddd;\n");
      out.write("                text-align: left;\n");
      out.write("                padding: 8px;\n");
      out.write("            }\n");
      out.write("            .tabla tr:nth-child(even) {\n");
      out.write("                background-color: #dddddd;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .btn {\n");
      out.write("                background: #3498db;\n");
      out.write("                background-image: -webkit-linear-gradient(top, #3498db, #2980b9);\n");
      out.write("                background-image: -moz-linear-gradient(top, #3498db, #2980b9);\n");
      out.write("                background-image: -ms-linear-gradient(top, #3498db, #2980b9);\n");
      out.write("                background-image: -o-linear-gradient(top, #3498db, #2980b9);\n");
      out.write("                background-image: linear-gradient(to bottom, #3498db, #2980b9);\n");
      out.write("                -webkit-border-radius: 28;\n");
      out.write("                -moz-border-radius: 28;\n");
      out.write("                border-radius: 28px;\n");
      out.write("                text-shadow: 1px 1px 3px #666666;\n");
      out.write("                font-family: Arial;\n");
      out.write("                color: #ffffff;\n");
      out.write("                font-size: 12px;\n");
      out.write("                padding: 10px 20px 10px 20px;\n");
      out.write("                text-decoration: none;\n");
      out.write("            }\n");
      out.write("            .btn:hover {\n");
      out.write("                background: #3cb0fd;\n");
      out.write("                background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);\n");
      out.write("                background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);\n");
      out.write("                background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);\n");
      out.write("                background-image: -o-linear-gradient(top, #3cb0fd, #3498db);\n");
      out.write("                background-image: linear-gradient(to bottom, #3cb0fd, #3498db);\n");
      out.write("                text-decoration: none;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .enjoy-input {\n");
      out.write("                display: inline-block;\n");
      out.write("                -webkit-box-sizing: content-box;\n");
      out.write("                -moz-box-sizing: content-box;\n");
      out.write("                box-sizing: content-box;\n");
      out.write("                padding: 5px 20px;\n");
      out.write("                border: 1px solid #b7b7b7;\n");
      out.write("                -webkit-border-radius: 3px;\n");
      out.write("                border-radius: 3px;\n");
      out.write("                font: normal medium/normal Arial, Helvetica, sans-serif;\n");
      out.write("                color: rgba(0,142,198,1);\n");
      out.write("                -o-text-overflow: clip;\n");
      out.write("                text-overflow: clip;\n");
      out.write("                background: rgba(252,252,252,1);\n");
      out.write("                -webkit-box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;\n");
      out.write("                box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;\n");
      out.write("                text-shadow: 1px 1px 0 rgba(255,255,255,0.66) ;\n");
      out.write("                -webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("            }\n");
      out.write("            .enjoy-input:hover {\n");
      out.write("                border: 1px solid #a3a3a3;\n");
      out.write("                background: rgba(255,255,255,1);\n");
      out.write("                -webkit-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -moz-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -o-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("            }\n");
      out.write("            .enjoy-input:focus {\n");
      out.write("                border: 1px solid #018dc4;\n");
      out.write("                -webkit-box-shadow: 4px 4px 4px 0 rgba(0,0,0,0.2) inset;\n");
      out.write("                box-shadow: 4px 4px 4px 0 rgba(0,0,0,0.2) inset;\n");
      out.write("                -webkit-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -moz-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                -o-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("                transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .divT{\n");
      out.write("                display: inline-block;\n");
      out.write("                text-align: center;\n");
      out.write("                height: 300px;\n");
      out.write("                color: #ffffff;\n");
      out.write("                overflow: auto;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </style>    \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"logo\"><image src=\"logo_pagina.png\"/></div>\n");
      out.write("        <div class=\"title\"><h2>LISTA DE CLIENTES</h2></div><br>\n");
      out.write("        <div class=\"divT\">\n");
      out.write("            <table class=\"tabla\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>Cedula</th>\n");
      out.write("                    <th>Nombre</th>\n");
      out.write("                    <th>Teléfono</th>\n");
      out.write("                    <th>Email</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    //ClienteDAO cliente = ClienteDAO.getClienteDAO();
                    ArrayList registros = (ArrayList)request.getAttribute("registros");
                    for (int i = 0; i < registros.size(); i+=4) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(registros.get(i));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(registros.get(i+1));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(registros.get(i+2));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(registros.get(i+3));
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");
  }   
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
