/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import com.taskmanager.entity.Usuario;
import com.taskmanager.session.UsuarioFacade;
import static com.taskmanager.websocket.LoginWs.queue;
import static com.taskmanager.websocket.LoginWs.logger;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.ejb.EJB;
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
@ServerEndpoint("/userws")
public class UserWs extends AbstractWs {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @OnMessage
    public void onMessage(String msg, Session peer) {
        System.out.println("(WS)Nuevo msg ==> " + msg);
        jsonObj = parseToJson(msg);
        String event = jsonObj.get("event").toString();
        String objParam = jsonObj.get("obj").toString();
        if(jsonObj != null){
            switch(event){
                case "listar":
                    switch(objParam){
                        case "usuario":
                            JSONObject jsonObjUser = new JSONObject();
                            for(Usuario usr : listarUsuarios()){
                                jsonObjUser.put(usr.getIdUsuario().toString(), usr.getNombre());
                            }
                            JSONObject jsonObjResp = new JSONObject();
                            jsonObjResp.put("type", "event");
                            jsonObjResp.put("event", "list");
                            jsonObjResp.put("param", jsonObjResp);
                            
                            replyMsg(peer, jsonObjResp.toString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
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
            return listUsers = usuarioFacade.findAll();
        }catch(Exception ex){
            System.err.println("(WS)No se ha podido listar los usuarios. "+ex.toString());
            throw new RuntimeException("Listing Exception",ex);
        }
    }
}
