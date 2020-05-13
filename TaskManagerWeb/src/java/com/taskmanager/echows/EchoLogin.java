/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.echows;

import java.io.IOException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author cisko
 */
@ServerEndpoint("/ecoLogin")
public class EchoLogin {

    @OnMessage
    public void onMessage(Session session, String msg) {
        try{
            session.getBasicRemote().sendText(msg);
        }catch(IOException e){
            
        }
    }
    
}
