/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.session;

import com.taskmanager.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TaskManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario verifUser(String name, String hash){
        try{
            Usuario uResult = findByName(name).get(0);
            String nameResult = uResult.getNombre();
            String hashResult = uResult.getHash();
            if(name.equals(nameResult) && hash.equals(hashResult)){
                System.out.println("Nombre de usuario y contraseña correctos.");
                return uResult;
            }else{
                    System.out.println("Usuario no accedido.");
            }
        }catch(Exception ex){
            System.out.println("Imposible iniciar el usuario.");
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Usuario> findByName(String nameuser){
        Query query = em.createNamedQuery("Usuario.findByNombre");
        return query.setParameter("nombre",nameuser).getResultList();
    }
    
}
