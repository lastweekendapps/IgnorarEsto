<%-- 
    Document   : opciones
    Created on : 11/03/2017, 06:03:54 PM
    Author     : PC USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador de Base de Datos</title>
        <style>
            body {
                background-image: url(Hawaii.jpg);
                background-repeat: no-repeat;
                background-size: cover;
                margin: 0;
                padding: 0;
            }
            
            .logo{
                text-align: center;
            }
            
            input{
                margin-right: 20px;
            }
            
            .h4{
                text-align: center;
            }
            
            .btn {
                background: #3498db;
                background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
                background-image: -moz-linear-gradient(top, #3498db, #2980b9);
                background-image: -ms-linear-gradient(top, #3498db, #2980b9);
                background-image: -o-linear-gradient(top, #3498db, #2980b9);
                background-image: linear-gradient(to bottom, #3498db, #2980b9);
                -webkit-border-radius: 28;
                -moz-border-radius: 28;
                border-radius: 28px;
                text-shadow: 1px 1px 3px #666666;
                font-family: Arial;
                color: #ffffff;
                font-size: 12px;
                padding: 10px 20px 10px 20px;
                text-decoration: none;
            }

            .btn:hover {
                background: #3cb0fd;
                background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
                background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <br><br><br><br>
        <div class="logo"><image src="logo_pagina.png"/></div><br>
        <div class="h4"><h3>Escoge una de las siguientes opciones:</h3></div><br><br>
        <div>
            <table>
                <tr>
                    <td>
                        <form target="_blank" action="nuevoCliente.jsp">
                            <input class="btn" type="submit" value="Nuevo Cliente" style="margin-left: 200px">
                        </form>
                    </td>
                
                    <td>
                        <form target="_blank" action="buscarCliente.jsp">
                                           <input class="btn" type="submit" value="Buscar Cliente">
                        </form>
                    </td>
                    
                    <td>
                        <form target="_blank" action="editarCliente.jsp">
                            <input class="btn" type="submit" value="Editar Cliente">
                        </form>
                    </td>
                    
                    <td>
                        <form target="_blank" action="nuevoUsuario.jsp">
                            <input class="btn" type="submit" value="Nuevo Usuario">
                        </form>
                    </td>
                    
                    <td>
                        <form target="_blank" action="nuevoRecordatorio.jsp">
                            <input class="btn" type="submit" value="Nuevo Recordatorio">
                        </form>
                    </td>
                    
                    <td>
                        <form target="_blank" action="programas.jsp">
                            <input class="btn" type="submit" value="Programas">
                        </form>
                    </td>
                    <td>
                        <form target="_blank" action="borrarUsuario.jsp">
                            <input class="btn" type="submit" value="Borrar Usuario">
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
