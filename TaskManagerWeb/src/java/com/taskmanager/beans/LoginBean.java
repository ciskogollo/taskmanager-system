/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.beans;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import javax.websocket.Session;

/**
 *
 * @author cisko
 */
@Stateful
public class LoginBean {
    /* Use the container's timer service */
    //@Resource TimerService tservice;
    private Random random;
    private volatile double price = 100.0;
    private volatile int volume = 300000;
    private static final Logger loger = Logger.getLogger("LoginBean");
    
    private static final Set<Session> peers = new HashSet<>();
    
    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        loger.log(Level.INFO, "(WS)Initializing EJB.");
        random = new Random();
        System.out.println("varRandom: "+random.toString());
        //tservice.createIntervalTimer(500, 1000, new TimerConfig());
    }
    
    public void addSession(Session peer) {
        loger.log(Level.INFO, "(WS)Conexión abierta.");
        peers.add(peer);
    }
    
    public void removeSession(Session peer) {
        loger.log(Level.INFO, "(WS)Conexión cerrada.");
        System.out.println("Conn close: ("+peer.getId()+").");
        peers.remove(peer);
    }
    
    public void verifyUser(){
        System.out.println("--Verificando datos--");
    };
    
    public void enviar(Session peer, String msg){
        System.out.println("--Enviando data--");
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        msg = String.format("%.2f / %d", price, volume);
        System.out.println("varMSG: "+msg);
        
        for(int i=0; i < peers.size(); i++){
            System.out.println("(WS)NAME-PEER:"+peers.getClass().getName());
        }
        
        for(Session session : peers){
            System.out.println("");
            try{
                peer.getBasicRemote().sendText(msg);
                System.out.println("(WS)Mensaje enviado:["+msg+"]");
            }catch(IOException ex){
                peers.remove(peer);
                System.out.println("(WS)ERROR:" + ex.toString());
                loger.log(Level.SEVERE, "(WS)Error al enviar msg al cliente(" + peer.getId() + ")", ex);
            }
        };
    }
}