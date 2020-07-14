/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.controller;

import com.taskmanager.entity.Funcion;
import com.taskmanager.entity.Tarea;
import com.taskmanager.entity.Usuario;
import com.taskmanager.entity.StatusWork;
import com.taskmanager.entity.Funcion;
import com.taskmanager.entity.Problema;
import com.taskmanager.session.ClienteFacade;
import com.taskmanager.session.StatusWorkFacade;
import com.taskmanager.session.FuncionFacade;
import com.taskmanager.session.ProblemaFacade;
import com.taskmanager.session.TareaFacade;
import com.taskmanager.session.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                           "",
                           "/index",
                           "/login",
                           "/logout",
                           "/tareas",
                           "/add-tarea",
                           "/edit-tarea",
                           "/confirmar-tarea",
                           "/del-tarea",
                           "/ver-tarea",
                           "/clientes",
                           "/problema"})
public class ControllerServlet extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TareaFacade tareaFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private StatusWorkFacade statusWorkFacade;
    @EJB
    private FuncionFacade funcionFacade;
    @EJB
    private ProblemaFacade problemaFacade;
    
    public HttpSession session;
    String userPath;
    String url;
            
    @Override
    public void init() throws ServletException{
        if(session != null){
            try{
                getServletContext().setAttribute("usuario", usuarioFacade.findAll().get(0).getCorreo());
                getServletContext().setAttribute("validaruser", usuarioFacade.findAll().size());
            }catch(Exception e){
                throw new RuntimeException("Error consulting database. xd: ", e);
            }
        }else{
            System.err.println("La <<conexion>> ha sido imposible. Xxd");
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
        userPath = request.getServletPath();
        
        if(session != null){
            switch(userPath){
                case "":
                    listarTareasUsuario(request);
                    break;
                case "/index":
                    getServletContext().setAttribute("Titulo", "Dashboard");
                    listarTareasUsuario(request);
                    /*try{
                        request.getRequestDispatcher(url).forward(request, response);
                    } catch (Exception ex) {
                        System.err.println();
                        ex.printStackTrace();
                    }*/
                    //response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
                case "/tareas":
                    getServletContext().setAttribute("Titulo", "Tareas");
                    listarTareasUsuario(request);
                    break;
                case "/add-tarea":
                    getServletContext().setAttribute("Titulo", "Agregar Tarea");
                    
                    listUsersRegistred();
                    listTasksRegistered();
                    listStatusWorks();
                    
                    //response.sendRedirect(request.getContextPath() + "/add-tarea.jsp");
                    break;
                case "/edit-tarea":
                    
                    break;
                case "/ver-tarea":
                    getServletContext().setAttribute("Titulo", "Ver/Editar Tarea");
                    
                    listUsersRegistred();
                    listTasksRegistered();
                    listStatusWorks();
                    
                    String idTarea = request.getParameter("id");
                    try {
                        Object tareaSelected = tareaFacade.findByIdTarea(new BigDecimal(idTarea));
                        //Tarea reTarSel = tareaFacade.findByIdTarea(new BigDecimal(idTarea)).get(0);
                        request.setAttribute("tareaSeleccionada", tareaFacade.findByIdTarea(new BigDecimal(idTarea)).get(0));
                        session.setAttribute("idTareaSeleccionadaSES", tareaFacade.findByIdTarea(new BigDecimal(idTarea)).get(0).getIdTarea());
                        session.setAttribute("IngresoTareaSeleccionadaSES", tareaFacade.findByIdTarea(new BigDecimal(idTarea)).get(0).getFechaIngreso());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "/confirmar-tarea":
                    String idTareaConf = request.getParameter("id");
                    try{
                        BigDecimal idTareaConfFmt = new BigDecimal(idTareaConf);
                        confirmarTarea(idTareaConfFmt);
                        response.sendRedirect(request.getContextPath() + "/tareas");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    break;
                case "/del-tarea":
                    String idTareaDel = request.getParameter("id");
                    try{
                        BigDecimal idTareaDelFmt = new BigDecimal(idTareaDel);
                        eliminarTarea(idTareaDelFmt);
                        response.sendRedirect(request.getContextPath() + "/tareas");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    
                    break;
                case "/clientes":
                    System.out.println(clienteFacade.findAll().get(0));
                    break;
                case "/problema":
                    listProblems();
                    
                    break;
                case "/logout":
                    try{
                        session.invalidate();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                default:
                    break;
            }
            // use RequestDispatcher to forward request internally
            switch (userPath) {
                case "":
                    url = userPath + "index.jsp";
                    break;
                case "/index":
                    url = userPath + ".jsp";
                    break;
                default:
                    url = "/WEB-INF/view" + userPath + ".jsp";
                    break;
            }
            System.out.println("urL: "+url);

            
            try{
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            System.err.println("La <<sesion>> no esta disponible. Operación anulada.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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
        switch (userPath) {
            case "/index":
                getServletContext().setAttribute("Titulo", "Dashboard");
                listarTareasUsuario(request);
                break;
            case "/login":
                getServletContext().setAttribute("Titulo", "Login - TMS");
                //PROBANDO GETQUERYSTRING, NO FUNCA HAS5TA AHORA
                String formLogin = request.getQueryString();
                if(formLogin == null){
                    //System.out.println("Datos no recibidos.");
                    formLogin = "";
                }   // get user from request (client)
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
                    session.setAttribute("idUser", userIn.getIdUsuario());
                    session.setAttribute("tareasUser", userIn.getTareaList());
                    session.setAttribute("objUser", userIn);
                    response.sendRedirect(request.getContextPath() + "/index");
                }else{
                    System.out.println("Datos erróneos >:(");
                    // AQUI se debe agregar el msg de validación para el html
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }   break;
            case "/tareas":
                break;
            case "/add-tarea":
                String formAddTarea = request.getQueryString();
                System.out.println("Recibiendo datos de Nueva Tarea...");
                String descrip = request.getParameter("txtDescriTarea");
                BigDecimal idUserResp = new BigDecimal(request.getParameter("selResponsableTarea"));
                BigDecimal idAntesces = new BigDecimal(request.getParameter("selTareaAntes"));
                Date fechaIngreso = new Date();
                String fechaPlRaw = request.getParameter("datePlazoTarea");
                System.out.println("INFO: fecPlazoRaw= " + fechaPlRaw);
                Date fechaPlazo = new Date();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fechaPlazo = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPlRaw);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try{
                    /*tareaFacade.crearTarea(descrip,fechaPlazo,responsable,(Usuario)session.getAttribute("objUser"));*/
                    BigDecimal idUltimaTarea = new BigDecimal(tareaFacade.findAll().size()).add(new BigDecimal(1));
                    Usuario responsable = usuarioFacade.findByIdUsuario(idUserResp).get(0);
                    Funcion funcTareaInit = funcionFacade.findAll().get(0);
                    StatusWork statusTareaInit = statusWorkFacade.findAll().get(0);
                    
                    Tarea newTarea;
                    //newTarea = new Tarea(idUltimaTarea, descrip, fechaIngreso, fechaPlazo, null, null, null, null, responsable, statusTareaInit);
                    Tarea tr = new Tarea();
                    tr.setIdTarea(idUltimaTarea);
                    tr.setDescripcion(descrip);
                    tr.setFechaIngreso(fechaIngreso);
                    tr.setFechaPlazo(fechaPlazo);
                    tr.setFechaRecepcion(null);
                    tr.setIdAntes(idAntesces);
                    tr.setIdSuces(null);
                    tr.setIdTsuperior(BigInteger.ZERO);
                    tr.setFuncionIdFuncion(funcTareaInit);
                    tr.setStatusWorkIdStatus(statusTareaInit);
                    tr.setUsuarioIdUsuario(responsable);
                    
                    //System.out.println("newTaarea: "+newTarea);
                    //tareaFacade.create(newTarea);
                    tareaFacade.create(tr);
                    response.sendRedirect(request.getContextPath() + "/index");
                }catch(Exception ex){
                    System.out.println("No se ha podido agregar la Tarea. "+ex);
                }break;
            case "/edit-tarea":
                String formEditTarea = request.getQueryString();
                System.out.println("Recibiendo datos de Tarea Editada...");
                System.out.println("- "+formEditTarea);
                
                String descr = request.getParameter("txtDescriTarea");
                String fecPlRaw = request.getParameter("datePlazoTarea");
                System.out.println("INFO: fecPlazoRaw= " + fecPlRaw);
                Date fecPlazo = new Date();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fecPlazo = new SimpleDateFormat("yyyy-MM-dd").parse(fecPlRaw);
                    System.out.println("INFO: fecPlazoFmtd= " + fecPlRaw);
                }catch (ParseException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                BigDecimal idUsResp = new BigDecimal(request.getParameter("selResponsableTarea"));
                BigDecimal idTarResp = new BigDecimal(request.getParameter("selTareaAntes"));
                BigDecimal idStatusResp = new BigDecimal(request.getParameter("selStatusWork"));
                try{
                    BigDecimal idTarea = (BigDecimal)session.getAttribute("idTareaSeleccionadaSES");
                    System.out.println("IDTAREA-EDITAR: "+idTarea);
                    Date FecIngTarea = (Date)session.getAttribute("IngresoTareaSeleccionadaSES");
                    System.out.println("FECHA-INGRESO-TAREA-EDITAR: "+FecIngTarea);
                    Usuario responsable = usuarioFacade.findByIdUsuario(idUsResp).get(0);
                    Funcion funcTareaInit = funcionFacade.findAll().get(0);
                    //StatusWork statusTareaInit = statusWorkFacade.findAll().get(0);
                    System.out.println("IDSTATUS-EDITAR: "+idStatusResp);
                    StatusWork statusTarea = statusWorkFacade.findByIdStatus(idStatusResp).get(0);
                    
                    Tarea tr = new Tarea();
                    tr.setIdTarea(idTarea);
                    tr.setDescripcion(descr);
                    tr.setFechaIngreso(FecIngTarea);
                    tr.setFechaPlazo(fecPlazo);
                    tr.setFechaRecepcion(null);
                    tr.setIdAntes(idTarResp);
                    tr.setIdSuces(null);
                    tr.setIdTsuperior(BigInteger.ZERO);
                    tr.setFuncionIdFuncion(funcTareaInit);
                    tr.setStatusWorkIdStatus(statusTarea);
                    tr.setUsuarioIdUsuario(responsable);
                    
                    //System.out.println("newTaarea: "+newTarea);
                    //tareaFacade.create(newTarea);
                    tareaFacade.edit(tr);
                }catch(Exception ex){
                    System.out.println("ERROR: No se ha podido editar la Tarea - "+ex);
                }
                response.sendRedirect(request.getContextPath() + "/index");
                break;
            case "/ver-tarea":
                
                break;
            case "/problema":
                String formAddProblem = request.getQueryString();
                System.out.println("Recibiendo datos de Nuevo Problema...");
                String comentario = request.getParameter("txtComentario");
                //idUserResp = new BigDecimal(request.getParameter("selResponsableTarea"));
                try{
                    BigDecimal idUltimoProblema = new BigDecimal(problemaFacade.findAll().size()).add(new BigDecimal(1));
                    idUserResp = new BigDecimal(session.getAttribute("idUser").toString());
                    Usuario responsable = usuarioFacade.findByIdUsuario(idUserResp).get(0);
                    
                    Problema pr = new Problema();
                    pr.setIdProblema(idUltimoProblema);
                    pr.setComentario(comentario);
                    pr.setUsuarioIdUsuario(responsable);
                    
                    System.out.println("newProblema: "+pr);
                    problemaFacade.create(pr);
                    
                    response.sendRedirect(request.getContextPath() + "/index");
                }catch(Exception ex){
                    System.out.println("No se ha podido agregar la Tarea. "+ex);
                }
                break;
            default:
                break;
        }
        
        // use RequestDispatcher to forward request internally
        //String url = "/WEB-INF/view" + userPath + ".jsp";
        //try{
        //    request.getRequestDispatcher(url).forward(request, response);
        //}catch(Exception ex){
        //    ex.printStackTrace();
        //}
    }
    
    /****************/
    /*    MÉTODOS   */
    /****************/
    
    public boolean verifUser(String name, String hash){
        System.out.println("Solicitando acceso '"+name+"'...");
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
    
    public void listarTareasUsuario(HttpServletRequest request){
        System.out.println("Notice: Listando tareas del Usuario...");

        Usuario idUserSession = (Usuario)session.getAttribute("objUser");
        Tarea tareaPP = new Tarea();
        tareaPP = tareaFacade.findAll().get(0);

        try{
            List<Tarea> listTareas = tareaFacade.findByIdResponsable(idUserSession);
            request.setAttribute("listTareas", listTareas);
        }catch(Exception e){
            System.out.println("Error: Listando tareas del usuario. - "+e);
        }
    }
    
    public void listUsersRegistred() {
        // Listar topdos los users registrados
        try{
            List<Usuario> listTotalUsers = usuarioFacade.findAll();
            getServletContext().setAttribute("usuariosRegistrados", listTotalUsers);
            System.out.println("Notice: Listados usuarios registrados.");
        }catch(Exception e){
            System.err.println("Error: Listando usuarios regs. - "+e);
        }
    }
    
    public void listTasksRegistered(){
        // Listar todas las tareas registrados
        try{
            List<Tarea> listTotalTareas = tareaFacade.findAll();
            getServletContext().setAttribute("tareasRegistradas", listTotalTareas);
        }catch(Exception e){
            System.out.println("Error: Listando tareas regs. - "+e);
        }
    }
    
    public void listStatusWorks(){
        // Listar todos los estados de tareas posibles
        try{
            List<StatusWork> listTotalStatus = statusWorkFacade.findAll();
            getServletContext().setAttribute("estadosRegistrados", listTotalStatus);
            System.out.println("Notice: Listados Status registrados.");
        }catch(Exception e){
            System.out.println("Error: Listando estados regs. - "+e);
        }
    }
    
    public void listProblems(){
        // Listar todos los problemas registrados
        try{
            List<Problema> listTotalProblems = problemaFacade.findAll();
            getServletContext().setAttribute("problemasRegistrados", listTotalProblems);
        }catch(Exception e){
            System.out.println("Error: Listando problemas regs. - "+e);
        }
    }
    
    public void eliminarTarea(BigDecimal id){
        Tarea objToDel = tareaFacade.find(id);
        tareaFacade.remove(objToDel);
    }
    
    public void confirmarTarea(BigDecimal id){
        System.out.println("Notice: Confirmando tarea...");
        Tarea objToConf = tareaFacade.find(id);
        StatusWork objStatus = statusWorkFacade.find(new BigDecimal(4));
        System.out.println("STATUS (4): "+objStatus);
        objToConf.setStatusWorkIdStatus(objStatus);
        System.out.println("TAREA Confirmada: "+objToConf);
        tareaFacade.edit(objToConf);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description del servlet y que pahaa";
    }// </editor-fold>

}
