/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import com.taskmanager.session.TareaFacade;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author cisko
 */
@ServerEndpoint("/wsted")
public class tareaedit {
    private static final Logger logger = Logger.getLogger("LoginWs");
   
    /* Funcionalidades en Bean*/
    @Inject
    private TareaFacade tareaFacade;
    
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void onOpen(Session peer){
        queue.add(peer);
       //loginBean.addSession(peer);
    }
    
    @OnMessage
    public void onMessage(String msg, Session peer){
        System.out.println("(WS)Nuevo msg ==> " + msg);
        //loginBean.enviar(peer, msg);
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
        //loginBean.removeSession(peer);
    }
}
