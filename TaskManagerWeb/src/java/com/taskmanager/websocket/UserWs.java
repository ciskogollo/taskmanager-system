/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import com.taskmanager.entity.Cliente;
import com.taskmanager.entity.Usuario;
import com.taskmanager.entity.Unidad;
import com.taskmanager.entity.Rol;
import com.taskmanager.entity.Proceso;
import com.taskmanager.session.UsuarioFacade;
import com.taskmanager.session.UnidadFacade;
import com.taskmanager.session.RolFacade;
import com.taskmanager.session.ProcesoFacade;
import com.taskmanager.session.ClienteFacade;
import static com.taskmanager.websocket.LoginWs.queue;
import static com.taskmanager.websocket.LoginWs.logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONObject;

/**
 *
 * @author cisko
 */
@ApplicationScoped
@ServerEndpoint("/userws")
public class UserWs extends AbstractWs {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private UnidadFacade unidadFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    @EJB
    private ClienteFacade clienteFacade;
    
    @OnMessage
    public void onMessage(String msg, Session peer) {
        System.out.println("(WS)Nueva solicitud ==> " + msg);
        jsonObj = parseToJson(msg);
        
        String event = jsonObj.get("event").toString();
        String objParam = jsonObj.get("obj").toString();
        
        JSONObject jsonObjAll = new JSONObject();
        //JSONObject jsonObjTemp = new JSONObject();
        JSONObject jsonObjUser = new JSONObject();
        JSONObject jsonObjUnit = new JSONObject();
        JSONObject jsonObjRole = new JSONObject();
        JSONObject jsonObjProcess = new JSONObject();
        JSONObject jsonObjResp = new JSONObject();
        
        if(jsonObj != null){
            switch(event){
                case "listar":
                    switch(objParam){
                        case "dashboard":
                            //Crear Json con Usuarios
                            for(Usuario usr : listarUsuarios()){
                                JSONObject jsonUserTemp = new JSONObject();
                                jsonUserTemp.put("id", usr.getIdUsuario().toString());
                                jsonUserTemp.put("nombre", usr.getNombre());
                                jsonUserTemp.put("rut", usr.getRut());
                                jsonUserTemp.put("correo", usr.getCorreo());
                                
                                jsonObjUser.put("user"+usr.getIdUsuario().toString(), jsonUserTemp);
                            }
                            //Crear Json con Unidades
                            for(Unidad und : listarUnidades()){
                                JSONObject jsonUnitTemp = new JSONObject();
                                jsonUnitTemp.put("id", und.getIdUnidad().toString());
                                jsonUnitTemp.put("nombre", und.getTipoUnidad());
                                jsonUnitTemp.put("proceso", und.getIdProceso().toString());
                                
                                jsonObjUnit.put("unit"+und.getIdUnidad().toString(), jsonUnitTemp);
                            }
                            //Crear Json con Roles
                            for(Rol role : listarRoles()){
                                JSONObject jsonRoleTemp = new JSONObject();
                                jsonRoleTemp.put("id", role.getIdRol().toString());
                                jsonRoleTemp.put("nombre", role.getNombreRol());
                                
                                jsonObjRole.put("role"+role.getIdRol().toString(), jsonRoleTemp);
                            }
                            //Crear Json con Procesos
                            for(Proceso prc : listarProcesos()){
                                JSONObject jsonProcTemp = new JSONObject();
                                jsonProcTemp.put("id", prc.getIdProceso().toString());
                                jsonProcTemp.put("nombre", prc.getTipoProceso());
                                jsonProcTemp.put("nombreusuario", prc.getUsuarioIdUsuario().getNombre());
                                
                                jsonObjProcess.put("process"+prc.getIdProceso().toString(), jsonProcTemp);
                            }
                            
                            //Empaquetando en Json los json-list
                            jsonObjAll.put("userlist", jsonObjUser);
                            jsonObjAll.put("unitlist", jsonObjUnit);
                            jsonObjAll.put("rolelist", jsonObjRole);
                            jsonObjAll.put("processlist", jsonObjProcess);
                            
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "dashboard");
                            jsonObjResp.put("param", jsonObjAll);
                            //replyMsg(peer, jsonObjResp.toString());
                            break;
                        case "usuario":
                            //Crear Json con Roles
                            for(Rol role : listarRoles()){
                                JSONObject jsonRoleTemp = new JSONObject();
                                jsonRoleTemp.put("id", role.getIdRol().toString());
                                jsonRoleTemp.put("nombre", role.getNombreRol());
                                
                                jsonObjRole.put("role"+role.getIdRol().toString(), jsonRoleTemp);
                            }
                            //Empaquetando en Json los json-list
                            jsonObjAll.put("rolelist", jsonObjRole);
                            
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "usuario");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        case "unidad":
                            //Crear Json con Procesos
                            for(Proceso prc : listarProcesos()){
                                JSONObject jsonProcTemp = new JSONObject();
                                jsonProcTemp.put("id", prc.getIdProceso().toString());
                                jsonProcTemp.put("nombre", prc.getTipoProceso());
                                jsonProcTemp.put("nombreusuario", prc.getUsuarioIdUsuario().getNombre());
                                
                                jsonObjProcess.put("process"+prc.getIdProceso().toString(), jsonProcTemp);
                            }
                            //Empaquetando en Json los json-list
                            jsonObjAll.put("processlist", jsonObjProcess);
                            
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "unidad");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        case "proceso":
                            //Crear Json con Procesos
                            for(Cliente clt : listarClientes()){
                                JSONObject jsonProcTemp = new JSONObject();
                                jsonProcTemp.put("id", clt.getIdCliente().toString());
                                jsonProcTemp.put("nombre", clt.getNombreCliente());
                                jsonProcTemp.put("habilitado", clt.getHabilitado());
                                
                                jsonObjProcess.put("client"+clt.getIdCliente().toString(), jsonProcTemp);
                            }
                            //Crear Json con Usuarios
                            for(Usuario usr : listarUsuarios()){
                                JSONObject jsonUserTemp = new JSONObject();
                                jsonUserTemp.put("id", usr.getIdUsuario().toString());
                                jsonUserTemp.put("nombre", usr.getNombre());
                                jsonUserTemp.put("rut", usr.getRut());
                                jsonUserTemp.put("correo", usr.getCorreo());
                                
                                jsonObjUser.put("user"+usr.getIdUsuario().toString(), jsonUserTemp);
                            }
                            //Empaquetando en Json los json-list
                            jsonObjAll.put("clientlist", jsonObjProcess);
                            jsonObjAll.put("userlist", jsonObjUser);
                            
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "proceso");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        default:
                            break;
                    }
                    break;
                case "registrar":
                    JSONObject jsonDatos;
                    JSONObject objDatos = new JSONObject(jsonObj.get("datos").toString());
                    //JSONObject objDatoss = new JSONObject(jsonObj.get.toString());
                    //Object objDatosss = jsonObj.get("datos");
                    System.out.println("objDatos: "+objDatos);
                    //jsonDatos = parseUsuario(objDatos);
                    //System.out.println("jsonDatos: "+jsonDatos);
                    switch(objParam){
                        case "usuario":
                            String nombre,correo,hash,rut,direccion,selRol;
                            nombre = objDatos.get("nombre").toString();
                            correo = objDatos.get("correo").toString();
                            hash = objDatos.get("hash").toString();
                            rut = objDatos.get("rut").toString();
                            direccion = objDatos.get("direccion").toString();
                            selRol = objDatos.get("selRol").toString();
                            BigDecimal idUser = new BigDecimal(usuarioFacade.findAll().size()).add(new BigDecimal(1));
                            Rol objRol = rolFacade.findByName(selRol).get(0);
                            
                            Usuario usr = new Usuario();
                            usr.setIdUsuario(idUser);
                            usr.setNombre(nombre);
                            usr.setCorreo(correo);
                            usr.setHash(hash);
                            usr.setRut(rut);
                            usr.setDireccion(direccion);
                            usr.setRolIdRol(objRol);
                            usuarioFacade.create(usr);
                            
                            jsonObjAll.put("registro","exitoso");
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "register_result");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        case "unidad":
                            String proceso, nombreUnid;
                            proceso = objDatos.get("proceso").toString();
                            nombreUnid = objDatos.get("nombre").toString();
                            BigDecimal idUnit = new BigDecimal(unidadFacade.findAll().size()).add(new BigDecimal(1));
                            Proceso objProc = procesoFacade.findByTipoProceso(proceso).get(0);
                            BigDecimal idProc = objProc.getIdProceso();
                            
                            Unidad und = new Unidad();
                            und.setIdUnidad(idUnit);
                            und.setIdProceso(idProc);
                            und.setTipoUnidad(nombreUnid);
                            unidadFacade.create(und);
                            
                            jsonObjAll.put("registro","exitoso");
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "register_result");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        case "rol":
                            String nombreRol;
                            nombreRol = objDatos.get("rol").toString();
                            BigDecimal idRol = new BigDecimal(rolFacade.findAll().size()).add(new BigDecimal(1));
                            
                            Rol rle = new Rol();
                            rle.setIdRol(idRol);
                            rle.setNombreRol(nombreRol);
                            rolFacade.create(rle);
                            
                            jsonObjAll.put("registro","exitoso");
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "register_result");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                        case "proceso":
                            String nombreProc,cliente,user;
                            BigDecimal idProce = new BigDecimal(procesoFacade.findAll().size()).add(new BigDecimal(1));
                            nombreProc = objDatos.get("nombre").toString();
                            cliente = objDatos.get("selClient").toString();
                            user = objDatos.get("selUser").toString();
                            Cliente objClient = clienteFacade.findByNombreCliente(cliente).get(0);
                            Usuario objUs = usuarioFacade.findByName(user).get(0);
                            
                            Proceso pso = new Proceso();
                            pso.setIdProceso(idProce);
                            pso.setTipoProceso(nombreProc);
                            pso.setClienteIdCliente(objClient);
                            pso.setUsuarioIdUsuario(objUs);
                            procesoFacade.create(pso);
                            
                            jsonObjAll.put("registro","exitoso");
                            jsonObjResp.put("type", "response");
                            jsonObjResp.put("event", "register_result");
                            jsonObjResp.put("param", jsonObjAll);
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        replyMsg(peer, jsonObjResp.toString());
    }
    
    @OnError
    public void onError(Session peer, Throwable e) {
        System.out.println("(WS)ERROR: " + e.getMessage());
        
        queue.remove(peer);
        logger.log(Level.INFO, "(WS)Connection error:");
        logger.log(Level.INFO, e.toString());
    }
    
    public void replyMsg(Session peer, String msg){
        try{
            peer.getBasicRemote().sendText(msg);
            System.out.println("(WS)SUCCESS: Enviado '"+msg+"'");
        }catch(IOException e){
            System.out.println("(WS)ERROR:" + e.toString());
        }
    }
    
    public List<Usuario> listarUsuarios(){
        try{
            List<Usuario> listUsers;
            listUsers = usuarioFacade.findAll();
            System.out.println("Listando usuarios: " + listUsers.toString());
            return listUsers;
        }catch(Exception ex){
            System.err.println("(WS)No se han podido listar los usuarios. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
    
    public List<Unidad> listarUnidades(){
        try{
            List<Unidad> listUnidades;
            listUnidades = unidadFacade.findAll();
            System.out.println("Listando unidades: " + listUnidades.toString());
            return listUnidades;
        }catch(Exception ex){
            System.err.println("(WS)No se han podido listar las unidades. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
    
    public List<Rol> listarRoles(){
        try{
            List<Rol> listRoles;
            listRoles = rolFacade.findAll();
            System.out.println("Listando roles: " + listRoles.toString());
            return listRoles;
        }catch(Exception ex){
            System.err.println("(WS)No se han podido listar los roles. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
    
    public List<Proceso> listarProcesos(){
        try{
            List<Proceso> listProcesos;
            listProcesos = procesoFacade.findAll();
            System.out.println("Listando procesos: " + listProcesos.toString());
            return listProcesos;
        }catch(Exception ex){
            System.err.println("(WS)No se han podido listar los procesos. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
    
    public List<Cliente> listarClientes(){
        try{
            List<Cliente> listClientes;
            listClientes = clienteFacade.findAll();
            System.out.println("Listando clientes: " + listClientes.toString());
            return listClientes;
        }catch(Exception ex){
            System.err.println("(WS)No se han podido listar los clientes. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
}
