/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.session;

import com.taskmanager.entity.Proceso;
import com.taskmanager.entity.Usuario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cisko
 */
@Stateless
public class ProcesoFacade extends AbstractFacade<Proceso> {

    @PersistenceContext(unitName = "TaskManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }
    
    public List<Proceso> findById(BigDecimal idProc){
        Query query = em.createNamedQuery("Usuario.findByIdProceso");
        return query.setParameter("idProceso",idProc).getResultList();
    }
    
    public List<Proceso> findByTipoProceso(String nomProc){
        Query query = em.createNamedQuery("Proceso.findByTipoProceso");
        return query.setParameter("tipoProceso",nomProc).getResultList();
    }
}
