/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.session;

import com.taskmanager.entity.Tarea;
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
public class TareaFacade extends AbstractFacade<Tarea> {

    @PersistenceContext(unitName = "TaskManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TareaFacade() {
        super(Tarea.class);
    }
    
    public List<Tarea> findByIdResponsable(Usuario idUser){
        Query query = em.createNamedQuery("Tarea.findByIdResponsable");
        return query.setParameter("usuarioIdUsuario",idUser).getResultList();
    }
    
    public List<Tarea> findByIdTarea(BigDecimal idTarea){
        Query query = em.createNamedQuery("Tarea.findByIdTarea");
        return query.setParameter("idTarea", idTarea).getResultList();
    }
}
