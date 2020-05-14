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
import javax.annotation.Resource;
//import javax.inject.Singleton;
//import javax.ejb.Startup;
//import javax.ejb.Timeout;
//import javax.ejb.TimerConfig;
//import javax.ejb.TimerService;
import com.taskmanager.websocket.LoginWs;
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
    private static final Logger logger = Logger.getLogger("LoginBean");
    
    private static final Set<Session> peers = new HashSet<>();
    
    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        logger.log(Level.INFO, "Initializing EJB.");
        random = new Random();
        System.out.println("varRandom: "+random.toString());
        //tservice.createIntervalTimer(500, 1000, new TimerConfig());
    }
    
    public void addSession(Session peer) {
        logger.log(Level.INFO, "Conexión abierta.");
        peers.add(peer);
    }
    
    public void removeSession(Session peer) {
        logger.log(Level.INFO, "Conexión cerrada.");
        System.out.println("Conn close: ("+peer.getId()+").");
        peers.remove(peer);
    }
    
    public void enviar(Session peer, String msg){
        System.out.println("--Enviando data--");
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        msg = String.format("%.2f / %d", price, volume);
        System.out.println("varMSG: "+msg);
        
        for(int i=0; i < peers.size(); i++){
            System.out.println("NAME-PEER:"+peers.getClass().getName());
        }
        
        for(Session session : peers){
            System.out.println("");
            try{
                peer.getBasicRemote().sendText(msg);
                System.out.println("Mensaje enviado:["+msg+"]");
            }catch(IOException ex){
                peers.remove(peer);
                System.out.println("ERROR:" + ex.toString());
                logger.log(Level.SEVERE, "Error al enviar msg al cliente(" + peer.getId() + ")", ex);
            }
        };
    }
    
    //*
    /* @Timeout
    /*public void timeout() {
    /*    System.out.println("--Enviando data--");
    /*    /* Adjust price and volume and send updates */
    /*    price += 1.0*(random.nextInt(100)-50)/100.0;
    /*    volume += random.nextInt(5000) - 2500;
    /*    LoginWs.send(price, volume);
    /*    LoginWs.sendpeers(price, volume);
    /*}
    //*/
}