/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.beans;

import java.util.Random;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import com.taskmanager.websocket.LoginWs;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author cisko
 */
@Singleton
public class LoginBean {
    /* Use the container's timer service */
    @Resource TimerService tservice;
    private Random random;
    private volatile double price = 100.0;
    private volatile int volume = 300000;
    private static final Logger logger = Logger.getLogger("PriceVolumeBean");
    
    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        logger.log(Level.INFO, "Initializing EJB.");
        random = new Random();
        tservice.createIntervalTimer(1000, 1000, new TimerConfig());
    }
    
    @Timeout
    public void timeout() {
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        LoginWs.send(price, volume);
    }
}
