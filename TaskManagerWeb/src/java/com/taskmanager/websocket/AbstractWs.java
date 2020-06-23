/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.websocket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cisko
 */
public abstract class AbstractWs {
    //private Class<T> wsClass;
    JSONObject jsonObj;
    
    /*public AbstractWs(Class<T> wsClass){
        this.wsClass = wsClass;
    }
    */

    public AbstractWs() {
    }

    
    public JSONObject parseToJson(String msg){
        try {
            jsonObj = new JSONObject(msg);
            JSONObject usrOBj = new JSONObject(jsonObj.getJSONObject("param").toString());
            return usrOBj;
        } catch (JSONException e) {
            System.err.println("Error: "+e.toString());
        }
        return null;
    }
    
    public void runFunction(String param){
        
        
    }
}
