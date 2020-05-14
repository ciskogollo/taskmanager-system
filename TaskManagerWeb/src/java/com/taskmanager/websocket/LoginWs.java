/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
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
import javax.inject.Inject;

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
    
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    
    
    
    public static void sendpeers(double price, int volume){
        String msg = String.format("%.2f / %d", price, volume);
        try {
            /* Send updates to all open WebSocket sessions */
            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
                logger.log(Level.INFO, "Sent: {0}", msg);
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
    
    @OnOpen
    public void onOpen(Session peer){
        queue.add(peer);
        loginBean.addSession(peer);
    }
    
    @OnMessage
    public void onMessage(String msg, Session peer){
        System.out.println("Nuevo msg ==> " + msg);
        loginBean.enviar(peer, msg);
    }
    
    @OnError
    public void onError(Session peer, Throwable e) {
        System.out.println("ERROR: " + e.getMessage());
        
        queue.remove(peer);
        logger.log(Level.INFO, "Connection error:");
        logger.log(Level.INFO, e.toString());
    }
    
    @OnClose
    public void onClose (Session peer){
        queue.remove(peer);
        loginBean.removeSession(peer);
    }
    
}
