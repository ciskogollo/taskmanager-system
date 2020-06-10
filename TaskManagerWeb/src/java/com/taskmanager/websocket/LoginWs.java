/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.taskmanager.beans.LoginBean;
import com.taskmanager.entity.Usuario;
import com.taskmanager.session.UsuarioFacade;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.JSONException;

/**
 *
 * @author cisko
 */
@ApplicationScoped
@ServerEndpoint("/LoginWs")
public class LoginWs {
    private static final Logger logger = Logger.getLogger("LoginWs");
   
    /* Funcionalidades en Bean*/
    @Inject
    private LoginBean loginBean;
    /*@EJB
    private UsuarioFacade usuarioFacade;
    */
    
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    //public HttpSession session;
    JSONObject jsonObj;

    @OnOpen
    public void onOpen(Session peer){
        queue.add(peer);
        loginBean.addSession(peer);
    }
    
    @OnMessage
    public void onMessage(String msg, Session peer){
        System.out.println("(WS)Nuevo msg ==> " + msg);
        try {
            jsonObj = new JSONObject(msg);
            //System.out.println("PARAM:"+jsonObj.getJSONObject("param"));
        } catch (JSONException e) {
            System.err.println("Error: "+e.toString());
        }
        JSONObject usrOBj = new JSONObject(jsonObj.getJSONObject("param").toString());

        String name = usrOBj.get("user").toString();
        String pass = usrOBj.get("hash").toString();
        Usuario userIn = new Usuario();
        //userIn = usuarioFacade.verifUser(name, pass);
        
        if(userIn != null){
            System.out.println("Accediendo '"+name+"'...");
            /*session.setAttribute("peer", peer);
            session.setMaxInactiveInterval(60*60);
            session.setAttribute("nameUser", userIn.getNombre());
            session.setAttribute("idUser", userIn.getIdUsuario());
            session.setAttribute("tareasUser", userIn.getTareaList());
            session.setAttribute("objUser", userIn);
            */
            
        }else{
            System.out.println("Datos erróneos >:(");
        }
    }
    
    @OnError
    public void onError(Session peer, Throwable e) {
        System.out.println("(WS)ERROR: " + e.getMessage());
        
        queue.remove(peer);
        logger.log(Level.INFO, "(WS)Connection error:");
        logger.log(Level.INFO, e.toString());
    }
    
    @OnClose
    public void onClose (Session peer){
        queue.remove(peer);
        loginBean.removeSession(peer);
    }
    
}
