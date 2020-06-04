/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.session;

import com.taskmanager.entity.StatusWork;
import com.taskmanager.entity.Tarea;
import com.taskmanager.entity.Usuario;
import java.math.BigDecimal;
import java.util.Date;
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
    
    public void crearTarea(String descripcion, Date fechaPlazo, BigDecimal responsable, Usuario usuarioIdUsuario, StatusWork statusWorkIdStatus){
        BigDecimal idUltimaTarea = (findAll().get(0).getIdTarea()).add(new BigDecimal(1));
        Date fechaIngreso = new Date();
        System.out.println("Ultima tarea: "+idUltimaTarea);
        
        //Tarea tr = new Tarea(idUltimaTarea, descripcion, fechaPlazo, responsable);
        Tarea tr = new Tarea();
        tr.setIdTarea(idUltimaTarea);
        tr.setDescripcion(descripcion);
        tr.setFechaIngreso(fechaPlazo);
        tr.setUsuarioIdUsuario(usuarioIdUsuario);
        tr.setStatusWorkIdStatus(statusWorkIdStatus);
        em.persist(tr);
        em.flush();
        System.out.println("Tarea "+idUltimaTarea+" Registrada.");
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
