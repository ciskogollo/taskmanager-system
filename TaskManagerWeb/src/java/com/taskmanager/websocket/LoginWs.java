/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import java.util.Set;
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
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.json.JSONObject;
import org.json.JSONException;

/**
 *
 * @author cisko
 */
@ApplicationScoped
@ServerEndpoint("/loginws")
public class LoginWs extends AbstractWs {
    /* Funcionalidades en Bean*/
    @Inject
    private LoginBean loginBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    public static final Logger logger = Logger.getLogger("LoginWS");
    public static final Set<Session> queue = new HashSet<>();
    private ArrayList<Usuario> userList = new ArrayList<Usuario>();
    
    JSONObject jsonObj;

    @OnOpen
    public void onOpen(Session peer){
        queue.add(peer);
        loginBean.addSession(peer);
    }
    
    @OnMessage
    public void onMessage(String msg, Session peer){
        System.out.println("(WS)Nueva conexión ==> " + msg);
        /*
        try {
            jsonObj = new JSONObject(msg);
            //System.out.println("PARAM:"+jsonObj.getJSONObject("param"));
        } catch (JSONException e) {
            System.err.println("Error: "+e.toString());
        }
        JSONObject usrOBj = new JSONObject(jsonObj.getJSONObject("param").toString());
        */
        jsonObj = parseToJson(msg);
        Usuario userIn = new Usuario();
        String name="", pass="";
        if(jsonObj != null){
            //Tomar datos recibidos del ws
            name = jsonObj.get("user").toString();
            pass = jsonObj.get("hash").toString();
            userIn = usuarioFacade.verifUser(name, pass);
        }
        /*
        String name = usrOBj.get("user").toString();
        String pass = usrOBj.get("hash").toString();
        */
        String response;
        
        if(userIn != null){
            System.out.println("(WS)Accediendo '"+name+"'...");
            if(userIn.getRolIdRol().getNombreRol().equals("Administrador")){
                userList.add(userIn);
                response = "logued";
                
                JSONObject jsonObjResp = new JSONObject();
                jsonObjResp.put("type", "event");
                jsonObjResp.put("event", "runFunction");
                jsonObjResp.put("param", response);
                
                replyMsg(peer,jsonObjResp.toString());
                System.out.println("(WS)USER ACCEPTED");
            }else{
                System.out.println("(WS)USER DENIED");
            }
            
        }else{
            response = "user_incorrect";
            System.out.println("Datos erróneos >:(");
            JSONObject jsonObjResp = new JSONObject();
            jsonObjResp.put("type", "event");
            jsonObjResp.put("event", "notice");
            jsonObjResp.put("param", response);
            replyMsg(peer,jsonObjResp.toString());
        }
    }
    
    public void replyMsg(Session peer, String msg){
        try{
            peer.getBasicRemote().sendText(msg);
            System.out.println("(WS)SUCCESS: Enviado '"+msg+"'");
        }catch(IOException e){
            System.out.println("(WS)ERROR:" + e.toString());
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
