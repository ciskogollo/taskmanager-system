/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.session;

import com.taskmanager.entity.Problema;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cisko
 */
@Stateless
public class ProblemaFacade extends AbstractFacade<Problema> {

    @PersistenceContext(unitName = "TaskManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProblemaFacade() {
        super(Problema.class);
    }
    
}
