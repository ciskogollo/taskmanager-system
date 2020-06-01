/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.controller;

import com.taskmanager.entity.Usuario;
import com.taskmanager.session.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cisko
 */
@WebServlet(name = "ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/ControllerServlet",
                           "/login", "/tareas"})
public class ControllerServlet extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    public HttpSession session;
            
    @Override
    public void init() throws ServletException{
        try{
            getServletContext().setAttribute("usuario", usuarioFacade.findAll().get(0).getCorreo());
            getServletContext().setAttribute("validaruser", usuarioFacade.findAll().size());
        }catch(Exception e){
            throw new RuntimeException("Error consulting database. xd: ", e);
        }
    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        
        if(userPath.equals("/tareas")){
            
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try{
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        
        String userPath = request.getServletPath();

        // Si Login es requerida
        if (userPath.equals("/login")){
            //PROBANDO GETQUERYSTRING, NO FUNCA HAS5TA AHORA
            String formLogin = request.getQueryString();
            if(formLogin == null){
                //System.out.println("Datos no recibidos.");
                formLogin = "";
            }
            
            // get user from request (client)
            String name = request.getParameter("inputEmail");
            String pass = request.getParameter("inputPassword");
            
            //verificar a través de UsuarioFacade si existe user en BD
            Usuario userIn = new Usuario();
            userIn = usuarioFacade.verifUser(name, pass);
            if(userIn != null){
                System.out.println("Accediendo '"+name+"'...");
                session = request.getSession();
                session.setMaxInactiveInterval(60*60);
                //userIn = (Usuario) session.getAttribute("user");
                session.setAttribute("nameUser", userIn.getNombre());
                session.setAttribute("objUser", userIn);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }else{
                System.out.println("Datos erróneos >:(");
                // AQUI se debe agregar el msg de validación para el html
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";
        
        try{
            request.getRequestDispatcher(url).forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public boolean verifUser(String name, String hash){
        try{
            String nameResult = usuarioFacade.findByName(name).get(0).getNombre();
            String hashResult = usuarioFacade.findByName(name).get(0).getHash();
            if(name.equals(nameResult) && hash.equals(hashResult)){
                System.out.println("Nombre de usuario y contraseña correctos.");
                return true;
            }else{
                    System.out.println("Usuario no accedido.");
            }
        }catch(Exception ex){
            System.out.println("Imposible iniciar el usuario.");
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description del servlet y que pahaa";
    }// </editor-fold>

}
